package esprit.CatchTalent.candidatservice.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.catalina.util.ResourceSet;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BingMapsApiResponse {

    private List<ResourceSet> resourceSets;

    public List<ResourceSet> getResourceSets() {
        return resourceSets;
    }

    public void setResourceSets(List<ResourceSet> resourceSets) {
        this.resourceSets = resourceSets;
    }

    // Other getters and setters for additional fields may be needed.
}
