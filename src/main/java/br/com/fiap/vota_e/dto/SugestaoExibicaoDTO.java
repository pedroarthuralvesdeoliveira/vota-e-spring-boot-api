package br.com.fiap.vota_e.dto;

import br.com.fiap.vota_e.model.Sugestao;

import java.util.Date;

public record SugestaoExibicaoDTO(
    Long sugestaoId,
    String descricao,
    String observacao,
    String localizacao,
    Date dataCriacao,
    Long usuarioId,
    String nomeUsuario
) {
    public SugestaoExibicaoDTO (Sugestao sugestao) {
        this(
                sugestao.getSugestaoId(),
                sugestao.getDescricao(),
                sugestao.getObservacao(),
                sugestao.getLocalizacao(),
                sugestao.getDataCriacao(),
                sugestao.getUsuario().getUsuarioId(),
                sugestao.getUsuario().getNome()
        );
    }
}
