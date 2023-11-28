package br.com.ada.f1rst.repository;

import br.com.ada.f1rst.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
