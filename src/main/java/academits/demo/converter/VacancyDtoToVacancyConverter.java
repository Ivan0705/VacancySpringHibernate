package academits.demo.converter;

import academits.demo.dto.VacancyDto;
import academits.demo.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacancyDtoToVacancyConverter extends AbstractConverter<VacancyDto, Vacancy> {
    private final AreaDtoToAreaConverter areaDtoToAreaConverter;

    private final EmployerDtoToEmployerConverter employerDtoConverter;

    private final SalaryDtoToSalaryConverter salaryDtoToSalaryConverter;

    @Autowired
    public VacancyDtoToVacancyConverter(AreaDtoToAreaConverter areaDtoToAreaConverter, EmployerDtoToEmployerConverter employerDtoConverter, SalaryDtoToSalaryConverter salaryDtoToSalaryConverter) {
        this.areaDtoToAreaConverter = areaDtoToAreaConverter;
        this.employerDtoConverter = employerDtoConverter;
        this.salaryDtoToSalaryConverter = salaryDtoToSalaryConverter;
    }

    @Override
    public Vacancy convert(VacancyDto source) {
        Vacancy vacancy = new Vacancy();

        vacancy.setId(source.getId());
        vacancy.setEmployer(employerDtoConverter.convert(source.getEmployer()));
        vacancy.setName(source.getName());
        vacancy.setArea(areaDtoToAreaConverter.convert(source.getArea()));
        vacancy.setSalary(salaryDtoToSalaryConverter.convert(source.getSalary()));
        vacancy.setPublishedAt(source.getPublishedAt());

        return vacancy;
    }
}
