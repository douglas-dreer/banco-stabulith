package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.enums.*;
import br.com.stabulith.bancostabulith.models.*;
import br.com.stabulith.bancostabulith.services.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cadastro/cliente")
public class PessoaController {
   @Autowired
   HttpServletRequest httpServletRequest;

   @Autowired
   private PessoaFisicaService pessoaFisicaService;

   @PostMapping(value = "/pessoa-fisica")
   public ResponseEntity<PessoaFisica> salvar(@RequestBody PessoaFisica pessoaFisica) throws URISyntaxException {
      pessoaFisica = pessoaFisicaService.salvar(pessoaFisica);
      URI uri = new URI(String.format("%s/%s", httpServletRequest.getRequestURI(), pessoaFisica.getId()));
      return ResponseEntity.created(uri).build();
   }

   @GetMapping(value = "/pessoa-fisica")
   public ResponseEntity<List<PessoaFisica>> listar() {
      return ResponseEntity.ok(pessoaFisicaService.listar());
   }

   @GetMapping("/pessoa-fisica/criar")
   public ResponseEntity<PessoaFisica> criarPessoaFisica(){
       Afiliacao afiliacao = new Afiliacao();
       afiliacao.setNomeCompletoMae("Maria Cleonice Matias Dreer");
       afiliacao.setNomeCompletoPai("José Carlos Dreer");

       List<Documento> documentoList = Arrays.asList(
          new Documento(3584058902L, TipoDocumento.CPF),
          new Documento(86841949, TipoDocumento.RG)
       );

       List<Endereco> enderecoList = Collections.singletonList(
               new Endereco(
                       "Rua Lázaro Zamenhof", 120,
                       null, null, 86040350L,
                       "Londrina", "Paraná",
                       TipoEndereco.RESIDENCIAL
               )
       );

       List<ContatoTelefonico> contatoTelefonicoList = Arrays.asList(
               new ContatoTelefonico(43, 996490584L, TipoTelefone.CELULAR, TipoContatoTelefonico.PESSOAL),
               new ContatoTelefonico(43, 984388156L, TipoTelefone.CELULAR, TipoContatoTelefonico.RECADO)
       );

       PessoaFisica pessoaFisica = new PessoaFisica();
       pessoaFisica.setId(UUID.randomUUID());
       pessoaFisica.setNomeCompleto("Douglas Dreer");
       pessoaFisica.setIdade(39);
       pessoaFisica.setAfiliacao(afiliacao);
       pessoaFisica.setGenero(Genero.M);
       pessoaFisica.setDataNascimento(LocalDate.of(1983,8,23));
       pessoaFisica.setEmail("douglasdreer@outlook.com.br");
       pessoaFisica.setDocumentoList(documentoList);
       pessoaFisica.setEnderecoList(enderecoList);
       pessoaFisica.setContatoTelefonicoList(contatoTelefonicoList);

        return ResponseEntity.ok(pessoaFisica);
   }
}
