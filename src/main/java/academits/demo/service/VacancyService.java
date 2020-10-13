package academits.demo.service;

import academits.demo.converter.VacancyToVacancyDtoConverter;
import academits.demo.dao.VacancyDao;
import academits.demo.dto.PageResult;
import academits.demo.dto.VacancyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VacancyService {
    private final VacancyDao vacancyDao;
    private final VacancyToVacancyDtoConverter vacancyToVacancyDtoConverter;

    @Autowired
    public VacancyService(VacancyDao vacancyDao, VacancyToVacancyDtoConverter vacancyToVacancyDtoConverter) {
        this.vacancyDao = vacancyDao;
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

    public PageResult<VacancyDto> getTopSalary(int page, int sizePage, int countTopVacancy) throws ParseException {
        int limit = Math.max(0, countTopVacancy - page * sizePage);
        limit = Math.min(sizePage, limit);
        List<VacancyDto> list = vacancyDao.getTopSalary(page, limit).stream().map(vacancyToVacancyDtoConverter::convert).collect(Collectors.toList());

        return new PageResult<>(list, countTopVacancy, (int) Math.ceil((double) countTopVacancy / sizePage));
    }

}
