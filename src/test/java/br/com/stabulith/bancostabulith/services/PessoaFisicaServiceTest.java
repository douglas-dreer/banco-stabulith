package br.com.stabulith.bancostabulith.services;

import br.com.stabulith.bancostabulith.ConfiguracaoTest;
import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;
import br.com.stabulith.bancostabulith.repositories.PessoaFisicaRepository;
import br.com.stabulith.bancostabulith.utils.MapperUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PessoaFisicaServiceTest extends ConfiguracaoTest {
    @InjectMocks
    private PessoaFisicaService service;

    @Mock
    private PessoaFisicaRepository repository;
    @Spy
    private MapperUtil mapperUtil;

    @Test
    public void mustReturnSuccesss_WhenBuscarPorId() {
        when(repository.findById(any())).thenReturn(Optional.of(criarPessoaFisicaEntity()));
        PessoaFisicaDTO resultado = service.buscarPorId(UUID.randomUUID());
        assertNotNull(resultado);
    }

    @Test
    public void mustReturnSuccesss_WhenBuscarPorCPF() {
        when(repository.findPessoaFisicaByDocumentosByCpf(anyInt())).thenReturn(Optional.of(criarPessoaFisicaEntity()));
        PessoaFisicaDTO resultado = service.buscarPorCpf(1L);
        assertNotNull(resultado);
    }

    @Test
    public void mustReturnSuccess_WhenListar() {
        when(repository.findAll()).thenReturn(criarPessoaFisicaEntityList());

        List<PessoaFisicaDTO> resultados = service.listar();

        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());
    }

    @Test
    public void mustReturnSuccess_WhenSalvar() {
        when(repository.save(any())).thenReturn(criarPessoaFisicaEntity());

        PessoaFisicaDTO pessoaFisicaDTO = criarPessoaFisicaDTO();
        pessoaFisicaDTO.setId(null);
        pessoaFisicaDTO.setDataCriacao(null);
        pessoaFisicaDTO.setDataModificacao(null);

        PessoaFisicaDTO resultado = service.salvar(pessoaFisicaDTO);

        assertNotNull(resultado);
    }

    @Test
    public void mustReturnSuccess_WhenSalvar_UUIDisPresent() {
        when(repository.save(any())).thenReturn(criarPessoaFisicaEntity());

        PessoaFisicaDTO pessoaFisicaDTO = criarPessoaFisicaDTO();
        pessoaFisicaDTO.setDataModificacao(null);

        PessoaFisicaDTO resultado = service.salvar(pessoaFisicaDTO);

        assertNotNull(resultado);
    }

    @Test
    public void mustReturnNullPointerException_WhenSalvar() {
        PessoaFisicaDTO resultado = service.salvar(null);

        assertNull(resultado);
    }

    @Test
    public void mustReturnTrue_WhenExcluir(){
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(true);

        boolean status = service.excluir(UUID.randomUUID());

        assertTrue(status);
    }

    @Test
    public void mustReturnFalse_WhenExcluir_BecouseNotFoundId(){
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(false);

        boolean status = service.excluir(UUID.randomUUID());

        assertFalse(status);
    }

    @Test
    public void mustReturnException_WhenExcluir() {
        boolean resultado = service.excluir(null);
        assertFalse(resultado);
    }



}
