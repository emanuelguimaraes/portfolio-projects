<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="form-group mb-3">
    <form:label path="nome" cssClass="form-label">Nome</form:label>
    <form:input path="nome" cssClass="form-control" id="nome"/>
    <form:errors path="nome" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="dataInicio" cssClass="form-label">Data de Início</form:label>
    <form:input type="date" path="dataInicio" cssClass="form-control" id="dataInicio"/>
    <form:errors path="dataInicio" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="previsaoTermino" cssClass="form-label">Previsão de Término</form:label>
    <form:input type="date" path="previsaoTermino" cssClass="form-control" id="previsaoTermino"/>
    <form:errors path="previsaoTermino" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="dataRealTermino" cssClass="form-label">Data Real de Término</form:label>
    <form:input type="date" path="dataRealTermino" cssClass="form-control" id="dataRealTermino"/>
    <form:errors path="dataRealTermino" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="orcamento" cssClass="form-label">Orçamento</form:label>
    <form:input path="orcamento" cssClass="form-control" id="orcamento"/>
    <form:errors path="orcamento" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="descricao" cssClass="form-label">Descrição</form:label>
    <form:textarea path="descricao" cssClass="form-control" id="descricao"/>
    <form:errors path="descricao" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="status" cssClass="form-label">Status</form:label>
    <form:select path="status" cssClass="form-control" id="status">
        <form:options items="${statusProjeto}" itemLabel="label" />
    </form:select>
    <form:errors path="status" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="risco" cssClass="form-label">Risco</form:label>
    <form:select path="risco" cssClass="form-control" id="risco">
        <form:options items="${classificacoesRisco}" itemLabel="label" />
    </form:select>
    <form:errors path="risco" cssClass="text-danger"/>
</div>
<div class="form-group mb-3">
    <form:label path="gerenteId" cssClass="form-label">Gerente</form:label>
    <form:select path="gerenteId" cssClass="form-control" id="gerente">
        <form:option value="">Selecione um gerente</form:option>
        <form:options items="${gerentes}" itemValue="id" itemLabel="nome"/>
    </form:select>
    <form:errors path="gerenteId" cssClass="text-danger"/>
</div>