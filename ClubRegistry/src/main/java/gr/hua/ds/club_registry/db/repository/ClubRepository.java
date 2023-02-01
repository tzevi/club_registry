package gr.hua.ds.club_registry.db.repository;

import gr.hua.ds.club_registry.db.models.Club;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends CrudRepository<Club,String> {
    Optional<Club> findByTaxNo(String tax_no);

    List<Club> findByActive(boolean active);

    Club findBySuperVisorUsernameAndActive(String username,boolean active);


}
