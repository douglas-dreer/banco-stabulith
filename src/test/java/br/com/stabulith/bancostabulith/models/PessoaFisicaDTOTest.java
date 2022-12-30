package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.MensagemEnum;
import br.com.stabulith.bancostabulith.enums.TipoDocumentoEnum;
import br.com.stabulith.bancostabulith.enums.TipoEnderecoEnum;
import br.com.stabulith.bancostabulith.enums.TipoTelefoneEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class PessoaFisicaDTOTest extends DTOTest{
   private AfiliacaoDTO afiliacao = new AfiliacaoDTO();
   private List<DocumentoDTO> documentos = new ArrayList<>();
   private List<TelefoneContatoDTO> telefoneContatos = new ArrayList<>();
   private List<EnderecoDTO> enderecos = new ArrayList<>();

   @BeforeEach
   public void setup(){
      afiliacao = new AfiliacaoDTO("Maria de Fátima", "João da Silva");

      documentos = Arrays.asList(
              new DocumentoDTO(TipoDocumentoEnum.CPF, 12345678900L),
              new DocumentoDTO(TipoDocumentoEnum.RG, 12345678L)
      );

      telefoneContatos = Collections.singletonList(
              new TelefoneContatoDTO(99, 333333333L, TipoTelefoneEnum.PARTICULAR)
      );

      enderecos = Collections.singletonList(
              new EnderecoDTO("Avenida Brasil", 1500, "casa", null, 86000000L, "São Paulo", "SP", TipoEnderecoEnum.RESIDENCIAL )
      );
   }

   @Test
   public void mustReturnSuccess_WhenCreateWithConstructorAllParams(){
      PessoaFisicaDTO dto = new PessoaFisicaDTO(
              ID, NOME, EMAIL, IDADE, DATA_NASCIMENTO,
              GENERO, afiliacao, documentos, telefoneContatos,
              enderecos, DATA_CRIACAO, DATA_MODIFICACAO, ATIVO
      );
      verificar(dto, false);
   }

   @Test
   public void mustReturnSuccess_WhenCreateResumeParameters(){
      PessoaFisicaDTO dto = new PessoaFisicaDTO(NOME, EMAIL, IDADE, DATA_NASCIMENTO, GENERO, afiliacao, documentos, telefoneContatos, enderecos);
      dto.setId(ID);
      verificar(dto, true);

   }

   @Test
   public void mustReturnSuccess_WhenToJSON() throws JsonProcessingException {
      PessoaFisicaDTO dto = criarDTO();
      assertFalse(dto.toJSON().isEmpty());
   }

   private PessoaFisicaDTO criarDTO(){
      return new PessoaFisicaDTO(
              ID, NOME, EMAIL, IDADE, DATA_NASCIMENTO,
              GENERO, afiliacao, documentos, telefoneContatos,
              enderecos, DATA_CRIACAO, DATA_MODIFICACAO, ATIVO
      );
   }

   @Override
   void verificar(Object object, boolean isCustomizado) {
      final String MENSAGEM_ERRO = "%s no campo %s";
      PessoaFisicaDTO dto = (PessoaFisicaDTO) object;
      assertNotNull(dto, MensagemEnum.PARAMENTRO_NULO.getDescricao());

      if (!isCustomizado) {
         assertEquals(ID, dto.getId(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "ID"));
         assertEquals(DATA_CRIACAO, dto.getDataCriacao(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "DATA_CRIACAO"));
         assertEquals(DATA_MODIFICACAO, dto.getDataModificacao(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "DATA_MODIFICACAO"));
      }


      assertEquals(NOME, dto.getNomeCompleto(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "NOME"));
      assertEquals(EMAIL, dto.getEmail(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "EMAIL"));
      assertEquals(IDADE, dto.getIdade(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "IDADE"));
      assertEquals(DATA_NASCIMENTO, dto.getDataNascimento(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "DATA_NASCIMENTO"));
      assertEquals(GENERO, dto.getGenero(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "GENERO"));

      assertNotNull(dto.getAfiliacao(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMENTRO_NULO.getDescricao(), "AFILIACAO"));
      assertEquals(afiliacao, dto.getAfiliacao(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "AFILIACAO"));

      assertFalse(dto.getDocumentos().isEmpty(),  String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_VAZIO.getDescricao(), "DOCUMENTOS"));
      assertEquals(documentos, dto.getDocumentos(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "DOCUMENTOS"));

      assertFalse(dto.getTelefonesContato().isEmpty(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_VAZIO.getDescricao(), "TELEFONE_CONTATOS"));
      assertEquals(telefoneContatos, dto.getTelefonesContato(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "TELEFONE_CONTATOS"));

      assertFalse(dto.getEnderecos().isEmpty(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_VAZIO.getDescricao(), "ENDERECO"));
      assertEquals(enderecos, dto.getEnderecos(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "ENDERECO"));



      assertEquals(ATIVO, dto.isAtivo(), String.format(MENSAGEM_ERRO, MensagemEnum.PARAMETRO_DESIGUAL.getDescricao(), "ATIVO"));

   }
}