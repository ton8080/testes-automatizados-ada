package br.com.ada.IFome.service;

import br.com.ada.IFome.entity.ContaBancaria;
import br.com.ada.IFome.entity.Documento;
import br.com.ada.IFome.entity.Entregador;
import br.com.ada.IFome.entity.Veiculo;
import br.com.ada.IFome.enums.DocumentoType;
import br.com.ada.IFome.exceptions.*;
import br.com.ada.IFome.repository.EntregadorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntregadorServiceTest {

    @Mock
    private EntregadorRepository entregadorRepository;

    @Mock
    private VeiculoService veiculoService;

    @Mock
    private ContaBancariaService contaBancariaService;

    @Mock
    private DocumentoService documentoService;

    @InjectMocks
    private EntregadorService entregadorService;

    @Test
    public void testaEntregadorNull() {
        assertThrows(EntregadorInvalidoException.class, () -> entregadorService.salvar(null));
    }

    @Test
    public void entregadorComDocumentoInvalido() {
        var entregador = new Entregador();
        entregador.setDocumento(new Documento());
        assertThrows(TipoDocumentoInvalidoException.class, () -> entregadorService.salvar(entregador));
    }

    @Test
    public void entregadorComVeiculoInvalido() {
        var entregador = criarEntregadorValido();
        doNothing().when(documentoService).validarDocumento(any());
        doNothing().when(veiculoService).validarVeiculo(any());
        assertThrows(VeiculoInvalidoException.class, () -> entregadorService.salvar(entregador));
    }


    @Test
    public void entregadorComContaBancariaInvalida() {
        var entregador = criarEntregadorValido();
        doNothing().when(documentoService).validarDocumento(any());
        doNothing().when(veiculoService).validarVeiculo(any());
        doThrow(ContaBancariaInvalidaException.class).when(contaBancariaService).validarContaBancaria(any());
        assertThrows(ContaBancariaInvalidaException.class, () -> entregadorService.salvar(entregador));
    }


    @Test
    public void entregadorValido() {
        var entregador = criarEntregadorValido();
        doNothing().when(documentoService).validarDocumento(any());
        doNothing().when(veiculoService).validarVeiculo(any());
        doNothing().when(contaBancariaService).validarContaBancaria(any());
        when(entregadorRepository.save(any())).thenReturn(entregador);

        var entregadorSalvo = entregadorService.salvar(entregador);

        assertNotNull(entregadorSalvo);
        assertEquals(entregador, entregadorSalvo);
    }

    private Entregador criarEntregadorValido() {
        Entregador entregador = new Entregador();
        Documento documento = new Documento();
        documento.setDocumentoType(DocumentoType.CPF);
        documento.setNumeroDocumento("12345678910");
        documento.setValidadeDocumento(LocalDate.now().plusYears(1));
        entregador.setDocumento(documento);
        Veiculo veiculo = new Veiculo();
        veiculo.setAnoModelo(2010);
        veiculo.setPlaca("ABC1234");
        veiculo.setRenavam("123456789");
        entregador.setVeiculo(veiculo);

        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setTipoConta("CORRENTE");
        contaBancaria.setNumeroConta("456");
        contaBancaria.setInstituicaoBancaria("Banco A");
        contaBancaria.setNumeroAgencia("123");
        entregador.setContaBancaria(contaBancaria);

        entregador.setEmail("washington@mail.com");

        return entregador;
    }

}
