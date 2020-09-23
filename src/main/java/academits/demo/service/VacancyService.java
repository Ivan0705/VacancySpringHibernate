package academits.demo.service;

import academits.demo.converter.VacancyToVacancyDtoConverter;
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
import java.util.stream.Collectors;


@Service
public class VacancyService {
    private final VacancyDao vacancyDao;
    private final VacancyDaoImpl vacancyDaoImp;

    private final VacancyToVacancyDtoConverter vacancyToVacancyDtoConverter;


    @Autowired
    public VacancyService(VacancyDao vacancyDao, VacancyDaoImpl vacancyDaoImp, VacancyToVacancyDtoConverter vacancyToVacancyDtoConverter) {
        this.vacancyDao = vacancyDao;
        this.vacancyDaoImp = vacancyDaoImp;

        this.vacancyToVacancyDtoConverter = vacancyToVacancyDtoConverter;
    }

    public PageResult<VacancyDto> getAllVacancies(String filterQuery, String filterRegion, int page, int sizePage) throws ParseException {
        if (!StringUtils.isEmpty(filterQuery) || !StringUtils.isEmpty(filterRegion)) {
            List<VacancyDto> list = vacancyDao.find(filterQuery, filterRegion, page, sizePage).stream()
                    .map(vacancyToVacancyDtoConverter::convert)
                    .collect(Collectors.toList());
            long count = vacancyDao.findCount(filterQuery, filterRegion);

            return new PageResult<>(list, count, (int) Math.ceil((double) count / sizePage));
        } else {
            List<VacancyDto> list = vacancyDao.getAllVacancies(filterQuery, filterRegion, page, sizePage).stream()
                    .map(vacancyToVacancyDtoConverter::convert)
                    .collect(Collectors.toList());
            long count = vacancyDao.countAll(filterQuery, filterRegion);

            return new PageResult<>(list, count, (int) Math.ceil((double) count / sizePage));
        }
    }

    public List<Vacancy> getTopSalary() {
        return vacancyDaoImp.getTopSalary();
    }
}
