<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="form-group">
    <form:label path="nome">Nome</form:label>
    <form:input path="nome" cssClass="form-control" id="nome"/>
    <form:errors path="nome" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="dataInicio">Data de Início</form:label>
    <form:input type="date" path="dataInicio" cssClass="form-control" id="dataInicio"/>
    <form:errors path="dataInicio" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="previsaoTermino">Previsão de Término</form:label>
    <form:input type="date" path="previsaoTermino" cssClass="form-control" id="previsaoTermino"/>
    <form:errors path="previsaoTermino" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="dataRealTermino">Data Real de Término</form:label>
    <form:input type="date" path="dataRealTermino" cssClass="form-control" id="dataRealTermino"/>
    <form:errors path="dataRealTermino" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="orcamento">Orçamento</form:label>
    <form:input path="orcamento" cssClass="form-control" id="orcamento"/>
    <form:errors path="orcamento" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="descricao">Descrição</form:label>
    <form:textarea path="descricao" cssClass="form-control" id="descricao"/>
    <form:errors path="descricao" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="status">Status</form:label>
    <form:select path="status" cssClass="form-control" id="status">
        <form:options items="${statusProjeto}" />
    </form:select>
    <form:errors path="status" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="risco">Risco</form:label>
    <form:select path="risco" cssClass="form-control" id="risco">
        <form:options items="${classificacoesRisco}" />
    </form:select>
    <form:errors path="risco" cssClass="text-danger"/>
</div>
<div class="form-group">
    <form:label path="gerenteId">Gerente</form:label>
    <form:select path="gerenteId" cssClass="form-control" id="gerente">
        <form:option value="">Selecione um gerente</form:option>
        <form:options items="${pessoas}" itemValue="id" itemLabel="nome"/>
    </form:select>
    <form:errors path="gerenteId" cssClass="text-danger"/>
</div>