package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.entities.TelefoneContato;
import br.com.stabulith.bancostabulith.enums.TipoDocumentoEnum;
import br.com.stabulith.bancostabulith.enums.TipoEnderecoEnum;
import br.com.stabulith.bancostabulith.enums.TipoTelefoneEnum;
import br.com.stabulith.bancostabulith.models.*;
import br.com.stabulith.bancostabulith.services.PessoaJuridicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
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
public class PessoaJuridicaControllerTest {
    private final static String ENDPOINT = "/cadastro/pessoa-juridica";
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PessoaJuridicaService pessoaJuridicaService;

    private final PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
    private List<PessoaJuridicaDTO> pessoaJuridicaList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        DocumentoDTO documentoCNPJ = new DocumentoDTO();
        documentoCNPJ.setNumero(14431620000190L);
        documentoCNPJ.setTipoDocumento(TipoDocumentoEnum.CNPJ);

        DocumentoDTO documentoIE = new DocumentoDTO();
        documentoIE.setNumero(4345364354343L);
        documentoIE.setTipoDocumento(TipoDocumentoEnum.IE);

        List<DocumentoDTO> documentoList = Arrays.asList(documentoCNPJ, documentoIE);

        TelefoneContatoDTO telefoneComercial = new TelefoneContatoDTO();
        telefoneComercial.setNumero(40041331);
        telefoneComercial.setTipoTelefoneContato(TipoTelefoneEnum.EMPRESARIAL);
        List<TelefoneContatoDTO> telefoneContatoList = Collections.singletonList(telefoneComercial);

        EnderecoDTO enderecoComercial = new EnderecoDTO();
        enderecoComercial.setLogradouro("Rua Brasil");
        enderecoComercial.setNumero(1500);
        enderecoComercial.setCep(8888888L);
        enderecoComercial.setTipoEndereco(TipoEnderecoEnum.RESIDENCIAL);
        enderecoComercial.setCidade("São Paulo");
        enderecoComercial.setEstado("SP");
        List<EnderecoDTO> enderecoList = Collections.singletonList(enderecoComercial);

        pessoaJuridicaDTO.setId(UUID.randomUUID());
        pessoaJuridicaDTO.setRazaoSocial("Confeitaria Fabiana Lima Silva");
        pessoaJuridicaDTO.setNomeFantasia("Doces & Travessuras FabiBia");
        pessoaJuridicaDTO.setDocumentos(documentoList);
        pessoaJuridicaDTO.setEnderecos(enderecoList);
        pessoaJuridicaDTO.setTelefonesContato(telefoneContatoList);
        pessoaJuridicaDTO.setEmail("docesetravessuras@docesetravessuras.com.br");
        pessoaJuridicaDTO.setDataCriacao(LocalDateTime.now());
        pessoaJuridicaDTO.setDataModificacao(LocalDateTime.now());

        pessoaJuridicaList = Collections.singletonList(pessoaJuridicaDTO);

    }

    @Test
    void mustReturnSuccess_WhenListarPessoaJuridica() throws Exception {
        MockHttpServletRequestBuilder getMethod = get(ENDPOINT);
        when(pessoaJuridicaService.listar()).thenReturn(pessoaJuridicaList);
        mockMvc.perform(getMethod)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)));

    }

    @Test
    void mustReturnSuccess_WhenBuscarPorId() throws Exception {
        UUID id = pessoaJuridicaDTO.getId();
        String uri = String.format("%s/%s", ENDPOINT, id);

        when(pessoaJuridicaService.buscarPorId(id)).thenReturn(pessoaJuridicaDTO);

        MockHttpServletRequestBuilder getMethod = get(uri);

        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnSuccess_WhenBuscarPorId_ThenNotFound() throws Exception {
        UUID id = pessoaJuridicaDTO.getId();
        String uri = String.format("%s/%s", ENDPOINT, id);

        pessoaJuridicaDTO.setId(null);

        MockHttpServletRequestBuilder getMethod = get(uri);

        when(pessoaJuridicaService.buscarPorId(id)).thenReturn(pessoaJuridicaDTO);
        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnSuccess_WhenBuscarPorCNPJ() throws Exception {
        long cnpj = pessoaJuridicaDTO.getDocumentos().get(0).getNumero();

        MockHttpServletRequestBuilder getMethod = get(ENDPOINT);
        getMethod.queryParam("cnpj", String.valueOf(cnpj));

        when(pessoaJuridicaService.buscarPorCNPJ(cnpj)).thenReturn(pessoaJuridicaDTO);
        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnSuccess_WhenBuscarPorCNPJ_WhenNotFound() throws Exception {
        long cnpj = pessoaJuridicaDTO.getDocumentos().get(0).getNumero();

        MockHttpServletRequestBuilder getMethod = get(ENDPOINT);
        getMethod.queryParam("cnpj", String.valueOf(cnpj));

        pessoaJuridicaDTO.setId(null);
        when(pessoaJuridicaService.buscarPorCNPJ(cnpj)).thenReturn(pessoaJuridicaDTO);
        mockMvc.perform(getMethod)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void mustReturnIsCreated_WhenSalvar() throws Exception {
        String bodyJson = pessoaJuridicaDTO.toJSON();

        MockHttpServletRequestBuilder postMethod = post(ENDPOINT);
        postMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);


        when(pessoaJuridicaService.salvar(any())).thenReturn(pessoaJuridicaDTO);

        mockMvc
                .perform(postMethod)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void mustReturnInternalError_WhenSalvar() throws Exception {
        String bodyJson = pessoaJuridicaDTO.toJSON();

        MockHttpServletRequestBuilder postMethod = post(ENDPOINT);
        postMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);


        when(pessoaJuridicaService.salvar(any())).thenReturn(null);

        mockMvc
                .perform(postMethod)
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void mustReturnIsCreated_WhenEditar() throws Exception {
        String bodyJson = pessoaJuridicaDTO.toJSON();

        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder putMethod = put(URI, pessoaJuridicaDTO.getId().toString());
        putMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaJuridicaService.salvar(any())).thenReturn(pessoaJuridicaDTO);

        mockMvc
                .perform(putMethod)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void mustReturnIsCreated_WhenEditar_WithIdDiff() throws Exception {
        String bodyJson = pessoaJuridicaDTO.toJSON();

        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder putMethod = put(URI, UUID.randomUUID().toString());
        putMethod
                .content(bodyJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaJuridicaService.salvar(any())).thenReturn(pessoaJuridicaDTO);

        mockMvc
                .perform(putMethod)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void mustReturnIsAccepted_WhenExcluir() throws Exception {
        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder deleteMethod = delete(URI, pessoaJuridicaDTO.getId().toString());
        deleteMethod
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaJuridicaService.excluir(any())).thenReturn(true);

        mockMvc
                .perform(deleteMethod)
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    void mustReturnIsNotFound_WhenExcluir() throws Exception {
        final String URI = ENDPOINT.concat("/{id}");

        MockHttpServletRequestBuilder deleteMethod = delete(URI, pessoaJuridicaDTO.getId().toString());
        deleteMethod
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        when(pessoaJuridicaService.excluir(any())).thenReturn(false);

        mockMvc
                .perform(deleteMethod)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
