package br.com.fiap.vota_e.service;

import br.com.fiap.vota_e.dto.UsuarioCadastroDTO;
import br.com.fiap.vota_e.dto.UsuarioExibicaoDTO;
import br.com.fiap.vota_e.exception.UsuarioNaoEncontradoException;
import br.com.fiap.vota_e.model.Usuario;
import br.com.fiap.vota_e.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    public UsuarioExibicaoDTO buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            return new UsuarioExibicaoDTO(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }

    public List<UsuarioExibicaoDTO> listarTodos() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDTO::new)
                .toList();
    }

    public void excluir(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }

    public UsuarioExibicaoDTO atualizarUsuario(UsuarioCadastroDTO usuarioDTO) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuarioDTO.usuarioId());

        if (usuarioExistente.isPresent()) {
            Usuario usuario = new Usuario();
            BeanUtils.copyProperties(usuarioDTO, usuario);
            return new UsuarioExibicaoDTO(usuarioRepository.save(usuario));
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }

    public UsuarioExibicaoDTO buscarPeloEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {
            return new UsuarioExibicaoDTO(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário com este e-mail não existe!");
        }
    }

    public UsuarioExibicaoDTO buscarPeloTelefone(String telefone) {
        Optional<Usuario> usuario = usuarioRepository.findByTelefone(telefone);

        if (usuario.isPresent()) {
            return new UsuarioExibicaoDTO(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario com este telefone não encontrado!");
        }
    }
}
