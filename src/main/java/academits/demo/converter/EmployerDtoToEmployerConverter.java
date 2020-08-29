package academits.demo.converter;

import academits.demo.dto.EmployerDto;
import academits.demo.model.Employer;
import org.springframework.stereotype.Component;

@Component
public class EmployerDtoToEmployerConverter extends AbstractConverter<EmployerDto, Employer> {

    @Override
    public Employer convert(EmployerDto source) {
        Employer employer = new Employer();

        employer.setId(source.getId());
        employer.setName(source.getName());
        employer.setUrl(source.getUrl());

        return employer;
    }
}
