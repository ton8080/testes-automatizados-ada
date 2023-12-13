package br.com.ada.IFome.repository;

import br.com.ada.IFome.entity.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
}

