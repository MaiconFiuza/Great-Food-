package fiap.postech.challenge.fase.one.repositories;

import fiap.postech.challenge.fase.one.entities.Dono;

import java.util.Optional;

public interface DonoRepository {
    Optional<Dono> findById (Long id);
    Optional<Dono> findByEmail (String email);
    Integer create(Dono dono);
    Integer update(Dono dono, Long id);

    void delete(Long id);
}
