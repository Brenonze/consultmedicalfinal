package br.com.brenofranco.medicalconsult.usuario.domain;

import br.com.brenofranco.medicalconsult.consulta.domain.Consulta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private long idUsuario;
    @Column(name = "NOME_USUARIO")
    private String nomeUsuario;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;
    @Column(name = "PERMISSAO")
    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
}
