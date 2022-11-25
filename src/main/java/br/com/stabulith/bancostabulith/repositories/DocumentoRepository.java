package br.com.stabulith.bancostabulith.repositories;

import br.com.stabulith.bancostabulith.entities.Documento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentoRepository extends CrudRepository<Documento, UUID> {
}
