package br.com.fiap.vota_e.controller;

import br.com.fiap.vota_e.config.security.TokenService;
import br.com.fiap.vota_e.dto.LoginDTO;
import br.com.fiap.vota_e.dto.TokenDTO;
import br.com.fiap.vota_e.dto.UsuarioCadastroDTO;
import br.com.fiap.vota_e.dto.UsuarioExibicaoDTO;
import br.com.fiap.vota_e.model.Usuario;
import br.com.fiap.vota_e.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UsuarioService usuarioService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        log.info("Tentativa de login para o email: {}", loginDTO.email());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.senha()
                );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        log.info("Login realizado com sucesso para: {}", loginDTO.email());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO registrar(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
        return usuarioService.salvarUsuario(usuarioCadastroDTO);
    }
}
