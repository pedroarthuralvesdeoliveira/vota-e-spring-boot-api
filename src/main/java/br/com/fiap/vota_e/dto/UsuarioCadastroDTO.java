package br.com.fiap.vota_e.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO (
        Long usuarioId,
        @NotBlank(message = "O nome de usuário é obrigatório!")
        String nome,
        @NotBlank(message = "O e-mail do usuário é obrigatório!")
        @Email(message = "O e-mail do usuário não é válido!")
        String email,
        @NotBlank(message = "A senha é obrigatória!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
        String senha
) {}
