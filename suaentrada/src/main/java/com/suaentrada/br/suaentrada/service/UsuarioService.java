package com.suaentrada.br.suaentrada.service;

import com.suaentrada.br.suaentrada.model.Usuario;
import com.suaentrada.br.suaentrada.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long codigoUsuario){
        return usuarioRepository.findByCodigoUsuario(codigoUsuario).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public void deletar(Long codigoUsuario){
        usuarioRepository.delete(buscarPorId(codigoUsuario));
    }
}
