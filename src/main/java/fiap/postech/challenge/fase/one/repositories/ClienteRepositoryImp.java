package fiap.postech.challenge.fase.one.repositories;

import fiap.postech.challenge.fase.one.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepositoryImp implements ClienteRepository {

    @Autowired
    private  final JdbcClient jdbcClient;

    public ClienteRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jdbcClient
                .sql("SELECT * FROM clientes WHERE id = :id")
                .param("id", id)
                .query(Cliente.class)
                .optional();
    }

    @Override
    public Optional<Cliente> findByEmail(String email) {
        return jdbcClient
                .sql("SELECT * FROM clientes WHERE email = :email")
                .param("email", email)
                .query(Cliente.class)
                .optional();
    }


    @Override
    public Integer save(Cliente cliente) {
        return this.jdbcClient
                .sql("INSERT INTO clientes(nome, email, login, senha, ultima_alteracao, endereco) " +
                        "VALUES (:nome, :email, :login, :senha, :ultima_alteracao, :endereco)")
                .param("nome", cliente.getNome())
                .param("email",cliente.getEmail())
                .param("login", cliente.getLogin())
                .param("senha", cliente.getSenha())
                .param("ultima_alteracao", cliente.getUltimaAlteracao())
                .param("endereco", cliente.getEndereco())
                .update();
    }


    @Override
    public Integer update(Cliente cliente, Long id) {
        return jdbcClient
                .sql("UPDATE clientes SET nome = :nome, email = :email, login = :login, senha = :senha, " +
                        "ultima_alteracao = :ultima_alteracao, endereco = :endereco " +
                        "WHERE id = :id" )
                .param("nome", cliente.getNome())
                .param("email", cliente.getEmail())
                .param("login", cliente.getLogin())
                .param("senha", cliente.getSenha())
                .param("ultima_alteracao", cliente.getUltimaAlteracao())
                .param("endereco", cliente.getEndereco())
                .param("id", id)
                .update();
    }

    public Integer updateSenha(String senha, Long id) {
        return jdbcClient
                .sql("UPDATE clientes senha = :senha WHERE id = :id ")
                .param("senha", senha)
                .param("id", id)
                .update();
    }

    @Override
    public void delete(Long id) {
        jdbcClient
                .sql("DELETE FROM clientes WHERE id = :id")
                .param("id", id)
                .update();

    }
}
