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

        if (evento.getVagasDisponiveisEvento() == 0) {
            evento.setVagasDisponiveisEvento(evento.getVagasTotaisEvento());
        }

        EventoModel salvo = repository.save(evento);
        return EventoMapper.toDto(salvo);
    }

    public EventoDto atualizar(Long id, EventoDto dto) {
        EventoModel eventoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        eventoExistente.setTituloEvento(dto.tituloEvento());
        eventoExistente.setDescricaoEvento(dto.descricaoEvento());
        eventoExistente.setDataEvento(dto.dataEvento());
        eventoExistente.setHoraEvento(dto.horaEvento());
        eventoExistente.setLocalEvento(dto.localEvento());
        eventoExistente.setVagasTotaisEvento(dto.vagasTotaisEvento());

        if (dto.vagasDisponiveisEvento() != null) {
            eventoExistente.setVagasDisponiveisEvento(dto.vagasDisponiveisEvento());
        }

        EventoModel atualizado = repository.save(eventoExistente);
        return EventoMapper.toDto(atualizado);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado");
        }
        repository.deleteById(id);
    }

    public EventoDto buscarPorId(Long id) {
        return repository.findById(id)
                .map(EventoMapper::toDto)
                .orElse(null);
    }

    public List<EventoDto> buscarTodos() {
        return repository.findAll().stream()
                .map(EventoMapper::toDto)
                .toList();
    }
}
