package br.com.fiap.vota_e.dto;

import java.util.Date;

public record ProjetoCadastroDTO(
        Long projeto_id,
        String descricao,
        String titulo,
        String status,
        Date dataCadastro,
        Date dataEnvio,
        Date dataAprovacao,
        Long sugestao_id
) {
}
