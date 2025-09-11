package com.suaentrada.br.suaentrada.controller;

import com.suaentrada.br.suaentrada.dto.EventoDto;
import com.suaentrada.br.suaentrada.service.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    @PostMapping
    public ResponseEntity<EventoDto> criar(@Valid @RequestBody EventoDto dto) {
        EventoDto salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDto> buscarPorId(@PathVariable Long id) {
        EventoDto dto = service.buscarPorId(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<EventoDto>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }





}