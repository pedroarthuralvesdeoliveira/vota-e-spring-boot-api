package br.com.fiap.vota_e.service;

import br.com.fiap.vota_e.dto.ProjetoCadastroDTO;
import br.com.fiap.vota_e.dto.ProjetoExibicaoDTO;
import br.com.fiap.vota_e.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.exception.ProjetoNaoEncontradoException;
import br.com.fiap.vota_e.model.Projeto;
import br.com.fiap.vota_e.model.Sugestao;
import br.com.fiap.vota_e.repository.ProjetoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            throw new ProjetoNaoEncontradoException("Projeto não encontrado!");
        }
    }

    public Page<ProjetoExibicaoDTO> listarTodos(Pageable pageable) {
        return projetoRepository
                .findAll(pageable)
                .map(ProjetoExibicaoDTO::new)
                ;
    }

    public void excluir(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        if (projeto.isPresent()) {
            projetoRepository.delete(projeto.get());
        } else {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado!");
        }
    }

    public ProjetoExibicaoDTO atualizar(ProjetoCadastroDTO projetoCadastroDTO) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(projetoCadastroDTO.projeto_id());

        if (projetoOptional.isPresent()) {
            Projeto projeto = new Projeto();
            BeanUtils.copyProperties(projetoCadastroDTO, projeto);
            return new ProjetoExibicaoDTO(projetoRepository.save(projeto));
        } else {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado!");
        }
    }

    public List<ProjetoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
            Date dataInicio,
            Date dataFim
    ) {
        return projetoRepository
                .findByDataCadastroBetween(
                        dataInicio, dataFim
                )
                .stream()
                .map(ProjetoExibicaoDTO::new)
                .toList();
    }
}
