package br.com.fiap.vota_e.controller;

import br.com.fiap.vota_e.dto.UsuarioCadastroDTO;
import br.com.fiap.vota_e.dto.UsuarioExibicaoDTO;
import br.com.fiap.vota_e.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@RequestBody UsuarioCadastroDTO usuarioDTO) {
        return usuarioService.salvarUsuario(usuarioDTO);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> listar() {
        return usuarioService.listarTodos();
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioExibicaoDTO> atualizar(@RequestBody UsuarioCadastroDTO usuario) {
        try {
            return ResponseEntity.ok(usuarioService.atualizarUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuarios/{email}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarUsuarioPorEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPeloEmail(email));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarUsuarioPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
