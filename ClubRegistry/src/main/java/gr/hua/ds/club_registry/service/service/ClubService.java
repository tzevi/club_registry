package gr.hua.ds.club_registry.service.service;

import java.util.List;

import gr.hua.ds.club_registry.db.models.Club;

public interface ClubService {
    public List<Club> findAllClubs();
    public List<Club> findAllActiveClubs();
    public List<Club> findAllInactiveClubs();
    public List <Club> searchClubByTeamName( String teamName );
    public List<Club> searchBySupervisorName(String supervisorName);
    public Club searchByTaxNo(String taxNo);
    public List<Club> searchByTeamNameAndActiveStatus(String teamName, Boolean active);
    public List<Club> searchBySupervisorAndActiveStatus(String supervisor, Boolean active);
    public Club insertClub(Club club);
    public void deleteClub(Club club);
    public Club updateClub(Club oldClub , Club newClub );
}


