package br.com.desafio.portfolioprojects.services.impl;

import br.com.desafio.portfolioprojects.dto.PessoaDTO;
import br.com.desafio.portfolioprojects.exceptions.PessoaNaoEncontradaException;
import br.com.desafio.portfolioprojects.models.Pessoa;
import br.com.desafio.portfolioprojects.models.enums.Atribuicao;
import br.com.desafio.portfolioprojects.repositories.PessoaRepository;
import br.com.desafio.portfolioprojects.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository repository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Override
    @Transactional
    public Pessoa buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada com ID: " + id));
    }

    @Override
    @Transactional
    public List<Pessoa> listarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Pessoa criarPessoaExterna(PessoaDTO pessoaDTO) {
        Pessoa pessoa = Pessoa.builder()
            .nome(pessoaDTO.getNome())
            .atribuicao(Atribuicao.valueOf(pessoaDTO.getAtribuicao()))
            .build();
        return repository.save(pessoa);
    }

    @Override
    @Transactional
    public Pessoa atualizarPessoa(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoaExistente = buscarPorId(id);

        pessoaExistente.setNome(pessoaDTO.getNome());
        pessoaExistente.setAtribuicao(Atribuicao.valueOf(pessoaDTO.getAtribuicao()));

        return repository.save(pessoaExistente);
    }

    @Override
    @Transactional
    public List<Pessoa> listarTodosGerentes() {
        return repository.findAllByAtribuicao(Atribuicao.GERENTE);
    }

    @Override
    @Transactional
    public List<Pessoa> listarTodosFuncionarios() {
        return repository.findAllByAtribuicao(Atribuicao.FUNCIONARIO);
    }
}
