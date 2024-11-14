package esprit.CatchTalent.candidatservice.service;


import java.io.File;
import java.util.List;


import esprit.CatchTalent.candidatservice.entities.EmailDetails;
import esprit.CatchTalent.candidatservice.entities.PieceJointe;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    public String sendSimpleMail(EmailDetails details)
    {


        try {

            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();


            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());


            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }


        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }


    public String
    sendMailWithAttachment(EmailDetails details)
    {

        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {


            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());


            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);


            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }


        catch (MessagingException e) {


            return "Error while sending mail!!!";
        }
    }

    public void envoyerPiecesJointesAuxEntreprises  (String adresseEmailEntreprise, String contenuEmail, List<PieceJointe> piecesJointes) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom(sender);
            helper.setTo(adresseEmailEntreprise);
            helper.setSubject("Pièces jointes de candidats");
            helper.setText(contenuEmail, true);


            for (PieceJointe pieceJointe : piecesJointes) {

              //  helper.addAttachment(pieceJointe.getFileName(), new ByteArrayResource(pieceJointe.getData()));
            }

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}