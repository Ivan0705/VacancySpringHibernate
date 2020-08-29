package academits.demo.dto;

import academits.demo.dto.api.AreaDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class VacancyDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("employer")
    private EmployerDto employer;

    @JsonProperty
    private AreaDto area;
    @JsonProperty("salary")
    private SalaryDto salary;

    @JsonProperty("published_at")
    private Date publishedAt;

    public long getId() {
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

    public EmployerDto getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerDto employer) {
        this.employer = employer;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

    public AreaDto getArea() {
        return area;
    }

    public SalaryDto getSalary() {
        return salary;
    }

    public void setSalary(SalaryDto salary) {
        this.salary = salary;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}


