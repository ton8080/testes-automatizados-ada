package br.com.ada.IFome.controller;

import br.com.ada.IFome.entity.Entregador;
import br.com.ada.IFome.service.EntregadorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IFomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EntregadorService entregadorService;

    @InjectMocks
    private IFomeController ifomeController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ifomeController).build();
    }

    @Test
    void cadastrarEntregador() throws Exception {
        Entregador entregador = new Entregador();
        entregador.setNome("Washington");

        Mockito.when(entregadorService.salvar(any())).thenReturn(entregador);

        mockMvc.perform(post("/entregadores/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(entregador)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Washington"));
    }

    @Test
    void removerEntregador() throws Exception {
        Long entregadorId = 1L;

        mockMvc.perform(delete("/entregadores/remover/{id}", entregadorId))
                .andExpect(status().isOk())
                .andExpect(content().string("Entregador removido com sucesso."));

        Mockito.verify(entregadorService, Mockito.times(1)).remover(eq(entregadorId));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
