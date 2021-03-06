package academits.demo.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    @PersistenceContext
    protected EntityManager entityManager;

    Class<T> clazz;

    GenericDaoImpl(Class<T> type) {
        this.clazz = type;
    }

    @Transactional
    @Override
    public List<T> findAll(int page, int sizePage) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);


        CriteriaQuery<T> select = cq.select(root);
        TypedQuery<T> q = entityManager.createQuery(select);

        q.setFirstResult((page) * sizePage);
        q.setMaxResults(sizePage);

        return q.getResultList();
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);

        CriteriaQuery<T> select = cq.select(cq.from(clazz));

        return entityManager.createQuery(select).getResultList();
    }


    @Override
    public long countAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(clazz);

        CriteriaQuery<Long> select = cq.select(cb.count(root));
        TypedQuery<Long> q = entityManager.createQuery(select);

        return q.getSingleResult();
    }


    @Override
    public void create(T obj) {
        entityManager.persist(obj);
    }

    @Override
    public void update(T obj) {
        entityManager.merge(obj);
    }

    @Override
    public void remove(T obj) {
        entityManager.remove(obj);
    }

    @Override
    public T getById(PK id) {
        return entityManager.find(clazz, id);
    }
}
