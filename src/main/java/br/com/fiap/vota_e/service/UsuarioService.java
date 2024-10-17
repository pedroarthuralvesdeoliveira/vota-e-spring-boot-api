package br.com.fiap.vota_e.service;

import br.com.fiap.vota_e.model.Usuario;
import br.com.fiap.vota_e.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.orElse(null);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void excluir(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        } else {
            throw new RuntimeException("Usuario não encontrado!");
        }
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getId());

        if (usuarioExistente.isPresent()) {
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado!");
        }
    }

    public Usuario buscarPeloEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new RuntimeException("E-mail não existe!");
        }
    }
}
