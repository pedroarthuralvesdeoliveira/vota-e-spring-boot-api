package br.com.fiap.vota_e.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Long sugestao_id;
    private String descricao;
    private String observacao;
    private String localizacao;

    @ManyToOne
    @JoinColumn(
            name = "usuario_id",
            referencedColumnName = "id"
    )
    private Usuario usuario;
}
