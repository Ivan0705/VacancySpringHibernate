package academits.demo.dao;

import academits.demo.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public interface VacancyDao extends GenericDao<Vacancy, Integer> {
    List<Vacancy> getAllVacancies(String filterQuery, String filterRegion, int page, int sizePage);

    List<Vacancy> find(String query, String filterRegion, int page, int sizePage) throws ParseException;
}
