package br.com.fiap.vota_e.repository;

import br.com.fiap.vota_e.model.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
    List<Sugestao> findByDataCriacaoBetween(Date dataInicial, Date dataFinal);
}
