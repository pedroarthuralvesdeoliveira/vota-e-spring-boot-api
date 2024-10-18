package br.com.fiap.vota_e.dto;

public record UsuarioCadastroDTO (
        Long usuarioId,
        String nome,
        String email,
        String senha
) {}
