package br.com.ada.IFome.entity;

import br.com.ada.IFome.enums.DocumentoType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    private DocumentoType documentoType;

    @Column(name = "numero_documento", nullable = false, unique = true)
    private String numeroDocumento;

    @Column(name = "validade_documento", nullable = false, unique = true)
    private LocalDate validadeDocumento;


}