package com.suaentrada.br.suaentrada.repository;

import com.suaentrada.br.suaentrada.model.InscricaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InscricaoRepository extends JpaRepository<InscricaoModel,Long> {
    Optional<InscricaoModel> findByCodigoInscricao(Long codigoInscricao);
    Optional<InscricaoModel> findAllByCodigoInscricao(Long codigoInscricao);



}
