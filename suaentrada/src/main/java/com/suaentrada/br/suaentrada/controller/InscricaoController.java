package com.suaentrada.br.suaentrada.controller;

import com.suaentrada.br.suaentrada.dto.InscricaoDto;
import com.suaentrada.br.suaentrada.service.InscricaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
@RequiredArgsConstructor
public class InscricaoController {

    private final InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<InscricaoDto> criar(@Valid @RequestBody InscricaoDto dto) {
        try {
            InscricaoDto salva = inscricaoService.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<InscricaoDto>> buscarTodas() {
        return ResponseEntity.ok(inscricaoService.buscarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscricaoDto> buscarPorId(@PathVariable Long id) {
        try {
            InscricaoDto inscricao = inscricaoService.buscarPorId(id);
            return ResponseEntity.ok(inscricao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            inscricaoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
