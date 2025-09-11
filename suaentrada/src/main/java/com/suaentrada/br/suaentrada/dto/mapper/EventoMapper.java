package com.suaentrada.br.suaentrada.dto.mapper;

import com.suaentrada.br.suaentrada.dto.EventoDto;
import com.suaentrada.br.suaentrada.model.Evento;

public class EventoMapper {

    public static EventoDto toEventoDto(Evento evento){
        if(evento == null) return null;
        return new EventoDto(
                evento.getTituloEvento(),
                evento.getDescricaoEvento(),
                evento.getDataEvento(),
                evento.getHoraEvento(),
                evento.getLocalEvento(),
                evento.getVagasTotaisEvento(),
                evento.getVagasDisponiveisEvento()
        );
    }

        

    }

