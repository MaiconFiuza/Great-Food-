package fiap.postech.challenge.fase.one.repositories;
import org.springframework.jdbc.core.simple.JdbcClient;
import fiap.postech.challenge.fase.one.entities.Dono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DonoRepositoryImp implements DonoRepository{

    @Autowired
    private  final JdbcClient jdbcClient;

    public DonoRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Dono> findById(Long id) {
        return jdbcClient
                .sql("SELECT * FROM donos WHERE id = :id")
                .param("id", id)
                .query(Dono.class)
                .optional();
    }

    @Override
    public Optional<Dono> findByEmail(String email) {
        return jdbcClient
                .sql("SELECT * FROM donos WHERE email = :email")
                .param("email", email)
                .query(Dono.class)
                .optional();
    }

    @Override
    public Integer create(Dono dono) {
        return jdbcClient
                .sql("INSERT INTO donos (nome, email, login, senha, ultima_alteracao) "+
                    "VALUES (:nome, :email, :login, :senha, :ultima_alteracao)")
                .param("nome", dono.getNome())
                .param("email", dono.getEmail())
                .param("login", dono.getLogin())
                .param("senha", dono.getSenha())
                .param("ultima_alteracao", dono.getUltimaAlteracao())
                .update();
    }

    @Override
    public Integer update(Dono dono, Long id) {
        return jdbcClient
                .sql("UPDATE donos SET nome = :nome, email = :email, " +
                        "senha = :senha, ultima_alteracao = :ultima_alteracao " +
                        "WHERE id = :id")
                .param("id", id)
                .param("nome", dono.getNome())
                .param("email", dono.getEmail())
                .param("login", dono.getLogin())
                .param("senha", dono.getSenha())
                .param("ultima_alteracao", dono.getUltimaAlteracao())
                .update();
    }

    @Override
    public void delete(Long id) {
        jdbcClient
                .sql("DELETE FROM donos WHERE id = :id")
                .param("id", id)
                .update();
    }
}
