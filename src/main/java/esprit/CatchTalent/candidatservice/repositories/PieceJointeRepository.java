package esprit.CatchTalent.candidatservice.repositories;

import java.util.List;


import esprit.CatchTalent.candidatservice.dto.SearchResultDTO;
import esprit.CatchTalent.candidatservice.entities.PieceJointe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
@Transactional
public interface PieceJointeRepository  extends JpaRepository<PieceJointe, Integer>{

    List<PieceJointe> findByCandidatId(Integer Id);
    //List<PieceJointe> findByStatusId(Integer Id);

    void deleteByCandidatId(Integer CandidatId);

    @Query(value = "SELECT * FROM PieceJointe WHERE MATCH(fileName, data) AGAINST(:query IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    List<SearchResultDTO> searchFullText(@Param("query") String query);

}


