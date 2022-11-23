package br.com.stabulith.bancostabulith.services;

import br.com.stabulith.bancostabulith.entities.TipoDocumentoEntity;
import br.com.stabulith.bancostabulith.repositories.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TipoDocumentoService {
    @Autowired
    private TipoDocumentoRepository repository;

    public List<TipoDocumentoEntity> listar() {
        return (List<TipoDocumentoEntity>) repository.findAll();
    }

    public TipoDocumentoEntity buscarPorId(UUID id){
        return repository.findById(id).get();
    }

    public TipoDocumentoEntity salvar(TipoDocumentoEntity tipoDocumento) {
        return repository.save(tipoDocumento);
    }

    public void salvarTodos(List<TipoDocumentoEntity> tipoDocumentoEntityList) {
        repository.saveAll(tipoDocumentoEntityList);
    }
}
