package gr.hua.ds.club_registry.db.repository;

import gr.hua.ds.club_registry.db.enums.Roles;
import gr.hua.ds.club_registry.db.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    List<User> findByUserRole(Roles role);

}