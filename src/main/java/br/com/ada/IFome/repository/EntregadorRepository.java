package br.com.ada.IFome.repository;

import br.com.ada.IFome.entity.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
}
