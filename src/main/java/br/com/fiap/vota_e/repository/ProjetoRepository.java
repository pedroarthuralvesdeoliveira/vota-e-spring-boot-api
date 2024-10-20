package br.com.fiap.vota_e.repository;

import br.com.fiap.vota_e.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByDataCadastroBetween(Date dataInicial, Date dataFinal);
}
