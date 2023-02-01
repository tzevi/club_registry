package gr.hua.ds.club_registry.service.service;

import java.util.List;

import gr.hua.ds.club_registry.db.models.Club;

public interface ClubService {
    public List<Club> findAllClubs();
    public List<Club> findAllActiveClubs();
    public List<Club> findAllInactiveClubs();
    public Club searchBySupervisorAndActiveStatus(String supervisor, Boolean active);
    public Club insertClub(Club club);
    public void deleteClub(Club club);
    public Club updateClub(Club oldClub , Club newClub );

    public Club searchByTaxNo( String taxNo );
}


