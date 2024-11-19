<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="modalAdicionarMembro" tabindex="-1" role="dialog" aria-labelledby="modalAdicionarMembroLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalAdicionarMembroLabel">Adicionar Membro ao Projeto</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form action="/projetos/${projeto.id}/membro" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="pessoaId">Pessoa:</label>
                        <select name="pessoaId" id="pessoaId" class="form-control">
                            <option value="">Selecione um funcionário</option>
                            <c:forEach items="${pessoasDisponiveis}" var="pessoa">
                                <option value="${pessoa.id}">${pessoa.nome}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                    <button type="submit" class="btn btn-primary">Adicionar</button>
                </div>
            </form>
        </div>
    </div>
</div>