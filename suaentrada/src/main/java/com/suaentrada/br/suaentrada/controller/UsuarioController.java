package com.suaentrada.br.suaentrada.controller;

import com.suaentrada.br.suaentrada.dto.UsuarioDto;
import com.suaentrada.br.suaentrada.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvar(@Valid @RequestBody UsuarioDto usuarioDto){
        UsuarioDto salvo = usuarioService.salvar(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{codigoUsuario}")
    public ResponseEntity<UsuarioDto> buscar(@PathVariable Long codigoUsuario){
        try {
            UsuarioDto usuario = usuarioService.buscarPorId(codigoUsuario);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigoUsuario}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigoUsuario){
        try {
            usuarioService.deletar(codigoUsuario);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

