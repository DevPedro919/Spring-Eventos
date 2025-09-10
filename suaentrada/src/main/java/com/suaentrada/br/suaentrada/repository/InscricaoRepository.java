package com.suaentrada.br.suaentrada.repository;

import com.suaentrada.br.suaentrada.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao,Long> {
    Optional<Inscricao> findById(Long codigoInscricao);
    Optional<Inscricao> findAllById (Long codigoInscricao);
}
