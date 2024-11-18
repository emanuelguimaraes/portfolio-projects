<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portfolio Projects</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/4.5.2/css/bootstrap.min.css"/>">
    <%--<link rel="stylesheet" href="/css/style.css">--%>
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
            <li class="nav-item">
                <a class="nav-link" href="/pessoas">Pessoas</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h1>Bem-vindo ao Portfolio Projects</h1>
    <p>Este é o sistema de gerenciamento de portfólio de projetos.</p>
    <div class="row mt-4">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Gerenciar Projetos</h5>
                    <p class="card-text">Acesse a lista de projetos, crie novos projetos, edite e exclua projetos existentes.</p>
                    <a href="/projetos" class="btn btn-primary">Gerenciar Projetos</a>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Gerenciar Pessoas</h5>
                    <p class="card-text">Acesse a lista de pessoas.</p>
                    <a href="/pessoas" class="btn btn-primary">Gerenciar Pessoas</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/webjars/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/popper.js/1.16.0/umd/popper.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"/>"></script>
<%--<script src="/js/script.js"></script>--%>
</body>
</html>