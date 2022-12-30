package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.TipoEnderecoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoDTO {
    private UUID id;
    private String logradouro;
    private int numero;
    private String complemento;
    private String pontoReferencia;
    private long cep;
    private String cidade;
    private String estado;
    private TipoEnderecoEnum tipoEndereco;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;

    public EnderecoDTO(String logradouro, int numero, String complemento, String pontoReferencia, long cep, String cidade, String estado, TipoEnderecoEnum tipoEndereco) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.pontoReferencia = pontoReferencia;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.tipoEndereco = tipoEndereco;
        this.dataCriacao = LocalDateTime.now();
        this.isAtivo = true;
    }
}
