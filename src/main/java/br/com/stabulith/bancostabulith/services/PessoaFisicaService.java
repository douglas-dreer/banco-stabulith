package br.com.stabulith.bancostabulith.services;

import br.com.stabulith.bancostabulith.entities.PessoaFisica;
import br.com.stabulith.bancostabulith.enums.MensagemEnum;
import br.com.stabulith.bancostabulith.models.PessoaFisicaDTO;
import br.com.stabulith.bancostabulith.repositories.PessoaFisicaRepository;
import br.com.stabulith.bancostabulith.utils.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class PessoaFisicaService {
    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private PessoaFisicaRepository repository;

    public List<PessoaFisicaDTO> listar() {
        List<PessoaFisica> resultado = (List<PessoaFisica>) repository.findAll();
        return mapperUtil.mapList(resultado, PessoaFisicaDTO.class);
    }

    public PessoaFisicaDTO buscarPorId(UUID id) {
        return mapperUtil.convertTo(repository.findById(id).orElse(new PessoaFisica()), PessoaFisicaDTO.class);
    }

    public PessoaFisicaDTO salvar(PessoaFisicaDTO dto) {
        PessoaFisica pessoaFisicaSalvo;
        try {
            Optional<UUID> uuidOptional = Optional.ofNullable(dto.getId());

            if (uuidOptional.isPresent()) {
                dto.setDataModificacao(LocalDateTime.now());
            }

            pessoaFisicaSalvo = repository.save(mapperUtil.convertTo(dto, PessoaFisica.class));

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        return mapperUtil.convertTo(pessoaFisicaSalvo, PessoaFisicaDTO.class);
    }

    public boolean excluir(UUID id) {
        boolean status = false;
        try {
            if (Optional.ofNullable(id).isPresent() && repository.existsById(id)){
                repository.deleteById(id);
                status = true;
            } else {
                throw new Exception(MensagemEnum.NAO_FOI_ENCONTRADO_REGISTROS.getDescricao());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return status;
    }

    public PessoaFisicaDTO buscarPorCpf(long cpf) {
        return mapperUtil.convertTo(repository.findPessoaFisicaByDocumentosByCpf(cpf).orElse(new PessoaFisica()), PessoaFisicaDTO.class);
    }
}
