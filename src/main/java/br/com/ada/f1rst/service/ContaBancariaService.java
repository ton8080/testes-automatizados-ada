package br.com.ada.f1rst.service;

import br.com.ada.f1rst.entity.ContaBancaria;
import br.com.ada.f1rst.exceptions.*;
import br.com.ada.f1rst.repository.ContaBancariaRepository;
import br.com.ada.f1rst.validation.ContaBancariaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;

    public ContaBancaria cadastrarContaBancaria(ContaBancaria contaBancaria) {
        validarContaBancaria(contaBancaria);
        return contaBancariaRepository.save(contaBancaria);
    }

    public void validarContaBancaria(ContaBancaria contaBancaria) {
        if (contaBancaria == null) {
            throw new ContaBancariaInvalidaException();
        }

        if (!ContaBancariaValidator.validarNumeroAgencia(contaBancaria.getNumeroAgencia())) {
            throw new NumeroAgenciaInvalidoException();
        }

        if (!ContaBancariaValidator.validarNumeroConta(contaBancaria.getNumeroConta())) {
            throw new NumeroContaInvalidoException();
        }

        if (!ContaBancariaValidator.validarTipoConta(contaBancaria.getTipoConta())) {
            throw new TipoContaInvalidoException();
        }

        if (!ContaBancariaValidator.validarInstituicaoBancaria(contaBancaria.getInstituicaoBancaria())) {
            throw new InstituicaoBancariaInvalidaException();
        }
    }
}
