package gr.hua.ds.club_registry.rest;

import java.util.List;
import java.util.Optional;

import gr.hua.ds.club_registry.db.models.Shop;
import gr.hua.ds.club_registry.rest.exception.ShopsNotFoundException;
import gr.hua.ds.club_registry.service.service.ShopService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path="/api/shops")
public class ShopsApiController {

    @Autowired
    private ShopService shopService;

    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Shop> getAllShops(){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findAllShops());
        return shops.orElseThrow(() ->new ShopsNotFoundException());
    }

    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_basedonactivity")
    public List<Shop> getAllShops( @RequestParam(name="active") Boolean active ){
        Optional <List<Shop>> activeShops = Optional.ofNullable(active?shopService.findAllActiveShops():shopService.findAllInActiveShops());
        return activeShops.orElseThrow(() ->new ShopsNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_by_city_name")
    public List<Shop> getAllShopsByCityName(@RequestParam(name="city_name") String cityName){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findShopsByCity(cityName));
        return shops.orElseThrow(() ->new ShopsNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_by_club_name")
    public List<Shop> getAllShopsByClubName(@RequestParam(name="club_name") String clubName){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findShopsByClubName(clubName));
        return shops.orElseThrow(() ->new ShopsNotFoundException() );
    }
    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_by_team_name")
    public List<Shop> getAllShopsByTeamName(@RequestParam(name="team_name") String teamName){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findShopsByClubName(teamName));
        return shops.orElseThrow(() ->new ShopsNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_POLICE')|| hasRole('ROLE_ADMIN')")
    @GetMapping("/all_by_team_name_and_city")
    public List<Shop> getAllShopsByTeamNameAndCity( @RequestParam(name="team_name") String teamName, @RequestParam(name="city") String cityName){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findShopsByCityAndTeamName(cityName, teamName));
        return shops.orElseThrow(() ->new ShopsNotFoundException() );
    }
    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_by_club_name_and_city")
    public List<Shop> getAllShopsByClubNameAndCity(@RequestParam(name="club_name") String clubName, @RequestParam(name="city") String cityName){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findShopsByCityAndClubName(cityName, clubName));
        return shops.orElseThrow(() ->new ShopsNotFoundException() );
    }
    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_by_team_name_and_activity")
    public List<Shop> getAllShopsByTeamNameAndActive(@RequestParam(name="team_name") String teamName, @RequestParam(name="active_Status") Boolean active){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findShopsByTeamNameAndActiveStatus(teamName, active));
        return shops.orElseThrow(() ->new ShopsNotFoundException() );
    }
    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_by_clu_name_and_activity")
    public List<Shop> getAllShopsByClubNameAndActive(@RequestParam(name="club_name") String clubName, @RequestParam(name="active_Status") Boolean active){
        Optional <List<Shop>> shops = Optional.ofNullable(shopService.findShopsByTeamNameAndActiveStatus(clubName, active));
        return shops.orElseThrow(() ->new ShopsNotFoundException() );
    }
    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/{shop_id}")
    public Optional<Shop> findShop(@PathVariable(name="shop_id") int shopId){
        Optional<Optional<Shop>> shop = Optional.ofNullable(shopService.findShop(shopId));
        return shop.orElseThrow(() ->new ShopsNotFoundException() );
    }
    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop insertShop(@RequestBody Shop shop){
        return shopService.insertShop(shop);
    }
    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop updateShop(@RequestBody Shop shop){
         //needs changes
        return  shopService.insertShop(shop);
    }
    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN')")
    @DeleteMapping("")
    public Shop deleteShop(@RequestBody Shop shop){
        shopService.deleteShop(shop);
        return  shop;
    }


}
