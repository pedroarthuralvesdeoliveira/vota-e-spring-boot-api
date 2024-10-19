package br.com.fiap.vota_e.repository;

import br.com.fiap.vota_e.model.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
}
