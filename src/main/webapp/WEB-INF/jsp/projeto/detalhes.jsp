<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes do Projeto</title>
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
    <h1>Detalhes do Projeto</h1>
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
    <dl class="row">
        <dt class="col-sm-3">ID:</dt>
        <dd class="col-sm-9">${projeto.id}</dd>
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
        <dd class="col-sm-9">${projeto.status}</dd>
        <dt class="col-sm-3">Risco:</dt>
        <dd class="col-sm-9">${projeto.risco}</dd>
        <dt class="col-sm-3">Gerente:</dt>
        <dd class="col-sm-9">${projeto.gerente.nome}</dd>
    </dl>
    <h2>Membros do Projeto</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Atribuição</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="membro" items="${projeto.membros}">
            <tr>
                <td>${membro.nome}</td>
                <td>${membro.atribuicao}</td>
                <td>
                    <div style="display: inline;">
                        <button type="button" class="btn btn-sm btn-danger" onclick="removerMembro(${projeto.id}, ${membro.id})">Remover</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2>Adicionar Membro ao Projeto</h2>
    <form action="/projetos/${projeto.id}/membro" method="post">
        <div class="form-group">
            <label for="pessoaId">Pessoa:</label>
            <select name="pessoaId" id="pessoaId" class="form-control">
                <c:forEach items="${pessoasDisponiveis}" var="pessoa">
                    <option value="${pessoa.id}">${pessoa.nome} (${pessoa.atribuicao})</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Adicionar</button>
    </form>
    <a href="/projetos" class="btn btn-secondary mt-3">Voltar</a>
    <a href="/projetos/editar/${projeto.id}" class="btn btn-warning mt-3">Editar</a>
    <div style="display: inline;">
        <button type="button" class="btn btn-sm btn-danger mt-3" onclick="excluirProjeto(${projeto.id})">Excluir</button>
    </div>
</div>

<script src="<c:url value="/webjars/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/popper.js/1.16.0/umd/popper.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/static/js/script.js"/>"></script>
<script>
    window.addEventListener('load', exibirMensagemSucesso);
    window.addEventListener('load', exibirMensagemErro);
</script>
</body>
</html>