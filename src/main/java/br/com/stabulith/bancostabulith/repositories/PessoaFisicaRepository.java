package br.com.stabulith.bancostabulith.repositories;

import br.com.stabulith.bancostabulith.entities.PessoaFisica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, UUID> {

    @Query(
            value = "SELECT DISTINCT ps FROM PessoaFisica ps " +
                    "INNER JOIN ps.documentos d " +
                    "WHERE d.numero = :numero "
    )
    Optional<PessoaFisica> findPessoaFisicaByDocumentosByCpf(@PathVariable long numero);
}
