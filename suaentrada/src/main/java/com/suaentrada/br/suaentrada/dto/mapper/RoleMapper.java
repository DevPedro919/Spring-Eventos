package com.suaentrada.br.suaentrada.dto.mapper;

import com.suaentrada.br.suaentrada.dto.RoleDto;
import com.suaentrada.br.suaentrada.model.Role;

public class RoleMapper {

    public static RoleDto toDto(Role role) {
        if (role == null) return null;
        return new RoleDto(role.getCodigoRole(), role.getNomeRole());
    }

    public static Role toEntity(RoleDto dto) {
        if (dto == null) return null;
        Role role = new Role();
        role.setCodigoRole(dto.id());
        role.setNomeRole(dto.nome());
        return role;
    }
}