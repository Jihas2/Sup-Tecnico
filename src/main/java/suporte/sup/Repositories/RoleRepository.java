package suporte.sup.Repositories;

import suporte.sup.Entities.Usuario;
import suporte.sup.Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
