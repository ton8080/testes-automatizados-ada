package br.com.ada.IFome.controller;

import br.com.ada.IFome.entity.Documento;
import br.com.ada.IFome.entity.Entregador;
import br.com.ada.IFome.enums.DocumentoType;
import br.com.ada.IFome.service.EntregadorServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;

class IFomeControllerTest {

    @InjectMocks
    private IFomeController ifomeController;

    @Mock
    private EntregadorServiceTest entregadorServiceTest;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ifomeController).build();
    }

    @Test
    void cadastrarEntregador() throws Exception {
        Entregador entregador = new Entregador();
        Documento documento = new Documento();
        documento.setNumeroDocumento("05566644411");
        documento.setDocumentoType(DocumentoType.CPF);

        entregador.setDocumento(documento);
        entregador.setNome("Washington");

        Mockito.when(entregadorServiceTest.salvar(any())).thenReturn(entregador);

        mockMvc.perform(MockMvcRequestBuilders.post("/entregadores/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(entregador)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
