package academits.demo.controller;

import academits.demo.dto.PageResult;
import academits.demo.dto.VacancyDto;
import academits.demo.dto.api.AreaDto;
import academits.demo.service.AreaService;
import academits.demo.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/vacancyBook/rpc/api/v1")
public class VacancyBookController {

    private VacancyService vacancyService;

    private final AreaService areaService;

    @Autowired
    public VacancyBookController(VacancyService vacancyService, AreaService areaService) {
        this.vacancyService = vacancyService;
        this.areaService = areaService;
    }

    @GetMapping("getRegions")
    @ResponseBody
    public List<AreaDto> getAreas() {
        return areaService.getListAreas();
    }

    @RequestMapping(value = "getAllVacancies", params = "page", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<VacancyDto> getAllVacancies(String filter, String filterRegion, int page, int sizePage) throws ParseException {
        return vacancyService.getAllVacancies(filter, filterRegion, page, sizePage);
    }

    @RequestMapping(value = "getTopSalary", method = RequestMethod.GET)
    @ResponseBody

    public PageResult<VacancyDto> getTopSalary(int page, int sizePage, int countTopVacancy) throws ParseException {
        return vacancyService.getTopSalary(page, sizePage, countTopVacancy);
    }
}


