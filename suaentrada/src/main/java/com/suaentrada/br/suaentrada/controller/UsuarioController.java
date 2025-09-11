package com.suaentrada.br.suaentrada.controller;

import com.suaentrada.br.suaentrada.model.Usuario;
import com.suaentrada.br.suaentrada.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    UsuarioService usuarioService;

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/{codigoUsuario}")
    public Usuario buscar(@PathVariable Long codigoUsuario){
        return usuarioService.buscarPorId(codigoUsuario);
    }

    @DeleteMapping("/{codigoUsuario")
    public void deletar(@PathVariable Long codigoUsuario){
        usuarioService.deletar(codigoUsuario);
    }
}
