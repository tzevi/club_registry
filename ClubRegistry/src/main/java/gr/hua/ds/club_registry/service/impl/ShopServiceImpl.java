package gr.hua.ds.club_registry.service.impl;

import java.util.List;
import java.util.Optional;

import gr.hua.ds.club_registry.db.models.Club;
import gr.hua.ds.club_registry.db.models.Shop;
import gr.hua.ds.club_registry.db.repository.ShopRepository;
import gr.hua.ds.club_registry.db.repository.ClubRepository;

import gr.hua.ds.club_registry.rest.exception.ClubNotFoundException;
import gr.hua.ds.club_registry.rest.exception.ShopsNotFoundException;
import gr.hua.ds.club_registry.rest.exception.UserNotFoundException;
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
    public List <Shop> findShopsByClubTaxNo( String taxNo ) {
        return  shopDAO.findByClub_TaxNo(taxNo);
    }

    @Override
    public Shop findShop(int shopId ) {
        return shopDAO.findById(shopId).orElseThrow(() -> new ShopsNotFoundException());
    }

    @Override
    public Shop insertShop(Shop shop ) {
        Club club =clubDAO.findByTaxNo(shop.getClubTaxNo()).orElseThrow(() -> new ClubNotFoundException());;
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
