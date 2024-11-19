package br.com.desafio.portfolioprojects.services;

import br.com.desafio.portfolioprojects.dto.PessoaDTO;
import br.com.desafio.portfolioprojects.models.Pessoa;

import java.util.List;

public interface PessoaService extends CrudService<Pessoa, Long> {

    Pessoa criarPessoaExterna(PessoaDTO pessoaDTO);
    Pessoa atualizarPessoa(Long id, PessoaDTO pessoaDTO);
    List<Pessoa> listarTodosGerentes();
    List<Pessoa> listarTodosFuncionarios();
}
