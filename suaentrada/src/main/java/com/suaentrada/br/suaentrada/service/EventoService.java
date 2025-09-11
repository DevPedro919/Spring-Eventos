package com.suaentrada.br.suaentrada.service;

import com.suaentrada.br.suaentrada.dto.EventoDto;
import com.suaentrada.br.suaentrada.dto.mapper.EventoMapper;
import com.suaentrada.br.suaentrada.model.EventoModel;
import com.suaentrada.br.suaentrada.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository repository;

    public EventoDto salvar(EventoDto dto) {
        EventoModel evento = EventoMapper.toEntity(dto);
        EventoModel salvo = repository.save(evento);
        return EventoMapper.toDto(salvo);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public EventoDto buscarPorId(Long id) {
        return repository.findById(id).map(EventoMapper::toDto).orElse(null);
    }

    public List<EventoDto> buscarTodos() {
        return repository.findAll().stream().map(EventoMapper::toDto).toList();
    }



}