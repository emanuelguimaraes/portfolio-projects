package br.com.desafio.portfolioprojects.models;

import br.com.desafio.portfolioprojects.models.enums.ClassificacaoRisco;
import br.com.desafio.portfolioprojects.models.enums.StatusProjeto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_previsao_fim")
    private Date previsaoTermino;

    @Column(name = "data_fim")
    private Date dataRealTermino;

    @Column(length = 5000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProjeto status;

    @Column(precision = 19, scale = 2)
    private BigDecimal orcamento;

    @Enumerated(EnumType.STRING)
    private ClassificacaoRisco risco;

    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    private Pessoa gerente;

    @ManyToMany
    @JoinTable(
        name = "membros",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    private List<Pessoa> membros = new ArrayList<>();

    public void adicionarMembro(Pessoa pessoa) {
        this.membros.add(pessoa);
    }

    public void removerMembro(Pessoa pessoa) {
        this.membros.remove(pessoa);
    }
}
