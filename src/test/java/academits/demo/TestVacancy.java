package academits.demo;

import academits.demo.dao.VacancyDaoImpl;
import academits.demo.model.Vacancy;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestVacancy {
    private VacancyDaoImpl vacancyDaoImpl;

    private String query;
    private String filterRegion;
    private int page;
    private int sizePage;


    @Before(value = "query, filterRegion, page, sizePage")
    void setUp() {
        vacancyDaoImpl = new VacancyDaoImpl();
        vacancyDaoImpl.find(query, filterRegion, page, sizePage);
    }

    @After(value = "id")
    void cleanUp(Vacancy obj) {
        vacancyDaoImpl.remove(obj);
    }

    @Test
    void testVacancy() {
        int count = 10;
        assertEquals(10, count);
    }
}
