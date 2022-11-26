package br.com.stabulith.bancostabulith.repositories;

import br.com.stabulith.bancostabulith.entities.PessoaJuridica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, UUID> {
    @Query(
            value = "SELECT DISTINCT pj FROM PessoaJuridica pj " +
                    "INNER JOIN pj.documentos d " +
                    "WHERE d.numero = :numero"
    )
    Optional<PessoaJuridica> findPessoaJuridicaByDocumentosByCPNJ(@PathVariable long numero);

}
