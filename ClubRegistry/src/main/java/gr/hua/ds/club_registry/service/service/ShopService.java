package gr.hua.ds.club_registry.service.service;

import gr.hua.ds.club_registry.db.models.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    public List <Shop> findAllShops();
    public  List<Shop> findAllActiveShops();
    public  List<Shop> findAllInActiveShops();
    public  List<Shop> findShopsByClubTaxNo(String clubName);
    public Shop findShop(int shopId );
    public Shop insertShop(Shop shop );
    public void deleteShop( Shop shop );
    public Shop updateShop(Shop oldShop , Shop newShop );


}


