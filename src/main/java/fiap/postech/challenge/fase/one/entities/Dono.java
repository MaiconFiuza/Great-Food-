package fiap.postech.challenge.fase.one.entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class Dono extends Usuario{

    public Dono(Long id, String nome, String email, String login, String senha, Date ultimaAlteracao) {
        super(id, nome, email, login, senha, ultimaAlteracao);
    }

    public Dono(String nome, String email, String login, String senha, Date ultimaAlteracao) {
        super(nome, email, login, senha, ultimaAlteracao);
    }
}
