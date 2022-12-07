package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.GeneroEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaFisicaDTO {
    private UUID id;
    private String nomeCompleto;
    private String email;
    private int idade;
    private LocalDate dataNascimento;
    private GeneroEnum genero;
    private AfiliacaoDTO afiliacao;
    private List<DocumentoDTO> documentos;
    private List<TelefoneContatoDTO> telefonesContato;
    private List<EnderecoDTO> enderecos;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(this);
    }

}
