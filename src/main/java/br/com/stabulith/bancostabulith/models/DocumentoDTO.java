package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.TipoDocumentoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentoDTO {
    private TipoDocumentoEnum tipoDocumento;
    private long numero;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private boolean isAtivo;
}
