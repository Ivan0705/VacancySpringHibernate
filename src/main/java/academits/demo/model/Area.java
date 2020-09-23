package academits.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Area {
    @Id
    private
    Long id;

    private String name;

    private String url;

    public Area() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
