package com.suaentrada.br.suaentrada.repository;

import com.suaentrada.br.suaentrada.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {
    Optional<Evento> findByCodigoEvento(Long codigoEvento);
    Optional<Evento> findAllByCodigoEvento(Long codigoEvento);

}
