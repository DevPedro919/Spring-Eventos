package com.suaentrada.br.suaentrada.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventoDto(

        @NotBlank(message = "Título é obrigatório")
        @Size(min = 3, max = 80, message = "Tamanho do título deve ser entre 3 e 80 caracteres")
        String tituloEvento,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(min = 3, max = 80, message = "Tamanho da descrição deve ser entre 3 e 80 caracteres")
        String descricaoEvento,

        @NotNull(message = "A data do evento precisa ser preenchida")
        @Future(message = "A data do evento precisa ser futura")
        LocalDate dataEvento,

        @NotNull(message = "A hora do evento precisa ser preenchida")
        LocalTime horaEvento,

        @NotBlank(message = "O local do evento precisa ser preenchido")
        String localEvento,

        @NotNull(message = "O campo de vagas totais não pode ficar vazio")
        @Min(value = 100, message = "O número de vagas totais deve ser no mínimo 100")
        Integer vagasTotaisEvento,

        @Min(value = 0, message = "O campo de vagas totais não pode ficar negativo")
        Integer vagasDisponiveisEvento
) {

}
