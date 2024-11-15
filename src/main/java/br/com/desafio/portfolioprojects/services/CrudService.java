package br.com.desafio.portfolioprojects.services;

import java.util.List;

public interface CrudService<T, ID> {

    T salvar(T entity);
    T buscarPorId(ID id);
    List<T> listarTodos();
    void excluir(ID id);
}