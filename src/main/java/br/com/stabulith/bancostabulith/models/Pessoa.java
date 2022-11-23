package br.com.stabulith.bancostabulith.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public abstract class Pessoa {
    private UUID id;
    private String email;
    private List<ContatoTelefonico> contatoTelefonicoList;
    private List<Endereco> enderecoList;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean isAtivo;

}
