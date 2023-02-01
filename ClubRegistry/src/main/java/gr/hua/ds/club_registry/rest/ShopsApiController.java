package gr.hua.ds.club_registry.rest;

import java.util.List;
import java.util.Optional;

import gr.hua.ds.club_registry.db.models.Club;
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
        return shopService.findAllShops();
    }

    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_basedonactivity")
    public List<Shop> getAllShops( @RequestParam(name="active") Boolean active ){
        return active?shopService.findAllActiveShops():shopService.findAllInActiveShops();
    }

    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLUB_SUPERVISOR')")
    @GetMapping("/all_by_club_tax_no")
    public List<Shop> getAllShopsByClubTaxNo(@RequestParam(name="taxNo") String taxNo){
        return shopService.findShopsByClubTaxNo(taxNo);
    }

    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLUB_SUPERVISOR')")
    @GetMapping("/{shop_id}")
    public Shop findShop(@PathVariable(name="shop_id") int shopId){
        return shopService.findShop(shopId);
    }
    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop insertShop(@RequestBody Shop shop){
        return shopService.insertShop(shop);
    }

    @PreAuthorize("hasRole('ROLE_POLICE') ||hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shop updateShop(@RequestBody Shop shop){
        Shop oldShop =shopService.findShop(shop.getId());
        return  shopService.updateShop(oldShop,shop);
    }
    @PreAuthorize("hasRole('ROLE_POLICE') || hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public Shop deleteShop(@PathVariable int id){
        Shop shop =shopService.findShop(id);
        shopService.deleteShop(shop);
        return  shop;
    }


}
