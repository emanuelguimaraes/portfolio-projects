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
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.3/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>
<body>

<%@ include file="/WEB-INF/jsp/components/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1>Formulário de Projeto</h1>
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <c:choose>
                <c:when test="${projetoID != null}">
                    <form:form method="post" action="/projetos/${projetoID}" modelAttribute="projetoDTO" id="formProjeto">
                        <%@include file="form_fields.jsp"%>
                        <div class="button-container d-flex justify-content-end mt-3">
                            <a href="/projetos" class="btn btn-secondary me-2">Voltar</a>
                            <button type="button" class="btn btn-primary" onclick="atualizarProjeto(${projetoID})">Atualizar</button>
                        </div>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <form:form method="post" action="/projetos/salvar" modelAttribute="projetoDTO" id="formProjeto">
                        <%@include file="form_fields.jsp"%>
                        <div class="button-container d-flex justify-content-end mt-3">
                            <a href="/projetos" class="btn btn-secondary me-2">Voltar</a>
                            <button type="submit" class="btn btn-primary">Salvar</button>
                        </div>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>

<script src="<c:url value="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/static/js/script.js"/>"></script>

</body>
</html>