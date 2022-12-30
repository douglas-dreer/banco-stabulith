package br.com.stabulith.bancostabulith.models;

import br.com.stabulith.bancostabulith.enums.GeneroEnum;
import br.com.stabulith.bancostabulith.enums.TipoDocumentoEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class DTOTest {
    public final UUID ID = UUID.randomUUID();
    public final String NOME = "Maria Aparecida da Silva";
    public final String EMAIL = "maria.silva@email.com";
    public final int IDADE = 18;
    public final LocalDate DATA_NASCIMENTO = LocalDate.now();
    public final GeneroEnum GENERO = GeneroEnum.F;
    public final AfiliacaoDTO AFILIACAO = new AfiliacaoDTO();
    public final List<DocumentoDTO> DOCUMENTOS = new ArrayList<>();
    public final List<TelefoneContatoDTO> CONTATOS = new ArrayList<>();
    public final List<EnderecoDTO> ENDERECOS = new ArrayList<>();
    public final LocalDateTime DATA_CRIACAO = LocalDateTime.now();
    public final LocalDateTime DATA_MODIFICACAO = LocalDateTime.now();
    public final boolean ATIVO = true;
    public final String NOME_COMPLETO_MAE = "Carmem da Silva";
    public final String NOME_COMPLETO_PAI = "João da Silva";
    public final boolean PAIS_DESCONHECIDO = false;
    public final TipoDocumentoEnum TIPO_DOCUMENTO = TipoDocumentoEnum.RG;
    public final long NUMERO_DOCUMENTO = 1234L;
    public final int NUMERO_ENDERECO = 10;
    public final String COMPLEMENTO_ENDERECO = "";
    public final String PONTO_REFERENCIA_ENDERECO = "Rua do Mercado";


    void verificar(Object object, boolean isCustomizado) {
    }
}
