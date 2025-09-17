package com.suaentrada.br.suaentrada.service;

import com.suaentrada.br.suaentrada.dto.UsuarioDto;
import com.suaentrada.br.suaentrada.dto.mapper.UsuarioMapper;
import com.suaentrada.br.suaentrada.model.Role;
import com.suaentrada.br.suaentrada.model.UsuarioModel;
import com.suaentrada.br.suaentrada.repository.RoleRepository;
import com.suaentrada.br.suaentrada.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        UsuarioDto usuarioSenhaCriptografada = new UsuarioDto(
                usuarioDto.codigoUsuario(),
                usuarioDto.nomeUsuario(),
                usuarioDto.emailUsuario(),
                passwordEncoder.encode(usuarioDto.senhaUsuario()),
                usuarioDto.roles()
        );

        UsuarioModel usuario = UsuarioMapper.toEntity(usuarioSenhaCriptografada);

        if (usuarioDto.roles() != null && !usuarioDto.roles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (String roleName : usuarioDto.roles()) {
                Role role = roleRepository.findByNomeRole(roleName)
                        .orElseThrow(() -> new RuntimeException("Role não encontrada: " + roleName));
                roles.add(role);
            }
            usuario.setRoles(roles);
        } else {
            // Se não especificar roles, dar role padrão de USER
            Role roleUser = roleRepository.findByNomeRole("USER")
                    .orElseThrow(() -> new RuntimeException("Role USER não encontrada."));
            usuario.getRoles().add(roleUser);
        }

        UsuarioModel salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(salvo);
    }

    public List<UsuarioDto> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDto)
                .toList();
    }

    public UsuarioDto buscarPorId(Long codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario)
                .map(UsuarioMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public void deletar(Long codigoUsuario) {
        if (!usuarioRepository.existsById(codigoUsuario)) {
            throw new RuntimeException("Usuario não encontrado");
        }
        usuarioRepository.deleteById(codigoUsuario);
    }
}
