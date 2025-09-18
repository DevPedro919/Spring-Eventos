package com.suaentrada.br.suaentrada.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBUSUARIOS")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDUSUARIO")
    private Long codigoUsuario;

    @Column(name = "NMUSUARIO")
    private String nomeUsuario;

    @Column(name = "NMEMAIL", unique = true)
    private String emailUsuario;

    @Column(name = "NMSENHA")
    private String senhaUsuario;

    @OneToMany(mappedBy = "usuarioModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InscricaoModel> inscricoes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TBUSUARIO_ROLES",
            joinColumns = @JoinColumn(name = "CDUSUARIO"),
            inverseJoinColumns = @JoinColumn(name = "CDROLE"))
    private Set<Role> roles = new HashSet<>();
}