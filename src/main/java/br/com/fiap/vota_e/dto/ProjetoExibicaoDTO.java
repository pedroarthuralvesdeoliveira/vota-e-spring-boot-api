package br.com.fiap.vota_e.dto;

import br.com.fiap.vota_e.model.Projeto;

import java.util.Date;

public record ProjetoExibicaoDTO(
        Long projetoId,
        String descricao,
        String titulo,
        String status,
        Date dataCadastro,
        Date dataEnvio,
        Date dataAprovacao,
        Long sugestaoId
) {
    public ProjetoExibicaoDTO (Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getDescricao(),
                projeto.getTitulo(),
                projeto.getStatus(),
                projeto.getDataCadastro(),
                projeto.getDataEnvio(),
                projeto.getDataAprovacao(),
                projeto.getSugestao().getId()
        );
    }
}
