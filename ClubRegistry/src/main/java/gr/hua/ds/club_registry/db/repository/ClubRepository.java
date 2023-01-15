package gr.hua.ds.club_registry.db.repository;

import gr.hua.ds.club_registry.db.models.Club;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClubRepository extends CrudRepository<Club,String> {
    Club findByTaxNo(String tax_no);

    List<Club> findByActive(boolean active);

    List<Club> findByTeamName(String teamName);

    List<Club> findBySuperVisorUsername(String username);

    List<Club> findBySuperVisorUsernameAndActive(String username,boolean active);

    List<Club> findByTeamNameAndActive(String teamName,boolean active);

}
