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
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
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
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1>Detalhes do Projeto</h1>
        <div>
            <a href="/projetos" class="btn btn-secondary">Voltar</a>
            <a href="/projetos/editar/${projeto.id}" class="btn btn-warning">Editar</a>
            <button type="button" class="btn btn-danger" onclick="excluirProjeto(${projeto.id})">Excluir</button>
        </div>
    </div>
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
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAdicionarMembro">
            Adicionar Membro
        </button>
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
            <tr>
                <td>${membro.nome}</td>
                <td>${membro.atribuicao.label}</td>
                <td>
                    <div style="display: inline;">
                        <button type="button" class="btn btn-sm btn-danger"
                                onclick="removerMembro(${projeto.id}, ${membro.id})"
                                <c:if test="${membro.atribuicao == 'GERENTE'}">disabled</c:if> >
                            Remover
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="modal_adicionar_membro.jsp"/>
</div>

<footer class="bg-light py-3 text-center">
    <p>© 2024 Portfolio Projects. Todos os direitos reservados.</p>
</footer>

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