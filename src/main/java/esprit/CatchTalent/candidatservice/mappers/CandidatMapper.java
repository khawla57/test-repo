package esprit.CatchTalent.candidatservice.mappers;

import org.mapstruct.Mapper;


import esprit.CatchTalent.candidatservice.dto.CandidatRequestDTO;
import esprit.CatchTalent.candidatservice.dto.CandidatResponseDTO;
import esprit.CatchTalent.candidatservice.entities.Candidat;


@Mapper(componentModel = "spring" ,  uses = {CandidatRequestDTO.class , CandidatResponseDTO .class})
public interface CandidatMapper {
	
	CandidatResponseDTO candidatToCandidatResponseDTO (Candidat candidat);

	Candidat  candidatResponseDTOtoCandidat(CandidatResponseDTO candidatResponseDTO);
	Candidat candidatRequestDToToCandidat (CandidatRequestDTO candidatRequestDTO);

}

