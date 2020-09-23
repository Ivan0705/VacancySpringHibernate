package academits.demo.dao;

import academits.demo.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public interface VacancyDao extends GenericDao<Vacancy, Integer> {
    List<Vacancy> getAllVacancies(String filterQuery, String filterRegion, int page, int sizePage);

    long countAll(String filterQuery, String filterRegion);

    List<Vacancy> find(String query, String filterRegion, int page, int sizePage) throws ParseException;

    long findCount(String query, String filterRegion) throws ParseException;
}
