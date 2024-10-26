package br.com.fiap.vota_e.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tbl_projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Projeto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PROJETOS"
    )
    @SequenceGenerator(
            name = "SEQ_PROJETOS",
            sequenceName = "SEQ_PROJETOS",
            allocationSize = 1
    )
    @Column(name = "projeto_id")
    private Long id;
    private String descricao;
    private String titulo;
    private String status;
    private Date dataCadastro;
    private Date dataEnvio;
    private Date dataAprovacao;

    @OneToOne
    @JoinColumn(
            name = "sugestao_id",
            referencedColumnName = "sugestao_id"
    )
    private Sugestao sugestao;
}
