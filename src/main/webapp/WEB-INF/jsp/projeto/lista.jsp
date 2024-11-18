<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <%-- Import fmt para formatação de datas --%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Projetos</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/4.5.2/css/bootstrap.min.css"/>">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Portfolio Projects</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/projetos">Projetos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/pessoas">Pessoas</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h1>Lista de Projetos</h1>
    <c:if test="${not empty mensagemSucesso}">
        <div class="alert alert-success" role="alert">
                ${mensagemSucesso}
        </div>
    </c:if>
    <c:if test="${not empty mensagemErro}">
        <div class="alert alert-danger" role="alert">
                ${mensagemErro}
        </div>
    </c:if>
    <a href="/projetos/novo" class="btn btn-primary mb-3">Novo Projeto</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
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
                <td>${projeto.id}</td>
                <td>${projeto.nome}</td>
                <td><fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${projeto.previsaoTermino}" pattern="dd/MM/yyyy"/></td>
                <td><fmt:formatDate value="${projeto.dataRealTermino}" pattern="dd/MM/yyyy"/></td>
                <td>${projeto.status}</td>
                <td>${projeto.gerente.nome}</td>
                <td>
                    <a href="/projetos/${projeto.id}" class="btn btn-sm btn-info">Detalhes</a>
                    <a href="/projetos/editar/${projeto.id}" class="btn btn-sm btn-warning">Editar</a>
                    <button type="submit" class="btn btn-sm btn-danger" onclick="excluirProjeto(${projeto.id})">Excluir</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="<c:url value="/webjars/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/popper.js/1.16.0/umd/popper.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/src/main/webapp/static/js/script.js"/>"></script>
</body>
</html>