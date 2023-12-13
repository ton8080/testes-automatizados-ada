package br.com.ada.IFome.service;

import br.com.ada.IFome.entity.Documento;
import br.com.ada.IFome.enums.DocumentoType;
import br.com.ada.IFome.exceptions.*;
import br.com.ada.IFome.repository.DocumentoRepository;
import br.com.ada.IFome.validation.DataValidator;
import br.com.ada.IFome.validation.DocumentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

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
