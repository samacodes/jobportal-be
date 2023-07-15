package sama.company.jobportalbe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sama.company.jobportalbe.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
}
