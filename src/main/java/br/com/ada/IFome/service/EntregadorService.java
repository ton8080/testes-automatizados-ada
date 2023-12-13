package br.com.ada.IFome.service;

import br.com.ada.IFome.entity.Entregador;
import br.com.ada.IFome.exceptions.*;
import br.com.ada.IFome.repository.EntregadorRepository;
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


    public void remover(Long id) {
        if (!entregadorRepository.existsById(id)) {
            throw new EntregadorNaoEncontradoException();
        }
        entregadorRepository.deleteById(id);
    }
}
