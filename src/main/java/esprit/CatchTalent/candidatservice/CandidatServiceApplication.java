package esprit.CatchTalent.candidatservice;

import esprit.CatchTalent.candidatservice.dto.OffreRequestDTO;
import esprit.CatchTalent.candidatservice.entities.FileStorageProperties;
import esprit.CatchTalent.candidatservice.entities.Status;
import esprit.CatchTalent.candidatservice.repositories.StatusRepository;
import esprit.CatchTalent.candidatservice.service.OffreService;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



import esprit.CatchTalent.candidatservice.dto.CandidatRequestDTO;
import esprit.CatchTalent.candidatservice.service.CandidatService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration(proxyBeanMethods = false)
@SpringBootApplication
@ComponentScan(basePackages = {"esprit.CatchTalent.candidatservice"})
@EnableConfigurationProperties({
		FileStorageProperties.class
})

@EnableDiscoveryClient 
public class CandidatServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CandidatServiceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public CookieSameSiteSupplier applicationCookieSameSiteSupplier() {
		return CookieSameSiteSupplier.ofNone();
	}
/*
	@Bean
	CommandLineRunner start(CandidatService candidatService, OffreService offreService , StatusRepository statusRepository) {
		return args -> {
			candidatService.save(new CandidatRequestDTO(1, "khawla", "bou@gmail.com" ,"engineer" ));
			candidatService.save(new CandidatRequestDTO(2, "khawla", "bou@gmail.com" ,"engineer"));

			statusRepository.save(new Status(1,"nouveaut Positionnement")) ;
			statusRepository.save(new Status(2,"a traiter ")) ;
			statusRepository.save(new Status(3,"a traiter ")) ;

		};
	}*/


}