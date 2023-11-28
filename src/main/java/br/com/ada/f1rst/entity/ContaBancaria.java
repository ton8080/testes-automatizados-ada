package br.com.ada.f1rst.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ContaBancaria {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String numeroAgencia;
    private String numeroConta;
    private String tipoConta;
    private String instituicaoBancaria;
}
