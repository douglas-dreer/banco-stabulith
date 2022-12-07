package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.enums.TipoDocumentoEnum;
import br.com.stabulith.bancostabulith.enums.TipoEnderecoEnum;
import br.com.stabulith.bancostabulith.models.AfiliacaoDTO;
import br.com.stabulith.bancostabulith.models.DocumentoDTO;
import br.com.stabulith.bancostabulith.models.EnderecoDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaFisicaControllerTest {
    private final static String ENDPOINT = "/cadastro/pessoa-fisica";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaFisicaService pessoaFisicaService;

    private final PessoaFisicaDTO pessoaFisica = new PessoaFisicaDTO();
    private List<PessoaFisicaDTO> pessoaFisicaList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        DocumentoDTO documentoCPF = new DocumentoDTO();
        documentoCPF.setNumero(12345678900L);
        documentoCPF.setTipoDocumento(TipoDocumentoEnum.CPF);
        List<DocumentoDTO> documentoList = Collections.singletonList(documentoCPF);

        EnderecoDTO enderecoResidencial = new EnderecoDTO();
        enderecoResidencial.setLogradouro("Rua Brasil");
        enderecoResidencial.setNumero(1500);
        enderecoResidencial.setCep(8888888L);
        enderecoResidencial.setTipoEndereco(TipoEnderecoEnum.RESIDENCIAL);
        enderecoResidencial.setCidade("São Paulo");
        enderecoResidencial.setEstado("SP");
        List<EnderecoDTO> enderecoList = Collections.singletonList(enderecoResidencial);

        AfiliacaoDTO afiliacao = new AfiliacaoDTO();
        afiliacao.setNomeCompletoPai("João da Silva");
        afiliacao.setNomeCompletoMae("Maria da Silva");
        afiliacao.setPaisDesconhecidos(false);

        pessoaFisica.setId(UUID.randomUUID());
        pessoaFisica.setNomeCompleto("Enzo da Silva");
        pessoaFisica.setDocumentos(documentoList);
        pessoaFisica.setIdade(18);
        pessoaFisica.setEnderecos(enderecoList);
        pessoaFisica.setEmail("enzo.silva@email.com.br");
        pessoaFisica.setAfiliacao(afiliacao);
        pessoaFisica.setDataNascimento(LocalDate.now());
        pessoaFisica.setDataCriacao(LocalDateTime.now());
        pessoaFisica.setDataModificacao(LocalDateTime.now());

        pessoaFisicaList = Collections.singletonList(pessoaFisica);

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
