package esprit.CatchTalent.candidatservice.service;


import esprit.CatchTalent.candidatservice.dto.StatusCountDTO;
import esprit.CatchTalent.candidatservice.entities.Candidat;
import esprit.CatchTalent.candidatservice.entities.Status;
import esprit.CatchTalent.candidatservice.repositories.CandidatRepository;
import esprit.CatchTalent.candidatservice.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private CandidatService candidatService;
    @Autowired
    private CandidatRepository candidatRepository;
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }


    public Status addStatusCandidat(Integer candidatId, Status status) {
        Candidat candidat = candidatService.getCandidatById(candidatId);
        if (candidat != null) {
           // status.setCandidat(candidat);
            return statusRepository.save(status);
        }
        return null;
    }
    public Status updateStatus(Integer statusId, Status updatedStatus) {
        Status existingStatus = statusRepository.findById(statusId).orElse(null);

        if (existingStatus != null) {

            existingStatus.setNom(updatedStatus.getNom());

            return statusRepository.save(existingStatus);
        }

        return null;
    }

    public List<StatusWithCandidatCount> getAllStatusWithCandidatCount() {
        List<Status> allStatus = statusRepository.findAll();

        return allStatus.stream()
                .map(status -> {
                    long candidatCount = candidatRepository.countByStatus(status);
                    return new StatusWithCandidatCount(status.getId(), status.getNom(), candidatCount);
                })
                .collect(Collectors.toList());
    }
    public List<StatusCountDTO> countCandidatsByStatus() {
        List<Object[]> result = statusRepository.countCandidatsByStatus();

        return result.stream()
                .map(row -> new StatusCountDTO((Status) row[0], (Long) row[1]))
                .collect(Collectors.toList());
    }
}
