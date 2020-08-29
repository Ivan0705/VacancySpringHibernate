package academits.demo.converter;

import academits.demo.dto.SalaryDto;
import academits.demo.model.Salary;
import org.springframework.stereotype.Component;

@Component
public class SalaryToSalaryDtoConverter extends AbstractConverter<Salary, SalaryDto> {
    @Override
    public SalaryDto convert(Salary sourse) {
        if (sourse != null) {
            SalaryDto salaryDto = new SalaryDto();

            salaryDto.setTo(sourse.getTo());
            salaryDto.setFrom(sourse.getFrom());
            salaryDto.setCurrency(sourse.getCurrency());
            salaryDto.setGross(sourse.isGross());

            return salaryDto;
        }
        return null;

    }
}
