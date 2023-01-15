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
        Optional <List<Club>> clubs = Optional.ofNullable(clubService.findAllClubs());
        return clubs.orElseThrow(() ->new ShopsNotFoundException());
    }

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_based_on_activity")
    public List<Club> getAllClubsActiveInactive( @RequestParam(name="active") Boolean active ){
        Optional <List<Club>> activeClubs = Optional.ofNullable(active?clubService.findAllActiveClubs():clubService.findAllInactiveClubs());
        return activeClubs.orElseThrow(() ->new ClubsNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_team_name")
    public List<Club> getAllClubsByTeamName(@RequestParam(name="team_name") String teamName){
        Optional <List<Club>> clubs = Optional.ofNullable(clubService.searchClubByTeamName(teamName));
        return clubs.orElseThrow(() ->new ShopsNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/all_supervisor_name")
    public List<Club> getClubsBySupervisorName(@RequestHeader String supervisorName){
        Optional <List<Club>> clubs = Optional.ofNullable(clubService.searchBySupervisorName(supervisorName));
        return clubs.orElseThrow(() ->new ShopsNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/team_active")
    public List<Club> getAllShopsByTeamNameAndActive(@RequestParam(name="team_name") String teamName, @RequestParam(name="active_Status") Boolean active){
        Optional <List<Club>> clubs = Optional.ofNullable(clubService.searchByTeamNameAndActiveStatus(teamName, active));
        return clubs.orElseThrow(() ->new ShopsNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/find")
    public Club findClub(@RequestHeader(name="tax_no") String taxNo){
        Optional <Club> club = Optional.ofNullable(clubService.searchByTaxNo(taxNo));
        return club.orElseThrow(() ->new ClubNotFoundException() );
    }
    @PreAuthorize("hasRole('ROLE_GGA') || hasRole('ROLE_ADMIN')")
    @GetMapping("/supervisor_active")
    public List<Club> searchBySupervisorAndActiveStatus(@RequestHeader String supervisor, @RequestHeader Boolean active){
        Optional <List<Club>> clubs = Optional.ofNullable(clubService.searchBySupervisorAndActiveStatus(supervisor,active));
        return clubs.orElseThrow(() ->new ClubNotFoundException() );
    }

    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN') ")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Club insertClub(@RequestBody Club club){
        return clubService.insertClub(club);
    }
    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN') ")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Club updateClub(@RequestBody Club club){
        return clubService.insertClub(club); //needs changes
    }

    @PreAuthorize("hasRole('ROLE_CLUB_SUPERVISOR') || hasRole('ROLE_ADMIN') ")
    @DeleteMapping("")
    public Club deleteClub(@RequestBody Club club){
        clubService.deleteClub(club);
        return  club;
    }

}
