package vn.iotstar.Dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import vn.iotstar.config.JPAConfig;

// <T> là kiểu dữ liệu chung (Generic), sau này truyền User hay Category vào đều được
public abstract class AbstractEntityDao<T> {

    private Class<T> entityClass;

    // Constructor nhận vào kiểu class (ví dụ User.class)
    public AbstractEntityDao(Class<T> cls) {
        this.entityClass = cls;
    }

    public void insert(T entity) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(entity);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public void update(T entity) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(entity);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public void delete(Object id) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            T entity = enma.find(entityClass, id);
            if (entity != null) {
                enma.remove(entity);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e; // Ném lỗi ra để Controller biết mà xử lý
        } finally {
            enma.close();
        }
    }

    public T findById(Object id) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.find(entityClass, id);
        } finally {
            enma.close();
        }
    }
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            // Sử dụng Criteria để tạo câu query động "Select t from T t"
            CriteriaBuilder builder = enma.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);
            query.select(root);
            
            TypedQuery<T> q = enma.createQuery(query);
            return q.getResultList();
        } finally {
            enma.close();
        }
    }

    public Long count() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            CriteriaBuilder builder = enma.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<T> root = query.from(entityClass);
            query.select(builder.count(root));
            
            return enma.createQuery(query).getSingleResult();
        } finally {
            enma.close();
        }
    }
    
    public List<T> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            CriteriaBuilder builder = enma.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);
            query.select(root);
            
            TypedQuery<T> q = enma.createQuery(query);
            q.setFirstResult(page * pagesize);
            q.setMaxResults(pagesize);
            return q.getResultList();
        } finally {
            enma.close();
        }
    }
}