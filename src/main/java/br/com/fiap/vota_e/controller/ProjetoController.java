package br.com.fiap.vota_e.controller;

import br.com.fiap.vota_e.dto.ProjetoCadastroDTO;
import br.com.fiap.vota_e.dto.ProjetoExibicaoDTO;
import br.com.fiap.vota_e.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjetoController {
    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping("/projetos")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjetoExibicaoDTO salvar(@RequestBody ProjetoCadastroDTO projetoCadastroDTO) {
        return projetoService.salvar(projetoCadastroDTO);
    }

    @GetMapping("/projetos")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoExibicaoDTO> listar() {
        return projetoService.listarTodos();
    }

    @GetMapping("/projetos/{id}")
    public ResponseEntity<ProjetoExibicaoDTO> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projetoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/projetos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        projetoService.excluir(id);
    }

    @PutMapping("/projetos")
    public ResponseEntity<ProjetoExibicaoDTO> atualizar(@RequestBody ProjetoCadastroDTO projetoCadastroDTO) {
        try {
            return ResponseEntity.ok(projetoService.atualizar(projetoCadastroDTO));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
