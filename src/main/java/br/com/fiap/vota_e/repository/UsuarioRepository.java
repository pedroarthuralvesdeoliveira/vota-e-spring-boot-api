package br.com.fiap.vota_e.repository;

import br.com.fiap.vota_e.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByTelefone(String telefone);
}
