package academits.demo.controller;


import academits.demo.converter.VacancyToVacancyDtoConverter;
import academits.demo.dto.PageResult;
import academits.demo.dto.VacancyDto;
import academits.demo.service.VacancyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/vacancyBook/rpc/api/v1")
public class VacancyBookController {
    private VacancyService vacancyService;
    private VacancyToVacancyDtoConverter vacancyToVacancyDtoConverter;


    public VacancyBookController(VacancyService vacancyService, VacancyToVacancyDtoConverter vacancyToVacancyDtoConverter) {
        this.vacancyService = vacancyService;
        this.vacancyToVacancyDtoConverter = vacancyToVacancyDtoConverter;
    }

    /*
        @RequestMapping(value = "getAllVacancies", method = RequestMethod.GET)
        @ResponseBody
        public List<VacancyDto> getAllVacancies(String filter, String filterRegion) throws ParseException {
            return vacancyToVacancyDtoConverter.convert(vacancyService.getAllVacancies(filter, filterRegion));

        }*/
    @RequestMapping(value = "getAllVacancies", params = "page", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<VacancyDto> getAllVacancies(String filter, String filterRegion, @RequestParam("page") int page, int sizePage) throws ParseException {
        //  PageResult<VacancyDto> pageResult = vacancyService.getAllVacancies(filter, filterRegion, page, sizePage);
        return vacancyService.getAllVacancies(filter, filterRegion, page, sizePage);// pageResult.setEntries(vacancyToVacancyDtoConverter.convert(vacancyService.getAllVacancies(filter, filterRegion)));
    }

    @RequestMapping(value = "getTopSalary", method = RequestMethod.GET)
    @ResponseBody
    public List<VacancyDto> getTopSalary() {
        return vacancyToVacancyDtoConverter.convert(vacancyService.getTopSalary());
    }
}


