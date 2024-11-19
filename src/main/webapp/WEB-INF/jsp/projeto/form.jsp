<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulário de Projeto</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/4.5.2/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
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
    <h1>Formulário de Projeto</h1>
    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
    <c:choose>
        <c:when test="${projetoID != null}">
            <form:form method="post" action="/projetos/${projetoID}" modelAttribute="projetoDTO">
                <%@include file="form_fields.jsp"%>
                <div class="button-container d-flex justify-content-end mt-3">
                    <a href="/projetos" class="btn btn-secondary mr-2">Voltar</a>
                    <button type="button" class="btn btn-primary" onclick="atualizarProjeto(${projetoID})">Atualizar</button>
                </div>
            </form:form>
        </c:when>
        <c:otherwise>
            <form:form method="post" action="/projetos/salvar" modelAttribute="projetoDTO">
                <%@include file="form_fields.jsp"%>
                <div class="button-container d-flex justify-content-end mt-3">
                    <a href="/projetos" class="btn btn-secondary mr-2">Voltar</a>
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </div>
            </form:form>
        </c:otherwise>
    </c:choose>
</div>

<footer class="bg-light py-3 text-center">
    <p>© 2024 Portfolio Projects. Todos os direitos reservados.</p>
</footer>

<script src="<c:url value="/webjars/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/popper.js/1.16.0/umd/popper.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/js/script.js"/>"></script>
</body>
</html>