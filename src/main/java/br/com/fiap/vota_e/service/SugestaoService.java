package br.com.fiap.vota_e.service;

import br.com.fiap.vota_e.dto.SugestaoCadastroDTO;
import br.com.fiap.vota_e.dto.SugestaoExibicaoDTO;
import br.com.fiap.vota_e.model.Sugestao;
import br.com.fiap.vota_e.model.Usuario;
import br.com.fiap.vota_e.repository.SugestaoRepository;
import br.com.fiap.vota_e.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SugestaoService {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public SugestaoExibicaoDTO salvarSugestao(SugestaoCadastroDTO sugestaoDTO) {
        Sugestao sugestao = new Sugestao();
        BeanUtils.copyProperties(sugestaoDTO, sugestao);

        Usuario usuario = usuarioRepository.findById(sugestaoDTO.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Usuário não encontrado!"
                ));

        sugestao.setUsuario(usuario);

        Sugestao sugestaoSalvo = sugestaoRepository.save(sugestao);
        return new SugestaoExibicaoDTO(sugestaoSalvo);
    }

    public SugestaoExibicaoDTO buscarPorId(Long id) {
        Optional<Sugestao> sugestao = sugestaoRepository.findById(id);
        if (sugestao.isPresent()) {
            return new SugestaoExibicaoDTO(sugestao.get());
        } else {
            throw new RuntimeException("Sugestão não existe!");
        }
    }

    public List<SugestaoExibicaoDTO> listarSugestoes() {
        return sugestaoRepository
                .findAll()
                .stream()
                .map(SugestaoExibicaoDTO::new)
                .toList();
    }

    public void excluir(Long id) {
        Optional<Sugestao> sugestao = sugestaoRepository.findById(id);
        if (sugestao.isPresent()) {
            sugestaoRepository.delete(sugestao.get());
        } else {
            throw new RuntimeException("Sugestão não encontrada!");
        }
    }

    public SugestaoExibicaoDTO atualizar(SugestaoCadastroDTO sugestaoDTO) {
        Optional<Sugestao> sugestaoOptional = sugestaoRepository.findById(sugestaoDTO.sugestaoId());

        if (sugestaoOptional.isPresent()) {
            Sugestao sugestao = new Sugestao();
            BeanUtils.copyProperties(sugestaoDTO, sugestao);
            return new SugestaoExibicaoDTO(sugestaoRepository.save(sugestao));
        } else {
            throw new RuntimeException("Sugestão não encontrada!");
        }
    }
}
