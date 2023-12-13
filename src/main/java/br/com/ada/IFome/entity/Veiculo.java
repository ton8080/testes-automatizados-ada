package br.com.ada.IFome.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Veiculo {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer anoModelo;
    private String placa;
    private String renavam;

}
