package academits.demo.service;

import academits.demo.dao.VacancyDao;
import academits.demo.dao.VacancyDaoImpl;
import academits.demo.dto.PageResult;
import academits.demo.dto.VacancyDto;
import academits.demo.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.List;


@Service
public class VacancyService {
    private final VacancyDao vacancyDao;
    private final VacancyDaoImpl vacancyDaoImp;


    @Autowired
    public VacancyService(VacancyDao vacancyDao, VacancyDaoImpl vacancyDaoImp) {
        this.vacancyDao = vacancyDao;
        this.vacancyDaoImp = vacancyDaoImp;

    }

    /*public List<Vacancy> getAllVacancies(String filterQuery, String filterRegion) throws ParseException {
        if (!StringUtils.isEmpty(filterQuery) || !StringUtils.isEmpty(filterRegion)) {
            return vacancyDao.find(filterQuery, filterRegion);
        } else {
            return vacancyDao.getAllVacancies(filterQuery, filterRegion);
        }
    }*/
    public PageResult<VacancyDto> getAllVacancies(String filterQuery, String filterRegion, int page, int sizePage) throws ParseException {
        if (!StringUtils.isEmpty(filterQuery) || !StringUtils.isEmpty(filterRegion)) {
            return (PageResult<VacancyDto>) vacancyDao.find(filterQuery, filterRegion, page, sizePage);
        } else {
            return (PageResult<VacancyDto>) vacancyDao.getAllVacancies(filterQuery, filterRegion, page, sizePage);
        }
    }
  /*  public List<PageResult> getAllVacancies(String filterQuery, String filterRegion, int page) throws ParseException {
        if (!StringUtils.isEmpty(filterQuery) || !StringUtils.isEmpty(filterRegion)) {
            pageResult.setEntries(vacancyDao.find(filterQuery, filterRegion, page));
        } else {
           pageResult.setEntries(vacancyDao.getAllVacancies(filterQuery, filterRegion, page));
        }
        return (List<PageResult>) pageResult;
    }*/

    public List<Vacancy> getTopSalary() {
        return vacancyDaoImp.getTopSalary();
    }

}
