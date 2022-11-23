package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.entities.TipoDocumentoEntity;
import br.com.stabulith.bancostabulith.enums.TipoDocumento;
import br.com.stabulith.bancostabulith.services.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cadastro/tipo-documento")
public class TipoDocumentoController {
    @Autowired
    private TipoDocumentoService service;

    @Autowired
    HttpServletRequest httpServletRequest;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TipoDocumentoEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<TipoDocumentoEntity> salvar(@RequestBody TipoDocumentoEntity tipoDocumento) throws URISyntaxException {
        tipoDocumento = service.salvar(tipoDocumento);
        URI uri = new URI(String.format("%s/%s", httpServletRequest.getRequestURI(), tipoDocumento.getSigla()));
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/padrao")
    @ResponseBody
    public ResponseEntity<TipoDocumentoEntity> criarDocuemntosPadrao() throws URISyntaxException {
       List<TipoDocumentoEntity> tipoDocumentoEntityList = Arrays.asList(
               new TipoDocumentoEntity(TipoDocumento.RG),
               new TipoDocumentoEntity(TipoDocumento.CPF),
               new TipoDocumentoEntity(TipoDocumento.CHN)
       );
       service.salvarTodos(tipoDocumentoEntityList);
       URI uri = new URI(String.format("%s", httpServletRequest.getRequestURI()));
       return ResponseEntity.created(uri).build();
    }

}
