package br.com.stabulith.bancostabulith.repositories;

import br.com.stabulith.bancostabulith.entities.TipoDocumentoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoDocumentoRepository extends CrudRepository<TipoDocumentoEntity, UUID> {
    TipoDocumentoEntity findBySigla(String sigla);
}
