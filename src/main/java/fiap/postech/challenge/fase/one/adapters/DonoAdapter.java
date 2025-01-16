package fiap.postech.challenge.fase.one.adapters;

import fiap.postech.challenge.fase.one.dtos.request.DonoCreateRequestDTO;
import fiap.postech.challenge.fase.one.dtos.request.DonoUpdateRequestDTO;
import fiap.postech.challenge.fase.one.entities.Dono;

import java.util.Date;

public class DonoAdapter {

    public Dono createDono(DonoCreateRequestDTO donoRequest, Date ultimaAlteracao) {
        Dono dono = new Dono(
                donoRequest.nome(),
                donoRequest.email(),
                donoRequest.login(),
                donoRequest.senha(),
                ultimaAlteracao
        );

        return dono;
    }

    public Dono updateDono(
            Long id,
            String nome,
            String email,
            String login,
            String senha,
            Date ultimaAlteracao
    ) {
        return new Dono(id, nome, email, login, senha, ultimaAlteracao);

    }

}
