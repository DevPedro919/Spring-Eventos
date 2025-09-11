package com.suaentrada.br.suaentrada.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

public record InscricaoDto(

        Long codigoInscricao,

        @CreationTimestamp
        LocalDate dataInscricao,

        @CreationTimestamp
        LocalTime horaInscricao,

        @NotNull(message = "O usuário é obrigatório")
        Long codigoUsuario,

        @NotNull(message = "O evento é obrigatório")
        Long codigoEvento
) {
}