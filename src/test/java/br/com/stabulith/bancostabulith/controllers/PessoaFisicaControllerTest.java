package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.ConfiguracaoTest;
import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;
import br.com.stabulith.bancostabulith.services.PessoaFisicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaFisicaControllerTest extends ConfiguracaoTest {
    private final static String ENDPOINT = "/cadastro/pessoa-fisica";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaFisicaService pessoaFisicaService;

    private PessoaFisicaDTO pessoaFisica = new PessoaFisicaDTO();
    private List<PessoaFisicaDTO> pessoaFisicaList = new ArrayList<>();

    @BeforeEach
    public void setup() {
       pessoaFisicaList = this.criarPessoaFisicaDTOList();
       pessoaFisica = this.criarPessoaFisicaDTO();
    }

    @Test
    void mustReturnSuccess_WhenListarPessoaFisica() throws Exception {
        MockHttpServletRequestBuilder getMethod = get(ENDPOINT);
        when(pessoaFisicaService.listar()).thenReturn(pessoaFisicaList);
        mockMvc.perform(getMethod)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

    @Test
    void mustReturnSuccess_WhenBuscarPorId() throws Exception {
        UUID id = pessoaFisica.getId();
        String uri = String.format("%s/%s", ENDPOINT, id);

        when(pessoaFisicaService.buscarPorId(id)).thenReturn(pessoaFisica);

        MockHttpServletRequestBuilder getMethod = get(uri);

        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnSuccess_WhenBuscarPorId_ThenNotFound() throws Exception {
        UUID id = pessoaFisica.getId();
        String uri = String.format("%s/%s", ENDPOINT, id);

        pessoaFisica.setId(null);

        MockHttpServletRequestBuilder getMethod = get(uri);

        when(pessoaFisicaService.buscarPorId(id)).thenReturn(pessoaFisica);
        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnSuccess_WhenBuscarPorCPF() throws Exception {
        long cpf = pessoaFisica.getDocumentos().get(0).getNumero();

        MockHttpServletRequestBuilder getMethod = get(ENDPOINT);
        getMethod.queryParam("cpf", String.valueOf(cpf));

        when(pessoaFisicaService.buscarPorCpf(cpf)).thenReturn(pessoaFisica);
        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnSuccess_WhenBuscarPorCPF_WhenNotFound() throws Exception {
        long cpf = pessoaFisica.getDocumentos().get(0).getNumero();

        MockHttpServletRequestBuilder getMethod = get(ENDPOINT);
        getMethod.queryParam("cpf", String.valueOf(cpf));

        pessoaFisica.setId(null);
        when(pessoaFisicaService.buscarPorCpf(cpf)).thenReturn(pessoaFisica);
        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnIsCreated_WhenSalvar() throws Exception {
        String bodyJson = pessoaFisica.toJSON();

        MockHttpServletRequestBuilder postMethod = post(ENDPOINT);
        postMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);


        when(pessoaFisicaService.salvar(any())).thenReturn(pessoaFisica);

        mockMvc
                .perform(postMethod)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void mustReturnInternalError_WhenSalvar() throws Exception {
        String bodyJson = pessoaFisica.toJSON();

        MockHttpServletRequestBuilder postMethod = post(ENDPOINT);
        postMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);


        when(pessoaFisicaService.salvar(any())).thenReturn(null);

        mockMvc
                .perform(postMethod)
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void mustReturnIsCreated_WhenEditar() throws Exception {
        String bodyJson = pessoaFisica.toJSON();

        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder putMethod = put(URI, pessoaFisica.getId().toString());
        putMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaFisicaService.salvar(any())).thenReturn(pessoaFisica);

        mockMvc
                .perform(putMethod)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void mustReturnIsCreated_WhenEditar_WithIdDiff() throws Exception {
        String bodyJson = pessoaFisica.toJSON();

        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder putMethod = put(URI, UUID.randomUUID().toString());
        putMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaFisicaService.salvar(any())).thenReturn(pessoaFisica);

        mockMvc
                .perform(putMethod)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void mustReturnIsAccepted_WhenExcluir() throws Exception {
        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder deleteMethod = delete(URI, pessoaFisica.getId().toString());
        deleteMethod
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaFisicaService.excluir(any())).thenReturn(true);

        mockMvc
                .perform(deleteMethod)
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void mustReturnIsNotFound_WhenExcluir() throws Exception {
        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder deleteMethod = delete(URI, pessoaFisica.getId().toString());
        deleteMethod
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaFisicaService.excluir(any())).thenReturn(false);

        mockMvc
                .perform(deleteMethod)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
