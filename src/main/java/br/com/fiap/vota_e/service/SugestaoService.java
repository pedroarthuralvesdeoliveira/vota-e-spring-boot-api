package br.com.fiap.vota_e.service;

import br.com.fiap.vota_e.dto.SugestaoCadastroDTO;
import br.com.fiap.vota_e.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.dto.UsuarioExibicaoDTO;
import br.com.fiap.vota_e.exception.SugestaoNaoEncontradaException;
import br.com.fiap.vota_e.model.Sugestao;
import br.com.fiap.vota_e.model.Usuario;
import br.com.fiap.vota_e.repository.SugestaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SugestaoService {

    private final SugestaoRepository sugestaoRepository;

    private final UsuarioService usuarioService;

    public SugestaoService(SugestaoRepository sugestaoRepository, UsuarioService usuarioService) {
        this.sugestaoRepository = sugestaoRepository;
        this.usuarioService = usuarioService;
    }

    public SugestaoExibicaoDTO salvarSugestao(SugestaoCadastroDTO sugestaoDTO) {
        Sugestao sugestao = new Sugestao();
        BeanUtils.copyProperties(sugestaoDTO, sugestao);

        UsuarioExibicaoDTO usuarioDTO = usuarioService.buscarPorId(sugestaoDTO.usuarioId());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);

        sugestao.setUsuario(usuario);
        Sugestao sugestaoSalvo = sugestaoRepository.save(sugestao);

        return new SugestaoExibicaoDTO(sugestaoSalvo);
    }

    public SugestaoExibicaoDTO buscarPorId(Long id) {
        Optional<Sugestao> sugestao = sugestaoRepository.findById(id);
        if (sugestao.isPresent()) {
            return new SugestaoExibicaoDTO(sugestao.get());
        } else {
            throw new SugestaoNaoEncontradaException("Sugestão não existe!");
        }
    }

    public Page<SugestaoExibicaoDTO> listarSugestoes(Pageable pageable) {
        return sugestaoRepository
                .findAll(pageable)
                .map(SugestaoExibicaoDTO::new)
                ;
    }

    public void excluir(Long id) {
        Optional<Sugestao> sugestao = sugestaoRepository.findById(id);
        if (sugestao.isPresent()) {
            sugestaoRepository.delete(sugestao.get());
        } else {
            throw new SugestaoNaoEncontradaException("Sugestão não encontrada!");
        }
    }

    public SugestaoExibicaoDTO atualizar(SugestaoCadastroDTO sugestaoDTO) {
        Optional<Sugestao> sugestaoOptional = sugestaoRepository.findById(sugestaoDTO.sugestaoId());

        if (sugestaoOptional.isPresent()) {
            Sugestao sugestao = new Sugestao();
            BeanUtils.copyProperties(sugestaoDTO, sugestao);
            return new SugestaoExibicaoDTO(sugestaoRepository.save(sugestao));
        } else {
            throw new SugestaoNaoEncontradaException("Sugestão não encontrada!");
        }
    }

    public List<SugestaoExibicaoDTO> listarSugestoesPorPeriodoDeCriacao(
            Date dataInicio,
            Date dataFim
    ) {
        return sugestaoRepository
                .findByDataCriacaoBetween(
                        dataInicio, dataFim
                )
                .stream()
                .map(SugestaoExibicaoDTO::new)
                .toList();
    }
}
