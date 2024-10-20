package br.com.fiap.vota_e.service;

import br.com.fiap.vota_e.dto.ProjetoCadastroDTO;
import br.com.fiap.vota_e.dto.ProjetoExibicaoDTO;
import br.com.fiap.vota_e.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.model.Projeto;
import br.com.fiap.vota_e.model.Sugestao;
import br.com.fiap.vota_e.repository.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {
    private final ProjetoRepository projetoRepository;

    private final SugestaoService sugestaoService;

    public ProjetoService(ProjetoRepository projetoRepository, SugestaoService sugestaoService) {
        this.projetoRepository = projetoRepository;
        this.sugestaoService = sugestaoService;
    }

    public ProjetoExibicaoDTO salvar(ProjetoCadastroDTO projetoCadastroDTO) {
        Projeto projeto = new Projeto();
        BeanUtils.copyProperties(projetoCadastroDTO, projeto);

        SugestaoExibicaoDTO sugestaoDTO = sugestaoService.buscarPorId(projetoCadastroDTO.sugestao_id());

        if (sugestaoDTO == null) {
            throw new EntityNotFoundException(
                    "Sugestão não encontrada!"
            );
        }

        Sugestao sugestao = new Sugestao();
        BeanUtils.copyProperties(sugestaoDTO, sugestao);

        projeto.setSugestao(sugestao);
        Projeto projetoSalvo = projetoRepository.save(projeto);

        return new ProjetoExibicaoDTO(projetoSalvo);
    }

    public ProjetoExibicaoDTO buscarPorId(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);

        if (projeto.isPresent()) {
            return new ProjetoExibicaoDTO(projeto.get());
        } else {
            throw new EntityNotFoundException("Projeto não encontrado!");
        }
    }

    public List<ProjetoExibicaoDTO> listarTodos() {
        return projetoRepository
                .findAll()
                .stream()
                .map(ProjetoExibicaoDTO::new)
                .toList();
    }

    public void excluir(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        if (projeto.isPresent()) {
            projetoRepository.delete(projeto.get());
        } else {
            throw new EntityNotFoundException("Projeto não encontrado!");
        }
    }

    public ProjetoExibicaoDTO atualizar(ProjetoCadastroDTO projetoCadastroDTO) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(projetoCadastroDTO.projeto_id());

        if (projetoOptional.isPresent()) {
            Projeto projeto = new Projeto();
            BeanUtils.copyProperties(projetoCadastroDTO, projeto);
            return new ProjetoExibicaoDTO(projetoRepository.save(projeto));
        } else {
            throw new EntityNotFoundException("Projeto não encontrado!");
        }
    }
}
