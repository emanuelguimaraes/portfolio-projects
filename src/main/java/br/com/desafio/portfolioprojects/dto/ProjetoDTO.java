package br.com.desafio.portfolioprojects.dto;

import br.com.desafio.portfolioprojects.models.enums.ClassificacaoRisco;
import br.com.desafio.portfolioprojects.models.enums.StatusProjeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate previsaoTermino;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataRealTermino;

    private String descricao;

    @NotNull(message = "O status é obrigatório")
    private StatusProjeto status;

    private BigDecimal orcamento;

    @NotNull(message = "O risco é obrigatório")
    private ClassificacaoRisco risco;

    @NotNull(message = "O gerente é obrigatório")
    private Long gerenteId;
}
