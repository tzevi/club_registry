package gr.hua.ds.club_registry.db.repository;

import gr.hua.ds.club_registry.db.models.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository  extends CrudRepository<Shop,Integer> {
    List<Shop> findByActive(boolean active);

    List<Shop> findByClub_TaxNo(String taxNo);



    List<Shop> findByClubTeamName(String teamName);

    List<Shop> findByClubTeamNameAndActive(String teamName, boolean active);

    List<Shop> findByCity(String city);

    List<Shop> findByCityAndClubTeamName(String city, String teamName);

    List<Shop> findByClubClubNameAndActive(String clubName, boolean active );

    List<Shop> findByCityAndClubClubName(String city, String clubName);

}