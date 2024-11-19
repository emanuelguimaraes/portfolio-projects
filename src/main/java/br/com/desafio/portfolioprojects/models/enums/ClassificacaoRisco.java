package br.com.desafio.portfolioprojects.models.enums;

import lombok.Getter;

@Getter
public enum ClassificacaoRisco {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private final String label;

    ClassificacaoRisco(String label) {
        this.label = label;
    }
}
