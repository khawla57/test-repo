package esprit.CatchTalent.candidatservice.maps;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceSet {

    private List<ResourceSet> resources;

    public List<ResourceSet> getResources() {
        return resources;
    }

    public void setResources(List<ResourceSet> resources) {
        this.resources = resources;
    }


}
