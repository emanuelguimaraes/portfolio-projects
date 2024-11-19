package br.com.desafio.portfolioprojects.services;

import br.com.desafio.portfolioprojects.dto.ProjetoDTO;
import br.com.desafio.portfolioprojects.models.Projeto;

public interface ProjetoService extends CrudService<Projeto, Long> {

    Projeto salvar(ProjetoDTO projetoDTO);
    Projeto atualizar(Long id, ProjetoDTO projetoDTO);
    void adicionarMembroAoProjeto(Long projetoId, Long pessoaId);
    void removerMembroProjeto(Long projetoId, Long pessoaId);
}
