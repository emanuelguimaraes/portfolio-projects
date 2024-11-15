package br.com.desafio.portfolioprojects.models;

import br.com.desafio.portfolioprojects.models.enums.Atribuicao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Atribuicao atribuicao;

    @ManyToMany(mappedBy = "membros")
    private List<Projeto> projetos;
}
