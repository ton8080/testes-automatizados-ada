package br.com.ada.f1rst.repository;

import br.com.ada.f1rst.entity.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
}
