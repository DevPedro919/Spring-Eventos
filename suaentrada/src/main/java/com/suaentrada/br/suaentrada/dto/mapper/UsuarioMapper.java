package com.suaentrada.br.suaentrada.dto.mapper;

import com.suaentrada.br.suaentrada.dto.UsuarioDto;
import com.suaentrada.br.suaentrada.model.Role;
import com.suaentrada.br.suaentrada.model.UsuarioModel;

import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioDto toDto(UsuarioModel usuario) {
        if (usuario == null) return null;

        return new UsuarioDto(
                usuario.getCodigoUsuario(),
                usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getSenhaUsuario(),
                usuario.getRoles().stream()
                        .map(Role::getNomeRole)
                        .collect(Collectors.toList())
        );
    }

    public static UsuarioModel toEntity(UsuarioDto dto) {
        if (dto == null) return null;

        UsuarioModel usuario = new UsuarioModel();
        usuario.setCodigoUsuario(dto.codigoUsuario());
        usuario.setNomeUsuario(dto.nomeUsuario());
        usuario.setEmailUsuario(dto.emailUsuario());
        usuario.setSenhaUsuario(dto.senhaUsuario());
        return usuario;
    }
}