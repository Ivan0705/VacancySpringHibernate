package academits.demo.model;

import javax.persistence.*;

@Entity
public class Salary {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "salaryTo")
    private int to;

    @Column(name = "salaryFrom")
    private int from;

    @Column
    private String currency;

    @Column
    private boolean gross;

    @OneToOne
    @JoinColumn(name = "vacancyId")
    private Vacancy vacancy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isGross() {
        return gross;
    }

    public void setGross(boolean gross) {
        this.gross = gross;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
