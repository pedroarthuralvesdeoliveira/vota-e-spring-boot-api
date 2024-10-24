package br.com.fiap.vota_e.controller;

import br.com.fiap.vota_e.dto.ProjetoCadastroDTO;
import br.com.fiap.vota_e.dto.ProjetoExibicaoDTO;
import br.com.fiap.vota_e.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ProjetoExibicaoDTO salvar(@RequestBody @Valid ProjetoCadastroDTO projetoCadastroDTO) {
        return projetoService.salvar(projetoCadastroDTO);
    }

    @GetMapping("/projetos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProjetoExibicaoDTO> listar(Pageable pageable) {
        return projetoService.listarTodos(pageable);
    }

    @RequestMapping(value = "/projetos", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProjetoExibicaoDTO> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(projetoService.buscarPorId(id));
    }

    @DeleteMapping("/projetos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        projetoService.excluir(id);
    }

    @PutMapping("/projetos")
    public ResponseEntity<ProjetoExibicaoDTO> atualizar(@RequestBody @Valid ProjetoCadastroDTO projetoCadastroDTO) {
        return ResponseEntity.ok(projetoService.atualizar(projetoCadastroDTO));
    }

    @RequestMapping(value = "/projetos", params = {"dataInicio", "dataFim"})
    @ResponseStatus(HttpStatus.OK)
    public List<ProjetoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
        @RequestParam Date dataInicio,
        @RequestParam Date dataFim
    ) {
        return projetoService.listarSugestoesPorPeriodoDeCriacao(dataInicio, dataFim);
    }
}
