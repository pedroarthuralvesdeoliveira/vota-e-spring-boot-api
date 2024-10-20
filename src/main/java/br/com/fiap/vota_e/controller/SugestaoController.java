package br.com.fiap.vota_e.controller;

import br.com.fiap.vota_e.dto.SugestaoCadastroDTO;
import br.com.fiap.vota_e.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.service.SugestaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public SugestaoExibicaoDTO salvar(@RequestBody @Valid SugestaoCadastroDTO sugestao) {
        return sugestaoService.salvarSugestao(sugestao);
    }

    @GetMapping("/sugestoes")
    @ResponseStatus(HttpStatus.OK)
    public List<SugestaoExibicaoDTO> listar() {
        return sugestaoService.listarSugestoes();
    }

    @RequestMapping(value = "/sugestoes", params = "id")
    public ResponseEntity<SugestaoExibicaoDTO> buscar(@RequestParam Long id) {
        return ResponseEntity.ok(sugestaoService.buscarPorId(id));
    }

    @DeleteMapping("/sugestoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        sugestaoService.excluir(id);
    }

    @PutMapping("/sugestoes")
    public ResponseEntity<SugestaoExibicaoDTO> atualizar(@RequestBody @Valid SugestaoCadastroDTO sugestao) {
        return ResponseEntity.ok(sugestaoService.atualizar(sugestao));
    }

    @RequestMapping(value = "/sugestoes", params = {"dataInicio", "dataFim"})
    @ResponseStatus(HttpStatus.OK)
    public List<SugestaoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
            @RequestParam Date dataInicio,
            @RequestParam Date dataFim
    ) {
        return sugestaoService.listarSugestoesPorPeriodoDeCriacao(dataInicio, dataFim);
    }
}
