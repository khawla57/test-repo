package esprit.CatchTalent.candidatservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file")
@NoArgsConstructor
@Data
public class FileStorageProperties {

    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

}