package br.com.desafio.portfolioprojects.models.enums;

import lombok.Getter;

@Getter
public enum Atribuicao {
    GERENTE("Gerente"),
    FUNCIONARIO("Funcionário");

    private final String label;

    Atribuicao(String label) {
        this.label = label;
    }
}
