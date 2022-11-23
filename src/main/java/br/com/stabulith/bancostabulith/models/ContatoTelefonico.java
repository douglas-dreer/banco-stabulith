package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.entities.ContatoTelefonicoEntity;
import br.com.stabulith.bancostabulith.enums.TipoContatoTelefonico;
import br.com.stabulith.bancostabulith.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContatoTelefonico {
    private UUID id;
    private int ddd;
    private long numero;
    private int ramal;
    private TipoTelefone tipoTelefone;
    private TipoContatoTelefonico tipoContatoTelefonico;

    public ContatoTelefonico(int ddd, long numero, TipoTelefone tipoTelefone, TipoContatoTelefonico tipoContatoTelefonico) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipoContatoTelefonico = tipoContatoTelefonico;
        this.tipoTelefone = tipoTelefone;
    }

    public ContatoTelefonico(int ddd, long numero, int ramal, TipoTelefone tipoTelefone, TipoContatoTelefonico tipoContatoTelefonico) {
        this.ddd = ddd;
        this.numero = numero;
        this.ramal = ramal;
        this.tipoContatoTelefonico = tipoContatoTelefonico;
        this.tipoTelefone = tipoTelefone;
    }




    public ContatoTelefonicoEntity convertToEntity(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ContatoTelefonicoEntity.class);
    }
}
