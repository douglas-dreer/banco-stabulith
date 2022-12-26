package br.com.stabulith.bancostabulith.services;

import br.com.stabulith.bancostabulith.ConfiguracaoTest;
import br.com.stabulith.bancostabulith.entities.PessoaFisica;
import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;
import br.com.stabulith.bancostabulith.repositories.PessoaFisicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PessoaFisicaServiceTest extends ConfiguracaoTest {
    @InjectMocks
    private PessoaFisicaService service;

    @MockBean
    private PessoaFisicaRepository repository;
    private List<PessoaFisicaDTO> pessoaFisicaList = new ArrayList<>();
    private PessoaFisica pessoaFisica = new PessoaFisica();
    private PessoaFisicaDTO dto = new PessoaFisicaDTO();


    @BeforeEach
    public void setup() {
        pessoaFisica = this.criarPessoaFisicaEntity();
        pessoaFisicaList = this.criarPessoaFisicaDTOList();
        dto = this.criarPessoaFisicaDTO();
    }

    @Test
    public void mustReturnCreated_WhenSalvar() {
        when(repository.save(any())).thenReturn(pessoaFisica);

        PessoaFisicaDTO pessoaFisicaSalva = service.salvar(dto);

        assertNotNull(pessoaFisicaSalva);
    }

    @Test
    public void mustReturnSuccess_WhenListar() {

    }

}
