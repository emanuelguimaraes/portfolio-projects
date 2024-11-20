<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portfolio Projects</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.3/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>
<body>

<%@ include file="/WEB-INF/jsp/components/header.jsp" %>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
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
    </div>
</div>

<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>

<script src="<c:url value="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"/>"></script>
</body>
</html>