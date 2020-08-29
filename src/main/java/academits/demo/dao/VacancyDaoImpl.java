package academits.demo.dao;

import academits.demo.model.Area;
import academits.demo.model.Employer;
import academits.demo.model.Salary;
import academits.demo.model.Vacancy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@Repository
public class VacancyDaoImpl extends GenericDaoImpl<Vacancy, Integer> implements VacancyDao {


    public VacancyDaoImpl() {
        super(Vacancy.class);
    }

    @Override
    public List<Vacancy> getAllVacancies(String filterQuery, String filterRegion, int page, int sizePage) {

        return findAll();
    }

    @Override
    public List<Vacancy> find(String query, String filterRegion, int page, int sizePage) {
        //int sizePage = это кол-во вакансий разделить на кол-во вакансий на 1 странице
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vacancy> criteriaQuery = criteriaBuilder.createQuery(clazz);

        Root<Vacancy> rootVacancy = criteriaQuery.from(clazz);

        Join<Vacancy, Salary> salary = rootVacancy.join("salary", JoinType.LEFT);
        Join<Vacancy, Employer> employer = rootVacancy.join("employer", JoinType.LEFT);
        Join<Vacancy, Area> area = rootVacancy.join("area", JoinType.LEFT);

        // Создать общий список критерий
        List<Predicate> criteriaList = new ArrayList<>();


        // Если query не пустой, то
        // Создать критерии для query и добавить в общий список

        if (!StringUtils.isEmpty(query)) {
            List<Predicate> queryCriteriaList = new ArrayList<>();

            Long queryNum;

            try {
                queryNum = parseLong(query);
                queryCriteriaList.add(criteriaBuilder.equal(area.get("id"), queryNum));
                queryCriteriaList.add(criteriaBuilder.equal(rootVacancy.get("id"), queryNum));
                queryCriteriaList.add(criteriaBuilder.equal(salary.get("to"), queryNum));
                queryCriteriaList.add(criteriaBuilder.equal(salary.get("from"), queryNum));
                queryCriteriaList.add(criteriaBuilder.equal(employer.get("id"), queryNum));
            } catch (Exception ignored) {
            }

            String queryDate;
            SimpleDateFormat formatter = new SimpleDateFormat();

            try {
                queryDate = formatter.format(query);
                queryCriteriaList.add(criteriaBuilder.equal(rootVacancy.get("publishedAt"), queryDate));

            } catch (Exception ignored) {
            }

            if ("true".equalsIgnoreCase(query)) {
                queryCriteriaList.add(criteriaBuilder.equal(salary.get("gross"), true));
            } else if ("false".equalsIgnoreCase(query)) {
                queryCriteriaList.add(criteriaBuilder.equal(salary.get("gross"), false));
            }
            queryCriteriaList.add(criteriaBuilder.equal(area.get("name"), query));
            queryCriteriaList.add(criteriaBuilder.equal(area.get("url"), query));
            queryCriteriaList.add(criteriaBuilder.equal(rootVacancy.get("name"), query));
            queryCriteriaList.add(criteriaBuilder.equal(salary.get("currency"), query));
            queryCriteriaList.add(criteriaBuilder.equal(employer.get("name"), query));
            queryCriteriaList.add(criteriaBuilder.equal(employer.get("url"), query));


            criteriaList.add(criteriaBuilder.or(queryCriteriaList.toArray(new Predicate[0])));
        }

        // Если filterRegion
        // Создать критерии для filterRegion и добавить в общий список

        if (!StringUtils.isEmpty(filterRegion)) {

            try {
                Long filterRegionNum = parseLong(filterRegion);
                criteriaList.add(criteriaBuilder.equal(area.get("id"), filterRegionNum));
            } catch (Exception ignored) {
            }

        }


        CriteriaQuery<Vacancy> select = criteriaQuery.select(rootVacancy);
        select.where(criteriaList.toArray(new Predicate[0]));
        TypedQuery<Vacancy> typedQuery = entityManager.createQuery(select);

        int startIndex = 50;
        int pageSize = 50;
        typedQuery.setFirstResult(startIndex);
        typedQuery.setMaxResults(pageSize);
        // Возвращать у контроллера не список ваканксий, а обертку PageResult?
        // Внутри список вакансий (страница) и кол-во всех вакансий вообще?
        // + Кол-во страниц

        // Кол-во страниц = кол-во вакансий / размер страницы?

        // Добавить новый параметр "page" - текущая страница в контроллер--

        // Добавить новый параметр в DAO - offset, maxResults+

        // typedQuery.setMaxResults(maxResults);+
        // typedQuery.setFirstResult(offset);+

        // offset - на сколько вакансий смещать список+
        // maxResults - сколько всего возвращать вакансий на страницу+

        // В верстке циклом v-for добавить кнопки-ссылки на страницы в зависимости сколько всего страниц
        // Добавить в верстку новое поле currentPage - отправлять его на контроллер при запросе как параметр page
        // При клике на кнопку-ссылку паджинации менять currentPage


        return typedQuery.getResultList();
    }

    @Override
    public void update(Vacancy obj) {
        entityManager.merge(obj);
    }

    @Override
    public void remove(Vacancy obj) {

        entityManager.remove(obj);
    }

    public List<Vacancy> getTopSalary() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vacancy> cq = cb.createQuery(clazz);
        Root<Vacancy> root = cq.from(clazz);
        Join<Vacancy, Salary> salaryJoin = root.join("salary");

        CriteriaQuery<Vacancy> select = cq.select(root);
        cq.orderBy(cb.desc(salaryJoin.get("to")));

        TypedQuery<Vacancy> q = entityManager.createQuery(select).setMaxResults(1);

        return q.getResultList();

    }
}
