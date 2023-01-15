package gr.hua.ds.club_registry.service.impl;

import java.util.List;
import java.util.Optional;

import gr.hua.ds.club_registry.db.models.Club;
import gr.hua.ds.club_registry.db.models.Shop;
import gr.hua.ds.club_registry.db.repository.ShopRepository;
import gr.hua.ds.club_registry.db.repository.ClubRepository;

import gr.hua.ds.club_registry.service.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class ShopServiceImpl implements ShopService {


    @Autowired
    private ShopRepository shopDAO;

    @Autowired
    private ClubRepository clubDAO;

    @Override
    public List <Shop> findAllShops() {
        return (List<Shop>) shopDAO.findAll();
    }

    @Override
    public List <Shop> findAllActiveShops() {
        return shopDAO.findByActive(true);
    }

    @Override
    public List <Shop> findAllInActiveShops() {
        return shopDAO.findByActive(false);
    }

    @Override
    public List <Shop> findShopsByClubName( String clubName ) {
        return  shopDAO.findByClubClubName(clubName);
    }

    @Override
    public List <Shop> findShopsByTeamName( String teamName ) {
        return shopDAO.findByClubTeamName(teamName);
    }

    @Override
    public List <Shop> findShopsByCity( String cityName ) {
        return shopDAO.findByCity(cityName);
    }

    @Override
    public List <Shop> findShopsByCityAndTeamName( String cityName , String teamName ) {
        return shopDAO.findByCityAndClubTeamName(cityName, teamName);
    }

    @Override
    public List <Shop> findShopsByCityAndClubName( String cityName , String clubName ) {
        return shopDAO.findByCityAndClubClubName(cityName, clubName);
    }

    @Override
    public List <Shop> findShopsByTeamNameAndActiveStatus( String teamName , Boolean active ) {
        return shopDAO.findByClubTeamNameAndActive(teamName, active);
    }

    @Override
    public List <Shop> findShopsByClubNameAndActiveStatus( String clubName , Boolean active ) {
        return shopDAO.findByClubClubNameAndActive(clubName, active);
    }

    @Override
    public Optional<Shop> findShop(int shopId ) {
        return shopDAO.findById(shopId);
    }

    @Override
    public Shop insertShop(Shop shop ) {
        Club club =clubDAO.findByTaxNo(shop.getClubTaxNo());
        shop.setClub(club);
        return shopDAO.save(shop);
    }

    @Override
    public void deleteShop( Shop shop ) {
      shopDAO.delete(shop);
    }

    @Override
    public Shop updateShop(Shop oldShop , Shop newShop ) {
        oldShop.setShopType(newShop.getShopType());
        oldShop.setCity(newShop.getCity());
        oldShop.setAddress(newShop.getAddress());
        oldShop.setActive(newShop.getActive());
        return shopDAO.save(oldShop);
    }

}
