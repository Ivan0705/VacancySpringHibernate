package academits.demo.converter;

import academits.demo.dto.api.AreaDto;
import academits.demo.model.Area;
import org.springframework.stereotype.Component;

@Component
public class AreaDtoToAreaConverter extends AbstractConverter<AreaDto, Area> {

    @Override
    public Area convert(AreaDto sourse) {
        Area area = new Area();
        area.setId(sourse.getId());
        area.setName(sourse.getName());
        area.setUrl(sourse.getUrl());

        return area;
    }
}
