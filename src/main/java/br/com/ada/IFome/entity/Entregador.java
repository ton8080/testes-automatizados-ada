package br.com.ada.IFome.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Entregador {

    @Id
    private Long id;
    private String nome;
    @ManyToOne(optional = false)
    @JoinColumn(name = "documento_ID", nullable = false, unique = true)
    private Documento documento;
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    @ManyToOne
    @JoinColumn(name = "conta_bancaria_id")
    private ContaBancaria contaBancaria;



}
