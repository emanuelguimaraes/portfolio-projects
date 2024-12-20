package br.com.desafio.portfolioprojects.services.impl;

import br.com.desafio.portfolioprojects.dto.ProjetoDTO;
import br.com.desafio.portfolioprojects.exceptions.*;
import br.com.desafio.portfolioprojects.models.Pessoa;
import br.com.desafio.portfolioprojects.models.Projeto;
import br.com.desafio.portfolioprojects.models.enums.Atribuicao;
import br.com.desafio.portfolioprojects.models.enums.StatusProjeto;
import br.com.desafio.portfolioprojects.repositories.ProjetoRepository;
import br.com.desafio.portfolioprojects.services.PessoaService;
import br.com.desafio.portfolioprojects.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository repository;
    private final PessoaService pessoaService;

    @Autowired
    public ProjetoServiceImpl(ProjetoRepository repository, PessoaService pessoaService) {
        this.repository = repository;
        this.pessoaService = pessoaService;
    }

    @Override
    @Transactional
    public Projeto salvar(Projeto projeto) {
        if (!projeto.getMembros().contains(projeto.getGerente())) {
            projeto.adicionarMembro(projeto.getGerente());
        }
        return repository.save(projeto);
    }

    @Override
    @Transactional
    public Projeto salvar(ProjetoDTO projetoDTO) {
        return salvar(convertToEntity(projetoDTO));
    }

    @Override
    @Transactional
    public Projeto atualizar(Long id, ProjetoDTO projetoDTO) {
        Projeto projetoExistente = buscarPorId(id);
        Pessoa gerente = pessoaService.buscarPorId(projetoDTO.getGerenteId());

        if (gerente == null) {
            throw new GerenteNaoEncontradoException("Gerente não encontrado com ID: " + projetoDTO.getGerenteId());
        }

        if (projetoExistente.getGerente().getId() != projetoDTO.getGerenteId()) {
            projetoExistente.removerMembro(projetoExistente.getGerente());
            projetoExistente.adicionarMembro(gerente);
        }

        projetoExistente.setNome(projetoDTO.getNome());
        projetoExistente.setDataInicio(projetoDTO.getDataInicio());
        projetoExistente.setPrevisaoTermino(projetoDTO.getPrevisaoTermino());
        projetoExistente.setDataRealTermino(projetoDTO.getDataRealTermino());
        projetoExistente.setDescricao(projetoDTO.getDescricao());
        projetoExistente.setStatus(projetoDTO.getStatus());
        projetoExistente.setOrcamento(projetoDTO.getOrcamento());
        projetoExistente.setRisco(projetoDTO.getRisco());
        projetoExistente.setGerente(gerente);

        return repository.save(projetoExistente);
    }

    @Override
    @Transactional
    public Projeto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProjetoNaoEncontradoException("Projeto não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public List<Projeto> listarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Projeto projeto = buscarPorId(id);

        if (StatusProjeto.INICIADO.equals(projeto.getStatus())
            || StatusProjeto.EM_ANDAMENTO.equals(projeto.getStatus())
            || StatusProjeto.ENCERRADO.equals(projeto.getStatus())) {
            throw new ProjetoNaoPodeSerExcluidoException("O projeto não pode ser excluído pois já foi iniciado, está em andamento ou foi encerrado.");
        }

        projeto.getMembros().clear();
        repository.save(projeto);

        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void adicionarMembroAoProjeto(Long projetoId, Long pessoaId) {
        Projeto projeto = buscarPorId(projetoId);
        Pessoa pessoa = pessoaService.buscarPorId(pessoaId);

        if (Atribuicao.GERENTE.equals(pessoa.getAtribuicao())) {
            throw new PessoaNaoPodeSerAdicionadaAoProjetoException("Somente funcionários podem ser adicionados ao projeto.");
        }

        projeto.adicionarMembro(pessoa);
        salvar(projeto);
    }

    @Override
    @Transactional
    public void removerMembroProjeto(Long projetoId, Long pessoaId) {
        Projeto projeto = buscarPorId(projetoId);
        Pessoa pessoa = pessoaService.buscarPorId(pessoaId);

        if (Atribuicao.GERENTE.equals(pessoa.getAtribuicao())) {
            throw new GerenteNaoPodeSerExcluidoException("Não é possível remover o gerente do projeto");
        }

        projeto.removerMembro(pessoa);
        salvar(projeto);
    }

    private Projeto convertToEntity(ProjetoDTO projetoDTO) {
        Pessoa gerente = pessoaService.buscarPorId(projetoDTO.getGerenteId());

        return Projeto.builder()
            .nome(projetoDTO.getNome())
            .dataInicio(projetoDTO.getDataInicio())
            .previsaoTermino(projetoDTO.getPrevisaoTermino())
            .dataRealTermino(projetoDTO.getDataRealTermino())
            .descricao(projetoDTO.getDescricao())
            .status(projetoDTO.getStatus())
            .orcamento(projetoDTO.getOrcamento())
            .risco(projetoDTO.getRisco())
            .gerente(gerente)
            .membros(new ArrayList<>())
            .build();
    }
}
