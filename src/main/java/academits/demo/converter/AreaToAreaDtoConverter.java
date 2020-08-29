package academits.demo.converter;

import academits.demo.dto.api.AreaDto;
import academits.demo.model.Area;
import org.springframework.stereotype.Component;

@Component
public class AreaToAreaDtoConverter extends AbstractConverter<Area, AreaDto> {
    @Override
    public AreaDto convert(Area sourse) {
        if (sourse != null) {
            AreaDto areaDto = new AreaDto();

            areaDto.setId(sourse.getId());
            areaDto.setName(sourse.getName());
            areaDto.setUrl(sourse.getUrl());
            return areaDto;
        }
        return null;
    }
}
