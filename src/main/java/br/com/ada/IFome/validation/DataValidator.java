package br.com.ada.IFome.validation;

import br.com.ada.IFome.exceptions.CnhInvalidaException;

import java.time.LocalDate;

public class DataValidator {

    public static void validarDataVencimentoCNH(LocalDate dataVencimentoCNH) {
        if (dataVencimentoCNH == null || dataVencimentoCNH.isBefore(LocalDate.now())) {
            throw new CnhInvalidaException();
        }
    }
}
