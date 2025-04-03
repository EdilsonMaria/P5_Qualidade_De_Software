package br.com.edilsonmaria.medicalconsult.consulta.domain;

import br.com.edilsonmaria.medicalconsult.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CONSULTAS")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSULTA")
    private Long idConsulta;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name ="DATA_CONSULTA")
    private Date dataConsulta;

    @Column(name = "PROFISSIONAL")
    private String profissional;

    @Column(name = "ESPECIALIDADE")
    private String especialidade;

    @ManyToOne
    @JoinColumn(name ="ID_USER")
    private User user;

    public Consulta() {
    }

    public Consulta(Long idConsulta, Date dataConsulta, String profissional, String especialidade, User user) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.profissional = profissional;
        this.especialidade = especialidade;
        this.user = user;
    }
}

