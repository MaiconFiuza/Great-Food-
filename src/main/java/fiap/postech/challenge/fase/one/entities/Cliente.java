package fiap.postech.challenge.fase.one.entities;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Cliente extends Usuario{
    private String endereco;

    public Cliente(Long id, String nome, String email, String login, String senha, String endereco, Date ultimaAlteracao) {
        super(id, nome, email, login, senha, ultimaAlteracao);
        this.endereco = endereco;
    }

    public Cliente(String nome, String email, String login, String senha, String endereco, Date ultimaAlteracao) {
        super(nome, email, login, senha, ultimaAlteracao);
        this.endereco = endereco;
    }
}
