package br.com.stabulith.bancostabulith.services;

import br.com.stabulith.bancostabulith.entities.PessoaFisicaEntity;
import br.com.stabulith.bancostabulith.entities.TipoDocumentoEntity;
import br.com.stabulith.bancostabulith.models.PessoaFisica;
import br.com.stabulith.bancostabulith.repositories.DocumentoRepository;
import br.com.stabulith.bancostabulith.repositories.PessoaFisicaRepository;
import br.com.stabulith.bancostabulith.repositories.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaFisicaService {
    @Autowired
    private PessoaFisicaRepository repository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    public PessoaFisica salvar(PessoaFisica pessoaFisica) {
        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisica.convertToEntity();
        pessoaFisicaEntity.getDocumentoList().stream().map(documento -> {
            TipoDocumentoEntity tipoDocumento = documento.getTipoDocumento();
            tipoDocumento = tipoDocumentoRepository.findBySigla(tipoDocumento.getSigla());
            documento.setTipoDocumento(tipoDocumento);
            return documento;
        });
        return repository.save(pessoaFisicaEntity).convertToModel();

    }

    public List<PessoaFisica> listar() {
        List<PessoaFisicaEntity> entityList = (List<PessoaFisicaEntity>) repository.findAll();
        return entityList.stream().map(entity -> entity.convertToModel()).collect(Collectors.toList());
    }
}
