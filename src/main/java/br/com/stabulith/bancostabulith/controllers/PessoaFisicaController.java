package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.enums.MensagemEnum;
import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;
import br.com.stabulith.bancostabulith.services.PessoaFisicaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.stabulith.bancostabulith.enums.MensagemEnum.*;

@RestController
@RequestMapping("/cadastro/pessoa-fisica")
@Log4j2
public class PessoaFisicaController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @GetMapping
    public ResponseEntity<List<PessoaFisicaDTO>> listar(){
        return ResponseEntity.ok(pessoaFisicaService.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PessoaFisicaDTO> buscarPorId(@PathVariable UUID id ){
        PessoaFisicaDTO resultado = pessoaFisicaService.buscarPorId(id);

        if (resultado.getId() == null) {
            log.info(MensagemEnum.NAO_FOI_ENCONTRADO_REGISTROS.getDescricao());
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(resultado);
    }

    @GetMapping(path = "", params = "cpf")
    public ResponseEntity<PessoaFisicaDTO> buscarPorCpf(@RequestParam(name = "cpf") long cpf) {
        PessoaFisicaDTO resultado = pessoaFisicaService.buscarPorCpf(cpf);
        if( resultado == null || resultado.getId() == null) {
            log.info(NAO_FOI_ENCONTRADO_REGISTROS);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<PessoaFisicaDTO> salvar(@RequestBody PessoaFisicaDTO dto) throws URISyntaxException {
        dto = pessoaFisicaService.salvar(dto);
        if (dto == null) {
            log.error(ERRO_INTERNO.getDescricao());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.created(new URI(String.format("%s/%s", httpServletRequest.getRequestURL(), dto.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisicaDTO> editar(@PathVariable UUID id, @RequestBody PessoaFisicaDTO dto) throws URISyntaxException {
        if (!id.equals(dto.getId())) {
            log.warn(PARAMETROS_INCORRETOS.getDescricao());
            return ResponseEntity.badRequest().build();
        }
        dto.setDataModificacao(LocalDateTime.now());
        dto = pessoaFisicaService.salvar(dto);
        return ResponseEntity.created(new URI(String.format("%s/%s", httpServletRequest.getRequestURL(), dto.getId()))).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaFisicaDTO> excluir(@PathVariable UUID id) {
        if(!pessoaFisicaService.excluir(id)) {
            log.info(NAO_FOI_ENCONTRADO_REGISTROS.getDescricao());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(null);
    }


}
