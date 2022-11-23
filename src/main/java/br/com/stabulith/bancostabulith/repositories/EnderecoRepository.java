package br.com.stabulith.bancostabulith.repositories;

import br.com.stabulith.bancostabulith.entities.EnderecoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EnderecoRepository extends CrudRepository<EnderecoEntity, UUID> {
}
