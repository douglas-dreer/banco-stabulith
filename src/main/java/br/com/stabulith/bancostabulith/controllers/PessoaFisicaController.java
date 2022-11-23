package br.com.stabulith.bancostabulith.controllers;

import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;
import br.com.stabulith.bancostabulith.services.PessoaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cadastro/pessoa-fisica")
public class PessoaFisicaController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping
    public ResponseEntity<PessoaFisicaDTO> salvar(@RequestBody PessoaFisicaDTO dto) throws URISyntaxException {
        dto = pessoaFisicaService.salvar(dto);
        return ResponseEntity.created(new URI(String.format("%s/%s", httpServletRequest.getRequestURL(), dto.getId()))).build();
    }

    @GetMapping
    public ResponseEntity<List<PessoaFisicaDTO>> listar(){
        return ResponseEntity.ok(pessoaFisicaService.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PessoaFisicaDTO> buscarPorId(@PathVariable UUID id ){
        return ResponseEntity.ok(pessoaFisicaService.buscarPorId(id));
    }
}
