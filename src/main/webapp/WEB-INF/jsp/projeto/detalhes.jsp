<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes do Projeto</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.3/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>
<body>

<%@ include file="/WEB-INF/jsp/components/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div id="message-container">
                <c:if test="${not empty mensagemSucesso}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert" id="mensagemSucesso">
                            ${mensagemSucesso}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <c:if test="${not empty mensagemErro}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert"  id="mensagemErro">
                            ${mensagemErro}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
            </div>
            <h1>Detalhes do Projeto</h1>
            <div class="mb-3">
                <a href="/projetos" class="btn btn-secondary me-2">Voltar</a>
                <a href="/projetos/editar/${projeto.id}" class="btn btn-warning me-2">Editar</a>
                <button type="button" class="btn btn-danger" onclick="excluirProjeto(${projeto.id})">Excluir</button>
            </div>
            <dl class="row">
                <dt class="col-sm-3">Nome:</dt>
                <dd class="col-sm-9">${projeto.nome}</dd>
                <dt class="col-sm-3">Data de Início:</dt>
                <dd class="col-sm-9"><fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy"/></dd>
                <dt class="col-sm-3">Previsão de Término:</dt>
                <dd class="col-sm-9"><fmt:formatDate value="${projeto.previsaoTermino}" pattern="dd/MM/yyyy"/></dd>
                <dt class="col-sm-3">Data Real de Término:</dt>
                <dd class="col-sm-9"><fmt:formatDate value="${projeto.dataRealTermino}" pattern="dd/MM/yyyy"/></dd>
                <dt class="col-sm-3">Orçamento:</dt>
                <dd class="col-sm-9">${projeto.orcamento}</dd>
                <dt class="col-sm-3">Descrição:</dt>
                <dd class="col-sm-9">${projeto.descricao}</dd>
                <dt class="col-sm-3">Status:</dt>
                <dd class="col-sm-9">${projeto.status.label}</dd>
                <dt class="col-sm-3">Risco:</dt>
                <dd class="col-sm-9">${projeto.risco.label}</dd>
                <dt class="col-sm-3">Gerente:</dt>
                <dd class="col-sm-9">${projeto.gerente.nome}</dd>
            </dl>
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h2>Membros do Projeto</h2>
                <div class="mb-3">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAdicionarMembro">
                        Adicionar Membro
                    </button>
                </div>
            </div>
            <table class="table table-striped" id="tabelaMembros">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Atribuição</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="membro" items="${projeto.membros}">
                    <tr id="membro-${membro.id}">
                        <td>${membro.nome}</td>
                        <td>${membro.atribuicao.label}</td>
                        <td>
                            <button type="button" class="btn btn-sm btn-danger" onclick="removerMembro(${projeto.id}, ${membro.id})"
                                    <c:if test="${membro.atribuicao == 'GERENTE'}">disabled</c:if>>Remover</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <jsp:include page="modal_adicionar_membro.jsp"/>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>

<script src="<c:url value="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/static/js/script.js"/>"></script>
</body>
</html>