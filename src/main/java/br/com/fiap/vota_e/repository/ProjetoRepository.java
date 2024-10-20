package br.com.fiap.vota_e.repository;

import br.com.fiap.vota_e.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
