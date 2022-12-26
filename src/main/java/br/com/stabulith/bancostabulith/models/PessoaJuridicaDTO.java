package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.Documento;
import br.com.stabulith.bancostabulith.entities.Endereco;
import br.com.stabulith.bancostabulith.entities.TelefoneContato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaJuridicaDTO {
    private UUID id;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;

    private List<DocumentoDTO> documentos;
    private List<PessoaFisicaDTO> funcionarios;
    private List<TelefoneContatoDTO> telefonesContato;
    private List<EnderecoDTO> enderecos;

    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataModificacao;

    @Column(name = "status")
    private boolean isAtivo;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.isAtivo = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.dataModificacao = LocalDateTime.now();
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(this);
    }
}
