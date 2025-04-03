package br.com.edilsonmaria.medicalconsult.user.domain;

import br.com.edilsonmaria.medicalconsult.consulta.domain.Consulta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;

    @Column(name = "USER_NOME")
    private String nomeUser;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "DATA_NASCIMENTO")
    private String dataNascimento;

    @Column(name = "PERMISSAO")
    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    public User() {
    }

    public User(Long idUser, String nomeUser, String email, String cpf, String telefone, String dataNascimento, Permissao permissao, List<Consulta> consultas) {
        this.idUser = idUser;
        this.nomeUser = nomeUser;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.permissao = permissao;
        this.consultas = consultas;
    }

}
