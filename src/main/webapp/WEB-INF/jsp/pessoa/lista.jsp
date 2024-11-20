<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Pessoas</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.3/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>
<body>

<%@ include file="/WEB-INF/jsp/components/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1>Lista de Pessoas</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Atribuição</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="pessoa" items="${pessoas}">
                    <tr>
                        <td>${pessoa.nome}</td>
                        <td>${pessoa.atribuicao.label}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/components/footer.jsp" %>

<script src="<c:url value="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"/>"></script>

</body>
</html>