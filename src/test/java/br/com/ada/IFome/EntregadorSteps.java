package br.com.ada.IFome;

import br.com.ada.IFome.entity.Documento;
import br.com.ada.IFome.entity.Entregador;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;

public class EntregadorSteps {

    private RequestSpecification request;
    private ValidatableResponse response;
    private static final String BASE_URL = "http://localhost:8080";

    @Given("o endpoint {string} do tipo {string}")
    public void setEndpoint(String endpoint, String tipo) {
        RestAssured.baseURI = BASE_URL.concat(endpoint);
        request = RestAssured.given();
    }

    @When("eu enviar uma requisição para salvar um novo entregador com o documento {string} e a validade da CNH {string}")
    public void enviarRequest(String documento, String validadeCNH) {
        var entregador = this.criarEntregador(documento, validadeCNH);
        response = request.body(asJsonString(entregador)).contentType(ContentType.JSON).post().then();
    }

    @When("eu enviar uma requisição para salvar um novo entregador com o documento {string} e a validade da CNH {string} e Nome {string}")
    public void enviarRequestComNome(String documento, String validadeCNH, String nome) {
        var entregador = this.criarEntregador(documento, validadeCNH);
        entregador.setNome(nome);
        response = request.body(asJsonString(entregador)).contentType(ContentType.JSON).post().then();
    }

    @Then("eu recebo uma resposta com código {int}")
    public void compararResultado(Integer codigo) {
        response.assertThat().statusCode(codigo);
    }

    @And("conteudo igual documento {string} e a validade da CNH {string}")
    public void conteudoIgualDocumentoEValidade(String documento, String validadeCNH) {
        response.assertThat().body("documento.numeroDocumento", equalTo(documento));
        response.assertThat().body("documento.validadeDocumento", equalTo(validadeCNH));
    }

    @And("conteudo igual documento {string} e a validade da CNH {string} e o nome {string}")
    public void conteudoIgualDocumentoEValidadeComNome(String documento, String validadeCNH, String nome) {
        response.assertThat().body("documento.numeroDocumento", equalTo(documento));
        response.assertThat().body("documento.validadeDocumento", equalTo(validadeCNH));
        response.assertThat().body("nome", equalTo(nome));
    }

    @And("conteudo com a mensagem {string}")
    public void conteudoComAMensagem(String mensagem) {
        response.assertThat().body(equalTo(mensagem));
    }

    private Entregador criarEntregador(String documento, String validadeCNH) {
        Entregador entregador = new Entregador();
        Documento documentoEntregador = new Documento();
        documentoEntregador.setNumeroDocumento(documento);
        documentoEntregador.setValidadeDocumento(LocalDate.parse(validadeCNH));
        entregador.setDocumento(documentoEntregador);
        entregador.setEmail("washington@mail.com");
        return entregador;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
