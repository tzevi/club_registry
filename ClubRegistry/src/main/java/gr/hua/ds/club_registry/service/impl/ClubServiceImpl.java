package gr.hua.ds.club_registry.service.impl;

import gr.hua.ds.club_registry.db.models.Club;
import gr.hua.ds.club_registry.db.models.User;
import gr.hua.ds.club_registry.db.repository.ClubRepository;
import gr.hua.ds.club_registry.db.repository.UserRepository;
import gr.hua.ds.club_registry.rest.exception.ClubNotFoundException;
import gr.hua.ds.club_registry.rest.exception.UserNotFoundException;
import gr.hua.ds.club_registry.service.service.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubDAO;
    @Autowired
    private UserRepository userDAO;
    @Override
    public List <Club> findAllClubs() {
     return (List<Club>) clubDAO.findAll();
    }

    @Override
    public List <Club> findAllActiveClubs() {
        return clubDAO.findByActive(true);
    }

    @Override
    public List<Club> findAllInactiveClubs(){
        return clubDAO.findByActive(false);
    }

    @Override
    public Club searchByTaxNo( String taxNo ) {
        return clubDAO.findByTaxNo(taxNo).orElseThrow(() -> new ClubNotFoundException());
    }


    @Override
    public Club searchBySupervisorAndActiveStatus( String supervisor , Boolean active ) {
        return clubDAO.findBySuperVisorUsernameAndActive(supervisor, active);
    }

    @Override
    public Club insertClub(Club club ) {
       User supervisor = userDAO.findByUsername(club.getSupervisorUsername()).orElseThrow(() -> new UserNotFoundException(club.getSupervisorUsername()));
       club.setSuperVisor(supervisor);
       return clubDAO.save(club);
    }

    @Override
    public void deleteClub( Club club ) {
      clubDAO.delete(club);
    }

    @Override
    public Club updateClub(Club oldClub , Club newClub ) {
        oldClub.setMembers(newClub.getMembers());
        oldClub.setTaxNo(newClub.getTaxNo());
        oldClub.setClubName(newClub.getClubName());
        oldClub.setActive(newClub.getActive());
        oldClub.setTeamName(newClub.getTeamName());
        return clubDAO.save(oldClub);
    }
}
