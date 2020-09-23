package academits.demo.converter;

import academits.demo.dto.VacancyDto;
import academits.demo.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacancyToVacancyDtoConverter extends AbstractConverter<Vacancy, VacancyDto> {
    private final AreaToAreaDtoConverter areaToAreaDtoConverter;

    private final EmployerToEmployerDtoConverter employerConverter;

    private final SalaryToSalaryDtoConverter salaryToSalaryDtoConverter;

    @Autowired
    public VacancyToVacancyDtoConverter(AreaToAreaDtoConverter areaToAreaDtoConverter, EmployerToEmployerDtoConverter employerConverter, SalaryToSalaryDtoConverter salaryToSalaryDtoConverter) {
        this.areaToAreaDtoConverter = areaToAreaDtoConverter;
        this.employerConverter = employerConverter;
        this.salaryToSalaryDtoConverter = salaryToSalaryDtoConverter;
    }

    @Override
    public VacancyDto convert(Vacancy source) {
        if (source != null) {
            VacancyDto vacancy = new VacancyDto();
            vacancy.setId(source.getId());
            vacancy.setEmployer(employerConverter.convert(source.getEmployer()));
            vacancy.setName(source.getName());
            vacancy.setArea(areaToAreaDtoConverter.convert(source.getArea()));
            vacancy.setSalary(salaryToSalaryDtoConverter.convert(source.getSalary()));
            vacancy.setPublishedAt(source.getPublishedAt());

            return vacancy;
        }
        return null;

    }
}
