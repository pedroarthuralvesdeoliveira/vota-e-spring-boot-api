package br.com.fiap.vota_e.dto;

public record SugestaoCadastroDTO(
        Long sugestaoId,
        String descricao,
        String observacao,
        String localizacao,
        Long usuarioId
) {
}
