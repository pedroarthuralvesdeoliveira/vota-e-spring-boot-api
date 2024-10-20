package br.com.fiap.vota_e.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProjetoCadastroDTO(
        Long projeto_id,
        @NotBlank(message = "A descrição é obrigatória!")
        String descricao,
        @NotBlank(message = "O título é obrigatório!")
        String titulo,
        String status,
        Date dataCadastro,
        Date dataEnvio,
        Date dataAprovacao,
        @NotNull(message = "A sugestão é obrigatória!")
        Long sugestao_id
) {
}
