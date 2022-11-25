package br.com.stabulith.bancostabulith.services;

import br.com.stabulith.bancostabulith.entities.PessoaFisica;
import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;
import br.com.stabulith.bancostabulith.repositories.PessoaFisicaRepository;
import br.com.stabulith.bancostabulith.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PessoaFisicaService {
    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private PessoaFisicaRepository repository;

    public List<PessoaFisicaDTO> listar(){
        List<PessoaFisica> resultado = (List<PessoaFisica>) repository.findAll();
        return mapperUtil.mapList(resultado, PessoaFisicaDTO.class);
    }

    public PessoaFisicaDTO buscarPorId(UUID id) {
        return mapperUtil.convertTo(repository.findById(id).get(), PessoaFisicaDTO.class);
    }

    public PessoaFisicaDTO salvar(PessoaFisicaDTO dto) {
        dto.setDataModificacao(LocalDateTime.now());
        PessoaFisica save = repository.save(mapperUtil.convertTo(dto, PessoaFisica.class));
        return mapperUtil.convertTo(save, PessoaFisicaDTO.class);
    }

    public void excluir(UUID id) {
       repository.deleteById(id);
    }
}
