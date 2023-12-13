package br.com.ada.IFome.validation;

public class DocumentoValidator {


    public static boolean validarRG(String rg) {
        rg = rg.replaceAll("[^0-9]", "");
        return rg.length() == 7;
    }

    public static boolean validarCNH(String cnh) {
        cnh = cnh.replaceAll("[^0-9]", "");
        return cnh.length() == 11;
    }

    public static boolean validaCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.length() == 11;
    }
}
