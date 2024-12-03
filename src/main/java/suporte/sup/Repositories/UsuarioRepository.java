package suporte.sup.Repositories;

import org.springframework.data.jpa.repository.Query;
import suporte.sup.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select * from usuario where nome = ?1", nativeQuery = true)
    Optional<Usuario> findByNomr(String login);

}