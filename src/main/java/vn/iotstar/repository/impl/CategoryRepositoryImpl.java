package vn.iotstar.repository.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.repository.ICategoryRepository;

public class CategoryRepositoryImpl implements ICategoryRepository {

    @Override
    public List<Category> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.createNamedQuery("Category.findAll", Category.class).getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public Category findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.find(Category.class, id);
        } finally {
            enma.close();
        }
    }

    @Override
    public void insert(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(category);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            
            // 1. Tìm đối tượng cần xóa
            Category category = enma.find(Category.class, id);
            
            if (category != null) {
                // 2. Xóa
                enma.remove(category);
            } else {
                throw new Exception("Không tìm thấy Category để xóa!");
            }
            
            // 3. Xác nhận xóa xuống DB
            trans.commit();
            
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Category> findByName(String keyword) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.categoryname LIKE :keyword";
            TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
    @Override
    public List<Category> findByManagerId(int managerId) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.managerId = :mid";
            TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
            query.setParameter("mid", managerId);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }
}