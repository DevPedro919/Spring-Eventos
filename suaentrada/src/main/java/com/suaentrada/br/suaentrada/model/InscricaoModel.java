package com.suaentrada.br.suaentrada.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBINSCRICAO")
public class InscricaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDINSCRICAO")
    private Long codigoInscricao;

    @Column(name = "DTINSCRICAO")
    private LocalDate dataInscricao;

    @Column(name = "HRINSCRICAO")
    private LocalTime horaInscricao;

    @ManyToOne
    @JoinColumn(name = "CDUSUARIO")
    private UsuarioModel usuarioModel;

    @ManyToOne
    @JoinColumn(name = "CDEVENTO")
    private EventoModel eventoModel;

}