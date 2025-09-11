package com.suaentrada.br.suaentrada.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDto (

        Long codigoUsuario,

        @NotNull(message = "Nome precisa ser preenchido")
         String nomeUsuario,

        @NotNull(message = "Email precisa ser preenchido")
        @Email
        String emailUsuario,

        @NotNull(message = "É necessario incluir sua senha")
        @Size(min = 6, max = 100, message = "A senha precisa ser maior que 6 digitos")
        String senhaUsuario
) {



}
