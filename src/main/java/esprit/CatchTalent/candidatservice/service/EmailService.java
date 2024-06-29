package esprit.CatchTalent.candidatservice.service;


import esprit.CatchTalent.candidatservice.entities.EmailDetails;

public interface EmailService {


    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}