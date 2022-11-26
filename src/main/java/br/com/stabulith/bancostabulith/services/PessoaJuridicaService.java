package br.com.stabulith.bancostabulith.services;

import br.com.stabulith.bancostabulith.entities.PessoaJuridica;
import br.com.stabulith.bancostabulith.models.PessoaJuridicaDTO;
import br.com.stabulith.bancostabulith.repositories.PessoaJuridicaRepository;
import br.com.stabulith.bancostabulith.utils.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class PessoaJuridicaService {
    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private PessoaJuridicaRepository repository;

    public List<PessoaJuridicaDTO> listar() {
        List<PessoaJuridica> resultado = (List<PessoaJuridica>) repository.findAll();
        return mapperUtil.mapList(resultado, PessoaJuridicaDTO.class);
    }

    public PessoaJuridicaDTO buscarPorId(UUID id) {
        return mapperUtil.convertTo(repository.findById(id).orElse(new PessoaJuridica()), PessoaJuridicaDTO.class);
    }

    public PessoaJuridicaDTO salvar(PessoaJuridicaDTO dto) {
        PessoaJuridica pessoaJuridicaSalvo = new PessoaJuridica();
        try {
            Optional<UUID> uuidOptional = Optional.ofNullable(dto.getId());

            if (uuidOptional.isPresent()) {
                dto.setDataModificacao(LocalDateTime.now());
            }

            pessoaJuridicaSalvo = repository.save(mapperUtil.convertTo(dto, PessoaJuridica.class));

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return mapperUtil.convertTo(pessoaJuridicaSalvo, PessoaJuridicaDTO.class);
    }

    public boolean excluir(UUID id) {
        try {
            if (!repository.existsById(id)) {
                return false;
            }
            repository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return true;
    }

    public PessoaJuridicaDTO buscarPorCNPJ(long cpf) {
        return mapperUtil.convertTo(repository.findPessoaJuridicaByDocumentosByCPNJ(cpf).orElse(new PessoaJuridica()), PessoaJuridicaDTO.class);
    }
}
