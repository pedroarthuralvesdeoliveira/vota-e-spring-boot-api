package br.com.fiap.vota_e.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tbl_sugestoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sugestao {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SUGESTOES"
    )
    @SequenceGenerator(
            name = "SEQ_SUGESTOES",
            sequenceName = "SEQ_SUGESTOES",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;
    private String descricao;
    private String observacao;
    private String localizacao;
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(
            name = "USUARIO_ID",
            referencedColumnName = "ID"
    )
    private Usuario usuario;
}
