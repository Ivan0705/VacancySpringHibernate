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

    private List<Predicate> createCriteriaList(CriteriaBuilder criteriaBuilder, Root<?> rootVacancy, String query,
                                               String filterRegion) {
        Join<Vacancy, Salary> salary = rootVacancy.join("salary", JoinType.LEFT);
        Join<Vacancy, Employer> employer = rootVacancy.join("employer", JoinType.LEFT);
        Join<Vacancy, Area> area = rootVacancy.join("area", JoinType.LEFT);
        List<Predicate> criteriaList = new ArrayList<>();

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

        if (!StringUtils.isEmpty(filterRegion)) {
            try {
                Long filterRegionNum = parseLong(filterRegion);
                criteriaList.add(criteriaBuilder.equal(area.get("id"), filterRegionNum));
            } catch (Exception ignored) {
            }
        }
        return criteriaList;
    }

    @Override
    public List<Vacancy> getAllVacancies(String filterQuery, String filterRegion, int page, int sizePage) {
        return findAll(page, sizePage);
    }

    @Override
    public long countAll(String filterQuery, String filterRegion) {
        return countAll();
    }

    @Override
    public List<Vacancy> find(String query, String filterRegion, int page, int sizePage) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vacancy> criteriaQuery = criteriaBuilder.createQuery(clazz);

        Root<Vacancy> rootVacancy = criteriaQuery.from(clazz);
        List<Predicate> predicates = createCriteriaList(criteriaBuilder, rootVacancy, query, filterRegion);

        CriteriaQuery<Vacancy> select = criteriaQuery.select(rootVacancy);
        select.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Vacancy> typedQuery = entityManager.createQuery(select);
        int startPosition = (page) * sizePage;
        typedQuery.setFirstResult(startPosition);
        typedQuery.setMaxResults(sizePage);

        return typedQuery.getResultList();
    }

    @Override
    public long findCount(String query, String filterRegion) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<Vacancy> vacancyRoot = criteriaQuery.from(Vacancy.class);

        List<Predicate> predicates = createCriteriaList(criteriaBuilder, vacancyRoot, query, filterRegion);

        CriteriaQuery<Long> select = criteriaQuery.select(criteriaBuilder.count(vacancyRoot));
        select.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Long> typedQuery = entityManager.createQuery(select);

        return typedQuery.getSingleResult();
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

        TypedQuery<Vacancy> q = entityManager.createQuery(select).setMaxResults(20);

        return q.getResultList();
    }
}
