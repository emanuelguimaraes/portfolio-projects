package br.com.desafio.portfolioprojects.services;

import br.com.desafio.portfolioprojects.dto.PessoaDTO;
import br.com.desafio.portfolioprojects.models.Pessoa;

public interface PessoaService extends CrudService<Pessoa, Long> {

    Pessoa criarPessoaExterna(PessoaDTO pessoaDTO);
    Pessoa atualizarPessoa(Long id, PessoaDTO pessoaDTO);
}
