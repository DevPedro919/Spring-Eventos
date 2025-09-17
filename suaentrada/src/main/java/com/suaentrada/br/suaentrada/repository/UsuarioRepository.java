package com.suaentrada.br.suaentrada.repository;

import com.suaentrada.br.suaentrada.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByCodigoUsuario(Long codigoUsuario);
    Optional<UsuarioModel> findAllByCodigoUsuario(Long codigoUsuario);
    Optional<UsuarioModel> findByEmailUsuario(String emailUsuario);
    boolean existsByEmailUsuario(String email);
}
