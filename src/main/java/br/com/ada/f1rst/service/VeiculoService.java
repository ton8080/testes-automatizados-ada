package br.com.ada.f1rst.service;

import br.com.ada.f1rst.entity.Veiculo;
import br.com.ada.f1rst.exceptions.AnoModeloInvalidoException;
import br.com.ada.f1rst.exceptions.PlacaInvalidaException;
import br.com.ada.f1rst.exceptions.RenavamInvalidoException;
import br.com.ada.f1rst.exceptions.VeiculoInvalidoException;
import br.com.ada.f1rst.repository.EntregadorRepository;
import br.com.ada.f1rst.repository.VeiculoRepository;
import br.com.ada.f1rst.validation.VeiculoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoService {


    private final VeiculoRepository veiculoRepository;

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        validarVeiculo(veiculo);
        return veiculoRepository.save(veiculo);
    }

    private void validarVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new VeiculoInvalidoException();
        }

        if (!VeiculoValidator.validarDataModelo(veiculo.getAnoModelo())) {
            throw new AnoModeloInvalidoException();
        }

        if (!VeiculoValidator.validarPlaca(veiculo.getPlaca())) {
            throw new PlacaInvalidaException();
        }

        if (!VeiculoValidator.validarRenavam(veiculo.getRenavam())) {
            throw new RenavamInvalidoException();
        }
    }
}
