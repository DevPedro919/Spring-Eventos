package com.suaentrada.br.suaentrada.repository;

import com.suaentrada.br.suaentrada.model.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EventoRepository extends JpaRepository<EventoModel,Long> {
    Optional<EventoModel> findByCodigoEvento(Long cdEvento);
    Optional<EventoModel> findAllByCodigoEvento(Long cdEvento);

}
