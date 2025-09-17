package com.suaentrada.br.suaentrada.dto;


import jakarta.validation.constraints.NotBlank;


public record RoleDto(

        Long id,

        @NotBlank(message = "Você precisa dar um nome a ROLE")
        String nome

) {

}
