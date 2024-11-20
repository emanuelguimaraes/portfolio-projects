<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Projetos</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.3/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>
<body>

<%@ include file="/WEB-INF/jsp/components/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h1>Lista de Projetos</h1>
                <a href="/projetos/novo" class="btn btn-primary">Novo Projeto</a>
            </div>
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
            <table class="table table-striped" id="tabelaProjetos">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Data de Início</th>
                    <th>Previsão de Término</th>
                    <th>Data Real de Término</th>
                    <th>Status</th>
                    <th>Gerente</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="projeto" items="${projetos}">
                    <tr id="projeto-${projeto.id}">
                        <td>${projeto.nome}</td>
                        <td><fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatDate value="${projeto.previsaoTermino}" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatDate value="${projeto.dataRealTermino}" pattern="dd/MM/yyyy"/></td>
                        <td>${projeto.status.label}</td>
                        <td>${projeto.gerente.nome}</td>
                        <td>
                            <a href="/projetos/${projeto.id}" class="btn btn-sm btn-info">Detalhes</a>
                            <a href="/projetos/editar/${projeto.id}" class="btn btn-sm btn-warning">Editar</a>
                            <button type="button" class="btn btn-sm btn-danger" onclick="excluirProjeto(${projeto.id})">Excluir</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>

<script src="<c:url value="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/static/js/script.js"/>"></script>

</body>
</html>