package com.suaentrada.br.suaentrada.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBUSUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDUSUARIO")
    private Long codigoUsuario ;

    @Column(name = "NMUSUARIO")
    private String nomeUsuario;

    @Column(name = "NMEMAIL")
    private String emailUsuario;

    @Column(name = "NMSENHA")
    private String senhaUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();

}


