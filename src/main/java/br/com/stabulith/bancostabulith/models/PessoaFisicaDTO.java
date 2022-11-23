package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.GeneroEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private GeneroEnum genero;
    private AfiliacaoDTO afiliacao;
    private List<TelefoneContatoDTO> telefonesContato;
    private List<EnderecoDTO> enderecos;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;
}
