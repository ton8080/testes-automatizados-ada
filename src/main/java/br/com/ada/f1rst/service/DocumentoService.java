package br.com.ada.f1rst.service;

import br.com.ada.f1rst.entity.ContaBancaria;
import br.com.ada.f1rst.entity.Documento;
import br.com.ada.f1rst.entity.Entregador;
import br.com.ada.f1rst.enums.DocumentoType;
import br.com.ada.f1rst.exceptions.*;
import br.com.ada.f1rst.repository.ContaBancariaRepository;
import br.com.ada.f1rst.repository.DocumentoRepository;
import br.com.ada.f1rst.validation.DataValidator;
import br.com.ada.f1rst.validation.DocumentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public Documento cadastrarDocumento(Documento documento) {
        validarDocumento(documento);
        return documentoRepository.save(documento);
    }

    public static void validarDocumento(Documento documento) {

        DocumentoType tipoDocumentoType = documento.getDocumentoType();
        String numeroDocumento = documento.getNumeroDocumento();

        switch (tipoDocumentoType) {
            case CPF:
                if (!DocumentoValidator.validaCpf(numeroDocumento)) {
                    throw new CpfInvalidoException();
                }
                break;
            case RG:
                if (!DocumentoValidator.validarRG(numeroDocumento)) {
                    throw new RgInvalidoException();
                }
                break;
            case CNH:
                if (!DocumentoValidator.validarCNH(numeroDocumento)) {
                    throw new CnhInvalidaException();
                }
                DataValidator.validarDataVencimentoCNH(documento.getValidadeDocumento());
                break;
            default:
                throw new TipoDocumentoInvalidoException();
        }
    }
}
