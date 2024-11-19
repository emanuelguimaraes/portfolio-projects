package br.com.desafio.portfolioprojects.services.impl;

import br.com.desafio.portfolioprojects.dto.ProjetoDTO;
import br.com.desafio.portfolioprojects.exceptions.*;
import br.com.desafio.portfolioprojects.models.Pessoa;
import br.com.desafio.portfolioprojects.models.Projeto;
import br.com.desafio.portfolioprojects.models.enums.Atribuicao;
import br.com.desafio.portfolioprojects.models.enums.ClassificacaoRisco;
import br.com.desafio.portfolioprojects.models.enums.StatusProjeto;
import br.com.desafio.portfolioprojects.repositories.ProjetoRepository;
import br.com.desafio.portfolioprojects.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceImplTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private ProjetoServiceImpl projetoService;

    @Test
    void salvar_comSucesso() {
        Projeto projeto = criarProjeto();
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        Projeto projetoSalvo = projetoService.salvar(projeto);

        assertNotNull(projetoSalvo);
        assertEquals(projeto.getNome(), projetoSalvo.getNome());
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void salvar_projetoDTO_comSucesso() {
        Projeto projeto = criarProjeto();
        ProjetoDTO projetoDTO = criarProjetoDTO();
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        Projeto projetoSalvo = projetoService.salvar(projetoDTO);

        assertNotNull(projetoSalvo);
        assertEquals(projetoDTO.getNome(), projetoSalvo.getNome());
    }

    @Test
    void atualizar_comSucesso() {
        Projeto projeto = criarProjeto();
        ProjetoDTO projetoDTO = criarProjetoDTO();

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenReturn(projeto.getGerente());
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        Projeto projetoAtualizado = projetoService.atualizar(1L, projetoDTO);

        assertNotNull(projetoAtualizado);
        assertEquals(projetoDTO.getNome(), projetoAtualizado.getNome());
    }

    @Test
    void atualizar_projetoNaoEncontrado() {
        ProjetoDTO projetoDTO = criarProjetoDTO();

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.atualizar(1L, projetoDTO));
    }
    @Test
    void atualizar_gerenteNaoEncontrado(){
        Projeto projeto = criarProjeto();
        ProjetoDTO projetoDTO = criarProjetoDTO();

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenReturn(null);

        assertThrows(GerenteNaoEncontradoException.class, () -> projetoService.atualizar(1L, projetoDTO));
    }

    @Test
    void buscarPorId_comSucesso() {
        Projeto projeto = criarProjeto();
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));

        Projeto projetoEncontrado = projetoService.buscarPorId(1L);

        assertNotNull(projetoEncontrado);
        assertEquals(1L, projetoEncontrado.getId());
    }

    @Test
    void buscarPorId_projetoNaoEncontrado() {
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.buscarPorId(1L));
    }

    @Test
    void listarTodos_comSucesso() {
        List<Projeto> projetos = new ArrayList<>();
        projetos.add(criarProjeto());

        when(projetoRepository.findAll()).thenReturn(projetos);

        List<Projeto> projetosEncontrados = projetoService.listarTodos();

        assertNotNull(projetosEncontrados);
        assertEquals(1, projetosEncontrados.size());
    }

    @Test
    void excluir_comSucesso() {
        Projeto projeto = criarProjeto();
        projeto.setStatus(StatusProjeto.EM_ANALISE);
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));

        assertDoesNotThrow(() -> projetoService.excluir(1L));
        verify(projetoRepository, times(1)).deleteById(1L);
    }

    @Test
    void excluir_projetoNaoEncontrado() {
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.excluir(1L));
    }

    @Test
    void excluir_projetoNaoPodeSerExcluido() {
        Projeto projeto = criarProjeto();
        projeto.setStatus(StatusProjeto.INICIADO);
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));

        assertThrows(ProjetoNaoPodeSerExcluidoException.class, () -> projetoService.excluir(1L));
    }

    @Test
    void adicionarMembroAoProjeto_comSucesso() {
        Projeto projeto = criarProjeto();
        Pessoa pessoa = criarPessoaFuncionario();
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenReturn(pessoa);
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        projetoService.adicionarMembroAoProjeto(1L, 1L);

        assertTrue(projeto.getMembros().contains(pessoa));
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void adicionarMembroAoProjeto_projetoNaoEncontrado() {
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.adicionarMembroAoProjeto(1L, 1L));
    }

    @Test
    void adicionarMembroAoProjeto_pessoaNaoEncontrada() {
        Projeto projeto = criarProjeto();
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenThrow(PessoaNaoEncontradaException.class);
        assertThrows(PessoaNaoEncontradaException.class, () -> projetoService.adicionarMembroAoProjeto(1L, 1L));
    }

    @Test
    void adicionarMembroAoProjeto_gerenteNaoPodeSerAdicionado() {
        Projeto projeto = criarProjeto();
        Pessoa gerente = criarPessoaGerente();

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenReturn(gerente);

        assertThrows(PessoaNaoPodeSerAdicionadaAoProjetoException.class, () -> projetoService.adicionarMembroAoProjeto(1L, 1L));
    }

    @Test
    void removerMembroProjeto_comSucesso() {
        Projeto projeto = criarProjeto();
        Pessoa membro = criarPessoaFuncionario();
        projeto.adicionarMembro(membro);
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenReturn(membro);

        assertDoesNotThrow(() -> projetoService.removerMembroProjeto(1L, 1L));

        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void removerMembroProjeto_projetoNaoEncontrado() {
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ProjetoNaoEncontradoException.class, () -> projetoService.removerMembroProjeto(1L, 1L));
    }

    @Test
    void removerMembroProjeto_pessoaNaoEncontrada() {
        Projeto projeto = criarProjeto();
        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenThrow(PessoaNaoEncontradaException.class);

        assertThrows(PessoaNaoEncontradaException.class, () -> projetoService.removerMembroProjeto(1L, 1L));
    }

    @Test
    void removerMembroProjeto_gerenteNaoPodeSerRemovido() {
        Projeto projeto = criarProjeto();
        Pessoa gerente = criarPessoaGerente();

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(pessoaService.buscarPorId(anyLong())).thenReturn(gerente);

        assertThrows(GerenteNaoPodeSerExcluidoException.class, () -> projetoService.removerMembroProjeto(1L, 1L));
    }

    private Projeto criarProjeto() {
        Pessoa gerente = criarPessoaGerente();
        return Projeto.builder()
            .id(1L)
            .nome("Projeto Teste")
            .dataInicio(new Date())
            .previsaoTermino(new Date())
            .dataRealTermino(new Date())
            .orcamento(BigDecimal.valueOf(1000))
            .descricao("Descrição do projeto")
            .status(StatusProjeto.EM_ANALISE)
            .risco(ClassificacaoRisco.BAIXO)
            .gerente(gerente)
            .membros(new ArrayList<>())
            .build();
    }

    private ProjetoDTO criarProjetoDTO() {
        return ProjetoDTO.builder()
            .nome("Projeto Teste")
            .dataInicio(new Date())
            .previsaoTermino(new Date())
            .dataRealTermino(new Date())
            .orcamento(BigDecimal.valueOf(1000))
            .descricao("Descrição do projeto")
            .status(StatusProjeto.EM_ANALISE)
            .risco(ClassificacaoRisco.BAIXO)
            .gerenteId(1L)
            .build();
    }

    private Pessoa criarPessoaGerente() {
        return Pessoa.builder()
            .id(1L)
            .nome("Gerente Teste")
            .atribuicao(Atribuicao.GERENTE)
            .build();
    }

    private Pessoa criarPessoaFuncionario() {
        return Pessoa.builder()
            .id(2L)
            .nome("Funcionário Teste")
            .atribuicao(Atribuicao.FUNCIONARIO)
            .build();
    }
}
