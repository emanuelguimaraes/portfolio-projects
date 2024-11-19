<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Pessoas</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/4.5.2/css/bootstrap.min.css"/>">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Portfolio Projects</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/projetos">Projetos</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/pessoas">Pessoas</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h1>Lista de Pessoas</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Atribuição</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="pessoa" items="${pessoas}">
            <tr>
                <td>${pessoa.id}</td>
                <td>${pessoa.nome}</td>
                <td>${pessoa.atribuicao}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="<c:url value="/webjars/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/popper.js/1.16.0/umd/popper.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"/>"></script>
</body>
</html>