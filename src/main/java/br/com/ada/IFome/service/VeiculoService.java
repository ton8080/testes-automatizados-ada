package br.com.ada.IFome.service;

import br.com.ada.IFome.entity.Veiculo;
import br.com.ada.IFome.exceptions.AnoModeloInvalidoException;
import br.com.ada.IFome.exceptions.PlacaInvalidaException;
import br.com.ada.IFome.exceptions.RenavamInvalidoException;
import br.com.ada.IFome.exceptions.VeiculoInvalidoException;
import br.com.ada.IFome.repository.VeiculoRepository;
import br.com.ada.IFome.validation.VeiculoValidator;
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


    public void validarVeiculo(Veiculo veiculo) {
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
