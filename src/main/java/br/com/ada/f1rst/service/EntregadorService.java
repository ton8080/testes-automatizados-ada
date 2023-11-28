package br.com.ada.f1rst.service;

import br.com.ada.f1rst.entity.Entregador;
import br.com.ada.f1rst.enums.DocumentoType;
import br.com.ada.f1rst.exceptions.*;
import br.com.ada.f1rst.repository.EntregadorRepository;
import br.com.ada.f1rst.validation.DataValidator;
import br.com.ada.f1rst.validation.DocumentoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;

    public Entregador salvar(Entregador entregador) {
        if (entregador == null) {
            throw new EntregadorInvalidoException();
        }

        DocumentoType tipoDocumentoType = entregador.getDocumentoType();
        String numeroDocumento = entregador.getDocumento().getNumeroDocumento();

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
                DataValidator.validarDataVencimentoCNH(entregador.getDocumento().getValidadeDocumento());
                break;
            default:
                throw new TipoDocumentoInvalidoException();
        }

        return entregadorRepository.save(entregador);
    }


}
