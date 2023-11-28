package br.com.ada.f1rst.validation;

import br.com.ada.f1rst.exceptions.CnhInvalidaException;

import java.time.LocalDate;

public class DataValidator {

    public static void validarDataVencimentoCNH(LocalDate dataVencimentoCNH) {
        if (dataVencimentoCNH == null || dataVencimentoCNH.isBefore(LocalDate.now())) {
            throw new CnhInvalidaException();
        }
    }
}
