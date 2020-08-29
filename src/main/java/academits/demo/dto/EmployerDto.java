package academits.demo.dto;

public class EmployerDto {
    private String name;
    private String url;
    private Long id;

    public EmployerDto() {
    }

    public EmployerDto(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "\"id\":\"" + this.id + "\", \"name\":\"" + this.name + "\", \"url\":\"" + this.url + "\"";
    }
}
