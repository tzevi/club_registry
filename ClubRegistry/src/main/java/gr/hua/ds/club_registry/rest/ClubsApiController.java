package gr.hua.ds.club_registry.rest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import gr.hua.ds.club_registry.db.models.Club;
import gr.hua.ds.club_registry.rest.exception.ClubNotFoundException;
import gr.hua.ds.club_registry.rest.exception.ClubsNotFoundException;
import gr.hua.ds.club_registry.rest.exception.ShopsNotFoundException;
import gr.hua.ds.club_registry.service.service.ClubService;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path="/api/clubs")
public class ClubsApiController {

    @Autowired
    private ClubService clubService;

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')" )
    @GetMapping("")
    public List<Club> getAllClubs(){
        return clubService.findAllClubs();
    }

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_based_on_activity")
    public List<Club> getAllClubsActiveInactive( @RequestParam(name="active") Boolean active ){
        return active?clubService.findAllActiveClubs():clubService.findAllInactiveClubs();
    }

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/find/{taxNo}")
    public Club findClub(@PathVariable String taxNo){
        return clubService.searchByTaxNo(taxNo);
    }

    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN') ")
    @GetMapping("/supervisor_active")
    public Club searchBySupervisorAndActiveStatus(@RequestHeader String supervisor, @RequestHeader Boolean active){
        return clubService.searchBySupervisorAndActiveStatus(supervisor,active);
    }

    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN') ")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Club insertClub(@RequestBody Club club){
        return clubService.insertClub(club);
    }

    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_GGA') ||hasRole('ROLE_ADMIN') ")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Club updateClub(@RequestBody Club club){
        Club oldClub =clubService.searchByTaxNo(club.getTaxNo());
        return clubService.updateClub(oldClub,club); //needs changes
    }

    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN') ")
    @DeleteMapping("{taxNo}")
    public Club deleteClub(@PathVariable String taxNo){
        Club club =clubService.searchByTaxNo(taxNo);
        clubService.deleteClub(club);
        return  club;
    }

}
