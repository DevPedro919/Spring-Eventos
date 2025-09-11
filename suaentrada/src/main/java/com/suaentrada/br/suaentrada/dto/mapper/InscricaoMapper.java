package com.suaentrada.br.suaentrada.dto.mapper;

import com.suaentrada.br.suaentrada.dto.InscricaoDto;
import com.suaentrada.br.suaentrada.model.EventoModel;
import com.suaentrada.br.suaentrada.model.InscricaoModel;
import com.suaentrada.br.suaentrada.model.UsuarioModel;

public class InscricaoMapper {

    public static InscricaoDto toDto(InscricaoModel inscricao) {
        if (inscricao == null) return null;
        return new InscricaoDto(
                inscricao.getCodigoInscricao(),
                inscricao.getDataInscricao(),
                inscricao.getHoraInscricao(),
                inscricao.getUsuarioModel() != null ? inscricao.getUsuarioModel().getCodigoUsuario() : null,
                inscricao.getEventoModel() != null ? inscricao.getEventoModel().getCodigoEvento() : null
        );
    }

    public static InscricaoModel toEntity(InscricaoDto dto) {
        if (dto == null) return null;
        InscricaoModel inscricao = new InscricaoModel();
        inscricao.setCodigoInscricao(dto.codigoInscricao());
        inscricao.setDataInscricao(dto.dataInscricao());
        inscricao.setHoraInscricao(dto.horaInscricao());

        if (dto.codigoUsuario() != null) {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setCodigoUsuario(dto.codigoUsuario());
            inscricao.setUsuarioModel(usuario);
        }

        if (dto.codigoEvento() != null) {
            EventoModel evento = new EventoModel();
            evento.setCodigoEvento(dto.codigoEvento());
            inscricao.setEventoModel(evento);
        }

        return inscricao;
    }

}