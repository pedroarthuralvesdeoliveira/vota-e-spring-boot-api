package br.com.fiap.vota_e.dto;

import br.com.fiap.vota_e.model.Usuario;

public record UsuarioExibicaoDTO(
    Long usuarioId,
    String nome,
    String email
) {
    public UsuarioExibicaoDTO (Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
