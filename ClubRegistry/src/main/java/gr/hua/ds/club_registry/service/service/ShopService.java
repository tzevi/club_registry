package gr.hua.ds.club_registry.service.service;

import gr.hua.ds.club_registry.db.models.Shop;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    public List <Shop> findAllShops();
    public  List<Shop> findAllActiveShops();
    public  List<Shop> findAllInActiveShops();
    public  List<Shop> findShopsByClubName(String clubName);
    public List<Shop>  findShopsByTeamName( String teamName );
    public List <Shop> findShopsByCity( String cityName );
    public List <Shop> findShopsByCityAndTeamName( String cityName , String teamName );
    public List <Shop> findShopsByCityAndClubName( String cityName , String clubName );
    public List <Shop> findShopsByTeamNameAndActiveStatus( String teamName , Boolean active );
    public List <Shop> findShopsByClubNameAndActiveStatus( String clubName , Boolean active );
    public Optional<Shop> findShop(int shopId );
    public Shop insertShop(Shop shop );
    public void deleteShop( Shop shop );
    public Shop updateShop(Shop oldShop , Shop newShop );


}


