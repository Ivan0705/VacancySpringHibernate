package academits.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vacancy {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    @JsonProperty("published_at")
    private Date publishedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employerId")

    private Employer employer;
    //    @ManyToOne(cascade = CascadeType.ALL)
    //  @JoinColumn(name = "areaId")
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Area area;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Salary salary;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }
}
