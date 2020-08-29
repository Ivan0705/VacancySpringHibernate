package academits.demo.service;

import academits.demo.dao.VacancyDao;
import academits.demo.dto.api.ResultPage;
import academits.demo.model.Vacancy;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class CreateVacancyComponent {
    private VacancyDao vacancyDao;

    @Value("${specialization}")
    private Integer specialization;

    @Value("${area}")
    private Integer area;

    @Autowired
    public CreateVacancyComponent(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(
                    String.format("https://api.hh.ru/vacancies?specialization=%s&area=%s&per_page=50",
                            specialization, area
                            ));

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String vacancy1 = EntityUtils.toString(entity);
                    ResultPage vacancy2 = objectMapper.readValue(vacancy1, ResultPage.class);

                    for (Vacancy vacancy: vacancy2.getItems()) {
                        vacancyDao.update(vacancy);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
