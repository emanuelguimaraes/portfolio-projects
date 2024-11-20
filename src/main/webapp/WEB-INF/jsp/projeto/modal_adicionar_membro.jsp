<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="modalAdicionarMembro" tabindex="-1" aria-labelledby="modalAdicionarMembroLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalAdicionarMembroLabel">Adicionar Membro ao Projeto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/projetos/${projeto.id}/membro" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="pessoaId" class="form-label">Pessoa:</label>
                        <select name="pessoaId" id="pessoaId" class="form-select">
                            <option value="">Selecione um funcionário</option>
                            <c:forEach items="${pessoasDisponiveis}" var="pessoa">
                                <option value="${pessoa.id}">${pessoa.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                    <button type="submit" class="btn btn-primary">Adicionar</button>
                </div>
            </form>
        </div>
    </div>
</div>