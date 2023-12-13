package br.com.ada.IFome.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VeiculoValidator {

    private static final int ANO_MODELO_MINIMO = 2010;

    private static final String PADRAO_RENAVAM = "\\d{11}";
    private static final String PADRAO_PLACA = "^[A-Z]{3}\\d{1}[A-Z0-9]{1}\\d{2}$";

    public static boolean validarDataModelo(int anoModelo) {
        return anoModelo >= ANO_MODELO_MINIMO;
    }

    public static boolean validarPlaca(String placa) {
        Pattern pattern = Pattern.compile(PADRAO_PLACA);
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
    }

    public static boolean validarRenavam(String renavam) {
        Pattern pattern = Pattern.compile(PADRAO_RENAVAM);
        Matcher matcher = pattern.matcher(renavam);
        if (!matcher.matches()) {
            return false;
        }

        return true;
    }
}
