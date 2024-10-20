package br.com.fiap.vota_e.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tbl_projetos")
@Data
public class Projeto {
    @Id
    private Long id;
    private String descricao;
    private String titulo;
    private String status;
    private Date dataCadastro;
    private Date dataEnvio;
    private Date dataAprovacao;

    @OneToOne
    @JoinColumn(
            referencedColumnName = "sugestao_id"
    )
    private Sugestao sugestao;
}
