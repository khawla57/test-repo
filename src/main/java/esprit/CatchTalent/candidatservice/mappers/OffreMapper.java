package esprit.CatchTalent.candidatservice.mappers;

import esprit.CatchTalent.candidatservice.dto.OffreRequestDTO;
import esprit.CatchTalent.candidatservice.dto.OffreResponseDTO;
import esprit.CatchTalent.candidatservice.entities.Offre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OffreMapper {

    OffreResponseDTO fromOffre (Offre Offre);
    Offre OffreRequestDToToOffre (OffreRequestDTO OffreRequestDTO);

}