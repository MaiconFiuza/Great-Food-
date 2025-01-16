package fiap.postech.challenge.fase.one.repositories;

import fiap.postech.challenge.fase.one.entities.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByEmail(String email);
    Integer save(Cliente cliente);
    Integer update(Cliente cliente, Long id);
    void delete(Long id);
}
