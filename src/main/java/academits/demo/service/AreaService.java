package academits.demo.service;

import academits.demo.converter.AreaToAreaDtoConverter;
import academits.demo.dao.AreaDao;
import academits.demo.dto.api.AreaDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService {
    private final AreaDao areaDao;

    private final AreaToAreaDtoConverter areaToAreaDtoConverter;

    @Autowired
    public AreaService(AreaDao areaDao, AreaToAreaDtoConverter areaToAreaDtoConverter) {
        this.areaDao = areaDao;
        this.areaToAreaDtoConverter = areaToAreaDtoConverter;
    }

    public List<AreaDto> getListAreas() {
        return areaDao.findAll().stream().map(areaToAreaDtoConverter::convert).collect(Collectors.toList());
    }
}
