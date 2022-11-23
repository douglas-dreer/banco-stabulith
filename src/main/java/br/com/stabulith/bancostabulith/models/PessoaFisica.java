package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.PessoaFisicaEntity;
import br.com.stabulith.bancostabulith.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaFisica extends Pessoa {
    private String nomeCompleto;
    private int idade;
    private LocalDate dataNascimento;
    private List<Documento> documentoList;
    private Afiliacao afiliacao;
    private Genero genero;

    public PessoaFisicaEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, PessoaFisicaEntity.class);
    }
}
