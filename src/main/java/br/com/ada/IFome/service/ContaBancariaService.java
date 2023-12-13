package br.com.ada.IFome.service;

import br.com.ada.IFome.entity.ContaBancaria;
import br.com.ada.IFome.exceptions.*;
import br.com.ada.IFome.repository.ContaBancariaRepository;
import br.com.ada.IFome.validation.ContaBancariaValidator;
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
