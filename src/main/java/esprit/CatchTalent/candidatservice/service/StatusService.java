package esprit.CatchTalent.candidatservice.service;

import esprit.CatchTalent.candidatservice.dto.StatusCountDTO;
import esprit.CatchTalent.candidatservice.entities.Status;

import java.util.List;

public interface StatusService {

    List<Status> getAllStatus();

    Status addStatusCandidat(Integer candidatId, Status status);
    Status updateStatus(Integer statusId, Status updatedStatus);
    List<StatusWithCandidatCount> getAllStatusWithCandidatCount();

    List<StatusCountDTO> countCandidatsByStatus();

}
