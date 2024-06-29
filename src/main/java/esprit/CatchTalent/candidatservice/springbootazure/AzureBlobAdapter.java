package esprit.CatchTalent.candidatservice.springbootazure;

import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AzureBlobAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AzureBlobAdapter.class);

    @Autowired
    private CloudBlobClient cloudBlobClient;

    @Autowired
    private CloudBlobContainer cloudBlobContainer;


    @Value("${azure.storage.connection-string}")
    private String azureStorageConnectionString;

    public boolean createContainer(String containerName){
        boolean containerCreated = false;
        CloudBlobContainer container = null;
        try {
            container = cloudBlobClient.getContainerReference(containerName);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (StorageException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        try {
            containerCreated = container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
        } catch (StorageException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return containerCreated;
    }

    public URI upload(MultipartFile multipartFile){
        URI uri = null;
        CloudBlockBlob blob = null;
        try {
            blob = cloudBlobContainer.getBlockBlobReference(multipartFile.getOriginalFilename());
            blob.upload(multipartFile.getInputStream(), -1);
            uri = blob.getUri();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }
    public BlobInputStream download(String blobName) {
        try {
            CloudBlockBlob blob = cloudBlobContainer.getBlockBlobReference(blobName);
            if (blob.exists()) {
                // Télécharge le contenu du blob dans un InputStream
                BlobInputStream blobInputStream = blob.openInputStream();

                // Vous pouvez maintenant lire le contenu du blob à partir de blobInputStream
                return blobInputStream;
            } else {
                throw new FileNotFoundException("Le blob spécifié n'existe pas : " + blobName);
            }
        } catch (URISyntaxException | StorageException | IOException e) {
            e.printStackTrace();
            // Gérez les exceptions appropriées en fonction de vos besoins
            return null;
        }
    }



    public List<URI> listBlobs(String containerName){
        List<URI> uris = new ArrayList<>();
        try {
            CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
            for (ListBlobItem blobItem : container.listBlobs()) {
                uris.add(blobItem.getUri());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        return uris;
    }

    public void deleteBlob(String containerName, String blobName){
        try {
            CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
            CloudBlockBlob blobToBeDeleted = container.getBlockBlobReference(blobName);
            blobToBeDeleted.deleteIfExists();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }
}
