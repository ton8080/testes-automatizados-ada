package br.com.ada.f1rst.service;

import br.com.ada.f1rst.entity.ContaBancaria;
import br.com.ada.f1rst.entity.Entregador;
import br.com.ada.f1rst.entity.Veiculo;
import br.com.ada.f1rst.enums.DocumentoType;
import br.com.ada.f1rst.exceptions.*;
import br.com.ada.f1rst.repository.EntregadorRepository;
import br.com.ada.f1rst.validation.ContaBancariaValidator;
import br.com.ada.f1rst.validation.DataValidator;
import br.com.ada.f1rst.validation.DocumentoValidator;
import br.com.ada.f1rst.validation.VeiculoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntregadorService {

    private final EntregadorRepository entregadorRepository;
    private final VeiculoService veiculoService;
    private final ContaBancariaService contaBancariaService;
    private final DocumentoService documentoService;

    public Entregador salvar(Entregador entregador) {
        if (entregador == null) {
            throw new EntregadorInvalidoException();
        }
        documentoService.validarDocumento(entregador.getDocumento());
        veiculoService.validarVeiculo(entregador.getVeiculo());
        contaBancariaService.validarContaBancaria(entregador.getContaBancaria());

        return entregadorRepository.save(entregador);
    }




}
