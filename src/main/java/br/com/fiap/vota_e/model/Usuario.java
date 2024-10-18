package br.com.fiap.vota_e.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tbl_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USUARIOS_SEQ"
    )
    @SequenceGenerator(
            name = "USUARIOS_SEQ",
            sequenceName = "USUARIOS_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String telefone;

    @OneToMany(mappedBy = "usuario")
    private List<Sugestao> sugestoes;
}
