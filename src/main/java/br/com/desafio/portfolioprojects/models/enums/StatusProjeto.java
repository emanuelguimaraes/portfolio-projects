package br.com.desafio.portfolioprojects.models.enums;

import lombok.Getter;

@Getter
public enum StatusProjeto {
    EM_ANALISE("Em Análise"),
    ANALISE_REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String label;

    StatusProjeto(String label) {
        this.label = label;
    }
}
