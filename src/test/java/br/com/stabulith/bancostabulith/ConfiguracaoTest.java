package br.com.stabulith.bancostabulith;

import br.com.stabulith.bancostabulith.entities.Afiliacao;
import br.com.stabulith.bancostabulith.entities.Documento;
import br.com.stabulith.bancostabulith.entities.Endereco;
import br.com.stabulith.bancostabulith.entities.PessoaFisica;
import br.com.stabulith.bancostabulith.enums.TipoDocumentoEnum;
import br.com.stabulith.bancostabulith.enums.TipoEnderecoEnum;
import br.com.stabulith.bancostabulith.models.AfiliacaoDTO;
import br.com.stabulith.bancostabulith.models.DocumentoDTO;
import br.com.stabulith.bancostabulith.models.EnderecoDTO;
import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class ConfiguracaoTest {
    protected PessoaFisicaDTO criarPessoaFisicaDTO() {
        PessoaFisicaDTO pessoaFisica = new PessoaFisicaDTO();

        DocumentoDTO documentoCPF = new DocumentoDTO();
        documentoCPF.setNumero(12345678900L);
        documentoCPF.setTipoDocumento(TipoDocumentoEnum.CPF);

        DocumentoDTO documentoCNH = new DocumentoDTO();
        documentoCNH.setNumero(326549867L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.CNH);

        DocumentoDTO documentoRG = new DocumentoDTO();
        documentoCNH.setNumero(97894561236L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.RG);

        List<DocumentoDTO> documentoList = Arrays.asList(documentoRG, documentoCNH, documentoCPF);

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

        return pessoaFisica;
    }

    protected List<PessoaFisicaDTO> criarPessoaFisicaDTOList() {
        PessoaFisicaDTO pessoaFisica = new PessoaFisicaDTO();

        DocumentoDTO documentoCPF = new DocumentoDTO();
        documentoCPF.setNumero(12345678900L);
        documentoCPF.setTipoDocumento(TipoDocumentoEnum.CPF);

        DocumentoDTO documentoCNH = new DocumentoDTO();
        documentoCNH.setNumero(326549867L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.CNH);

        DocumentoDTO documentoRG = new DocumentoDTO();
        documentoCNH.setNumero(97894561236L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.RG);

        List<DocumentoDTO> documentoList = Arrays.asList(documentoRG, documentoCNH, documentoCPF);

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

        return Collections.singletonList(pessoaFisica);
    }

    protected PessoaFisica criarPessoaFisicaEntity(){
        PessoaFisica pessoaFisica = new PessoaFisica();

        Documento documentoCPF = new Documento();
        documentoCPF.setNumero(12345678900L);
        documentoCPF.setTipoDocumento(TipoDocumentoEnum.CPF);

        Documento documentoCNH = new Documento();
        documentoCNH.setNumero(326549867L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.CNH);

        Documento documentoRG = new Documento();
        documentoCNH.setNumero(97894561236L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.RG);

        List<Documento> documentoList = Arrays.asList(documentoRG, documentoCNH, documentoCPF);

        Endereco enderecoResidencial = new Endereco();
        enderecoResidencial.setLogradouro("Rua Brasil");
        enderecoResidencial.setNumero(1500);
        enderecoResidencial.setCep(8888888L);
        enderecoResidencial.setTipoEndereco(TipoEnderecoEnum.RESIDENCIAL);
        enderecoResidencial.setCidade("São Paulo");
        enderecoResidencial.setEstado("SP");
        List<Endereco> enderecoList = Collections.singletonList(enderecoResidencial);

        Afiliacao afiliacao = new Afiliacao();
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

        return pessoaFisica;
    }

    protected List<PessoaFisica> criarPessoaFisicaEntityList(){
        PessoaFisica pessoaFisica = new PessoaFisica();

        Documento documentoCPF = new Documento();
        documentoCPF.setNumero(12345678900L);
        documentoCPF.setTipoDocumento(TipoDocumentoEnum.CPF);

        Documento documentoCNH = new Documento();
        documentoCNH.setNumero(326549867L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.CNH);

        Documento documentoRG = new Documento();
        documentoCNH.setNumero(97894561236L);
        documentoCNH.setTipoDocumento(TipoDocumentoEnum.RG);

        List<Documento> documentoList = Arrays.asList(documentoRG, documentoCNH, documentoCPF);

        Endereco enderecoResidencial = new Endereco();
        enderecoResidencial.setLogradouro("Rua Brasil");
        enderecoResidencial.setNumero(1500);
        enderecoResidencial.setCep(8888888L);
        enderecoResidencial.setTipoEndereco(TipoEnderecoEnum.RESIDENCIAL);
        enderecoResidencial.setCidade("São Paulo");
        enderecoResidencial.setEstado("SP");
        List<Endereco> enderecoList = Collections.singletonList(enderecoResidencial);

        Afiliacao afiliacao = new Afiliacao();
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

        return Collections.singletonList(pessoaFisica);
    }
}
