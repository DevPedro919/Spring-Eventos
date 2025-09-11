package com.suaentrada.br.suaentrada.dto.mapper;

import com.suaentrada.br.suaentrada.dto.EventoDto;
import com.suaentrada.br.suaentrada.model.EventoModel;

public class EventoMapper {

    public static EventoDto toDto(EventoModel evento) {
        if (evento == null) return null;
        return new EventoDto(
                evento.getCodigoEvento(),
                evento.getTituloEvento(),
                evento.getDescricaoEvento(),
                evento.getDataEvento(),
                evento.getHoraEvento(),
                evento.getLocalEvento(),
                evento.getVagasTotaisEvento(),
                evento.getVagasDisponiveisEvento()
        );
    }

    public static EventoModel toEntity(EventoDto dto) {
        if (dto == null) return null;
        EventoModel evento = new EventoModel();
        evento.setTituloEvento(dto.tituloEvento());
        evento.setDescricaoEvento(dto.descricaoEvento());
        evento.setDataEvento(dto.dataEvento());
        evento.setHoraEvento(dto.horaEvento());
        evento.setLocalEvento(dto.localEvento());
        evento.setVagasTotaisEvento(dto.vagasTotaisEvento());
        evento.setVagasDisponiveisEvento(dto.vagasDisponiveisEvento());
        return evento;
    }

}