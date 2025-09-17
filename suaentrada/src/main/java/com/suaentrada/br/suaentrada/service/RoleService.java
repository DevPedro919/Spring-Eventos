package com.suaentrada.br.suaentrada.service;

import com.suaentrada.br.suaentrada.dto.RoleDto;
import com.suaentrada.br.suaentrada.dto.mapper.RoleMapper;
import com.suaentrada.br.suaentrada.model.Role;
import com.suaentrada.br.suaentrada.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleDto salvar(RoleDto roleDto) {
        Role role = RoleMapper.toEntity(roleDto);
        Role salva = roleRepository.save(role);
        return RoleMapper.toDto(salva);
    }

    public List<RoleDto> listarTodas() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toDto)
                .toList();
    }

    public RoleDto buscarPorId(Long id) {
        return roleRepository.findById(id)
                .map(RoleMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));
    }

    public void deletar(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role não encontrada");
        }
        roleRepository.deleteById(id);
    }
}