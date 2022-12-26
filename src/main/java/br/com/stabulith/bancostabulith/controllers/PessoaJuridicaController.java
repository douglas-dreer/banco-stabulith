package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.enums.MensagemEnum;
import br.com.stabulith.bancostabulith.models.PessoaJuridicaDTO;
import br.com.stabulith.bancostabulith.services.PessoaJuridicaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.stabulith.bancostabulith.enums.MensagemEnum.*;

@RestController
@RequestMapping("/cadastro/pessoa-juridica")
@Log4j2
public class PessoaJuridicaController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @GetMapping
    public ResponseEntity<List<PessoaJuridicaDTO>> listar() {
        return ResponseEntity.ok(pessoaJuridicaService.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PessoaJuridicaDTO> buscarPorId(@PathVariable UUID id) {
        PessoaJuridicaDTO resultado = pessoaJuridicaService.buscarPorId(id);

        if (resultado.getId() == null) {
            log.info(MensagemEnum.NAO_FOI_ENCONTRADO_REGISTROS.getDescricao());
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(resultado);
    }

    @GetMapping(path = "", params = "cnpj")
    public ResponseEntity<PessoaJuridicaDTO> buscarPorCpf(@RequestParam(name = "cnpj") long cnpj) {
        PessoaJuridicaDTO resultado = pessoaJuridicaService.buscarPorCNPJ(cnpj);
        if (resultado == null || resultado.getId() == null) {
            log.info(NAO_FOI_ENCONTRADO_REGISTROS);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<PessoaJuridicaDTO> salvar(@RequestBody PessoaJuridicaDTO dto) throws URISyntaxException {
        dto = pessoaJuridicaService.salvar(dto);
        if (dto == null) {
            log.error(ERRO_INTERNO.getDescricao());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.created(new URI(String.format("%s/%s", httpServletRequest.getRequestURL(), dto.getId()))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaJuridicaDTO> editar(@PathVariable UUID id, @RequestBody PessoaJuridicaDTO dto) throws URISyntaxException {
        if (!id.equals(dto.getId())) {
            log.warn(PARAMETROS_INCORRETOS.getDescricao());
            return ResponseEntity.badRequest().build();
        }
        dto.setDataModificacao(LocalDateTime.now());
        dto = pessoaJuridicaService.salvar(dto);
        return ResponseEntity.created(new URI(String.format("%s/%s", httpServletRequest.getRequestURL(), dto.getId()))).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaJuridicaDTO> excluir(@PathVariable UUID id) {
        if (!pessoaJuridicaService.excluir(id)) {
            log.info(NAO_FOI_ENCONTRADO_REGISTROS.getDescricao());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().build();
    }


}
