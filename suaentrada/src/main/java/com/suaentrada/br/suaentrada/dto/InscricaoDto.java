package com.suaentrada.br.suaentrada.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

public record InscricaoDto (

        @CreationTimestamp
        LocalDate datainscricao,

        @CreationTimestamp
        LocalTime horaInscricao


) {

}
