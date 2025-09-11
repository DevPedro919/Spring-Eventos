package com.suaentrada.br.suaentrada.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBEVENTO")
public class EventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDEVENTO")
    private Long codigoEvento;

    @Column(name = "NMTITULO")
    private String tituloEvento;

    @Column(name = "NMDESCRICAO")
    private String descricaoEvento;

    @Column(name = "DTEVENTO")
    private LocalDate dataEvento;

    @Column(name = "HREVENTO")
    private LocalTime horaEvento;

    @Column(name = "NMLOCAL")
    private String localEvento;

    @Column(name = "NUVAGASTOTAIS")
    private int vagasTotaisEvento;

    @Column(name = "NUVAGASDISPONIVEIS")
    private int vagasDisponiveisEvento;

    @OneToMany(mappedBy = "eventoModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InscricaoModel> inscricoes = new ArrayList<>();

}
