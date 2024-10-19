package br.com.fiap.vota_e.dto;

import br.com.fiap.vota_e.model.Sugestao;

public record SugestaoExibicaoDTO(
    Long sugestao_id,
    String descricao,
    String observacao,
    String localizacao,
    Long usuarioId,
    String nomeUsuario
) {
    public SugestaoExibicaoDTO (Sugestao sugestao) {
        this(
                sugestao.getSugestao_id(),
                sugestao.getDescricao(),
                sugestao.getObservacao(),
                sugestao.getLocalizacao(),
                sugestao.getUsuario().getId(),
                sugestao.getUsuario().getNome()
        );
    }
}
