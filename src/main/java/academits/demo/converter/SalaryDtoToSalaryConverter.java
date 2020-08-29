package academits.demo.converter;

import academits.demo.dto.SalaryDto;
import academits.demo.model.Salary;
import org.springframework.stereotype.Component;

@Component
public class SalaryDtoToSalaryConverter extends AbstractConverter<SalaryDto, Salary> {
    @Override
    public Salary convert(SalaryDto sourse) {
        Salary salary = new Salary();
        salary.setTo(sourse.getTo());
        salary.setFrom(sourse.getFrom());
        salary.setCurrency(sourse.getCurrency());
        salary.setGross(sourse.isGross());

        return salary;
    }
}
