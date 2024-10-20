package br.com.fiap.vota_e.controller;

import br.com.fiap.vota_e.dto.SugestaoCadastroDTO;
import br.com.fiap.vota_e.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.service.SugestaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SugestaoController {
    private final SugestaoService sugestaoService;

    public SugestaoController(SugestaoService sugestaoService) {
        this.sugestaoService = sugestaoService;
    }

    @PostMapping("/sugestoes")
    @ResponseStatus(HttpStatus.CREATED)
    public SugestaoExibicaoDTO salvar(@RequestBody SugestaoCadastroDTO sugestao) {
        return sugestaoService.salvarSugestao(sugestao);
    }

    @GetMapping("/sugestoes")
    @ResponseStatus(HttpStatus.OK)
    public List<SugestaoExibicaoDTO> listar() {
        return sugestaoService.listarSugestoes();
    }

    @GetMapping("/sugestoes/{id}")
    public ResponseEntity<SugestaoExibicaoDTO> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sugestaoService.buscarPorId(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/sugestoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        sugestaoService.excluir(id);
    }

    @PutMapping("/sugestoes")
    public ResponseEntity<SugestaoExibicaoDTO> atualizar(@RequestBody SugestaoCadastroDTO sugestao) {
        try {
            return ResponseEntity.ok(sugestaoService.atualizar(sugestao));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
