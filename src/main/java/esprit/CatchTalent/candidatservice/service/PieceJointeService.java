package esprit.CatchTalent.candidatservice.service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import esprit.CatchTalent.candidatservice.Exceptions.FileStorageException;
import esprit.CatchTalent.candidatservice.dto.SearchResultDTO;
import esprit.CatchTalent.candidatservice.entities.FileStorageProperties;
import esprit.CatchTalent.candidatservice.entities.PieceJointe;
import esprit.CatchTalent.candidatservice.entities.Status;
import esprit.CatchTalent.candidatservice.repositories.CandidatRepository;
import esprit.CatchTalent.candidatservice.repositories.PieceJointeRepository;
import esprit.CatchTalent.candidatservice.repositories.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
@Transactional
public class PieceJointeService {

    @Autowired
    PieceJointeRepository piecejointerepository ;

    @Autowired
    CandidatRepository candidatRepo ;
    @Autowired
    private  StatusRepository statusRepository;
    private Path fileStorageLocation ;



    @Autowired
    public PieceJointeService(StatusRepository statusRepository, FileStorageProperties fileStorageProperties) {
        this.statusRepository = statusRepository;

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String LocalstoreFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileSystemNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileSystemNotFoundException("File not found " + fileName);
        }
    }


    public List<PieceJointe> findByCandidatId (Integer candidatId){

        /*
         * if(!piecejointerepository.existsById(candidatId)) { throw new
         * ResourceNotFoundException("Not found Tutorial with id = " + candidatId); }
         */
        return    piecejointerepository.findByCandidatId(candidatId) ;

    }

/*
    public PieceJointe UpdateStatut(Integer pieceJointeId, Integer statutId) {

        PieceJointe pieceJointe = piecejointerepository.findById(pieceJointeId)
                .orElseThrow(() -> new EntityNotFoundException("Pièce jointe introuvable"));

        Status nouveauStatut = statusRepository.findById(statutId)
                .orElseThrow(() -> new EntityNotFoundException("Statut introuvable"));

        pieceJointe.setStatus(nouveauStatut);
        return piecejointerepository.save(pieceJointe);
    }
   public PieceJointe storeFile(MultipartFile file ,Integer id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());


        // Check if the file's name contains invalid characters
        if
        (
                fileName.contains("..")) { throw new
                FileStorageException("Sorry! Filename contains invalid path sequence " +
                fileName); }

        Candidat candidat = candidatRepo.getById(id);
        PieceJointe dbFile = new PieceJointe(fileName, file.getContentType()  );
        dbFile.setCandidat(candidat);
        return piecejointerepository.save(dbFile);
    }*/


    public List<SearchResultDTO> SearchInFile (String query){

        return piecejointerepository.searchFullText(query) ;


    }

  /*  public List<PieceJointe> findByStatus(Integer statusId) {

       return piecejointerepository.findByStatusId(statusId) ;
    }*/

}