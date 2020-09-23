package academits.demo.converter;

import academits.demo.dto.EmployerDto;
import academits.demo.model.Employer;
import org.springframework.stereotype.Component;

@Component
public class EmployerToEmployerDtoConverter extends AbstractConverter<Employer, EmployerDto> {
    @Override
    public EmployerDto convert(Employer source) {
        EmployerDto employer = new EmployerDto();
        employer.setId(source.getId());
        employer.setName(source.getName());
        employer.setUrl(source.getUrl());

        return employer;
    }
}
