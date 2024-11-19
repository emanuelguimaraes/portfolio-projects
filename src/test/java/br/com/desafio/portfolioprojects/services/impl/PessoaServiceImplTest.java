package br.com.desafio.portfolioprojects.services.impl;

import br.com.desafio.portfolioprojects.dto.PessoaDTO;
import br.com.desafio.portfolioprojects.exceptions.PessoaNaoEncontradaException;
import br.com.desafio.portfolioprojects.models.Pessoa;
import br.com.desafio.portfolioprojects.models.enums.Atribuicao;
import br.com.desafio.portfolioprojects.repositories.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceImplTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Test
    void salvar_comSucesso() {
        Pessoa pessoa = criarPessoa(Atribuicao.FUNCIONARIO);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa pessoaSalva = pessoaService.salvar(pessoa);

        assertNotNull(pessoaSalva);
        assertEquals(pessoa.getNome(), pessoaSalva.getNome());
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void buscarPorId_comSucesso() {
        Pessoa pessoa = criarPessoa(Atribuicao.FUNCIONARIO);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoa));

        Pessoa pessoaEncontrada = pessoaService.buscarPorId(1L);

        assertNotNull(pessoaEncontrada);
        assertEquals(pessoa.getNome(), pessoaEncontrada.getNome());
    }

    @Test
    void buscarPorId_pessoaNaoEncontrada(){
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.buscarPorId(1L));
    }

    @Test
    void listarTodos_comSucesso() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(criarPessoa(Atribuicao.FUNCIONARIO));

        when(pessoaRepository.findAll()).thenReturn(pessoas);
        List<Pessoa> pessoasEncontradas = pessoaService.listarTodos();

        assertNotNull(pessoasEncontradas);
        assertEquals(1, pessoasEncontradas.size());
    }

    @Test
    void excluir_comSucesso() {
        assertDoesNotThrow(() -> pessoaService.excluir(1L));
        verify(pessoaRepository, times(1)).deleteById(1L);
    }

    @Test
    void criarPessoaExterna_comSucesso() {
        PessoaDTO pessoaDTO = criarPessoaDTO("Nome Teste");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(criarPessoa(Atribuicao.FUNCIONARIO));

        Pessoa pessoaCriada = pessoaService.criarPessoaExterna(pessoaDTO);

        assertNotNull(pessoaCriada);
        assertEquals("Nome Teste", pessoaCriada.getNome());
    }

    @Test
    void atualizarPessoa_comSucesso() {
        Pessoa pessoa = criarPessoa(Atribuicao.FUNCIONARIO);
        PessoaDTO pessoaDTO = criarPessoaDTO("Nome Teste Atualizado");

        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(1L, pessoaDTO);

        assertNotNull(pessoaAtualizada);
        assertEquals(pessoaDTO.getNome(), pessoaAtualizada.getNome());
    }

    @Test
    void atualizarPessoa_PessoaNaoEncontrada() {
        PessoaDTO pessoaDTO = criarPessoaDTO("Nome Teste");
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PessoaNaoEncontradaException.class, () -> pessoaService.atualizarPessoa(1L, pessoaDTO));
    }

    @Test
    void listarTodosGerentes() {
        List<Pessoa> gerentes = new ArrayList<>();
        gerentes.add(criarPessoa(Atribuicao.GERENTE));

        when(pessoaRepository.findAllByAtribuicao(Atribuicao.GERENTE)).thenReturn(gerentes);
        List<Pessoa> gerentesEncontrados = pessoaService.listarTodosGerentes();

        assertNotNull(gerentesEncontrados);
        assertEquals(1, gerentesEncontrados.size());
    }

    @Test
    void listarTodosFuncionarios() {
        List<Pessoa> funcionarios = new ArrayList<>();
        funcionarios.add(criarPessoa(Atribuicao.FUNCIONARIO));

        when(pessoaRepository.findAllByAtribuicao(Atribuicao.FUNCIONARIO)).thenReturn(funcionarios);
        List<Pessoa> funcionariosEncontrados = pessoaService.listarTodosFuncionarios();

        assertNotNull(funcionariosEncontrados);
        assertEquals(1, funcionariosEncontrados.size());
    }

    private Pessoa criarPessoa(Atribuicao atribuicao) {
        return Pessoa.builder()
            .id(1L)
            .nome("Nome Teste")
            .atribuicao(atribuicao)
            .build();
    }

    private PessoaDTO criarPessoaDTO(String nome) {
        return PessoaDTO.builder()
            .nome(nome)
            .atribuicao("FUNCIONARIO")
            .build();
    }
}
