package fiap.postech.challenge.fase.one.entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Usuario {
    protected Long id;
    protected String nome;
    protected String email;
    protected String login;
    protected String senha;
    protected Date ultimaAlteracao;

    public Usuario(String nome, String email, String login, String senha, Date ultimaAlteracao) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Usuario(Long id, String nome, String email, String login, Date ultimaAlteracao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.ultimaAlteracao = ultimaAlteracao;
    }
}