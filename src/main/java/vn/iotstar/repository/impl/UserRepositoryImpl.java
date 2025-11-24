package vn.iotstar.repository.impl;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "Select u from User u";
        TypedQuery<User> query = enma.createQuery(jpql, User.class);
        return query.getResultList();
    }
    @Override
    public User findByUsername(String username) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :username";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            // Không tìm thấy user sẽ rơi vào đây
            return null;
        } finally {
            enma.close();
        }
    }


    @Override
	public User findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        User user = enma.find(User.class, id);
        return user;
    }


    

 
    @Override
	public void delete(int id) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            User user = enma.find(User.class, id);
            if (user != null) {
                enma.remove(user);
            } else {
                throw new Exception("Không tìm thấy User có ID: " + id);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

 
    @Override
	public List<User> findByFullname(String fullname) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.fullname LIKE :fullname";
        TypedQuery<User> query = enma.createQuery(jpql, User.class);
        query.setParameter("fullname", "%" + fullname + "%");
        return query.getResultList();
    }


    @Override
	public List<User> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "Select u from User u";
        TypedQuery<User> query = enma.createQuery(jpql, User.class);
        query.setFirstResult((page) * pagesize);
        query.setMaxResults(pagesize);
        return query.getResultList();
    }

   
    @Override
	public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT count(u) FROM User u";
        Query query = enma.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
 // File: vn.iotstar.repository.impl.UserRepositoryImpl.java

    
    @Override
    public void insert(User user) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(user); // Hàm persist dùng để thêm mới trong JPA
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
            Long count = enma.createQuery(jpql, Long.class)
                             .setParameter("email", email)
                             .getSingleResult();
            return count > 0;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistUsername(String username) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT COUNT(u) FROM User u WHERE u.username = :username";
            Long count = enma.createQuery(jpql, Long.class)
                             .setParameter("username", username)
                             .getSingleResult();
            return count > 0;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistPhone(String phone) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT COUNT(u) FROM User u WHERE u.phone = :phone";
            Long count = enma.createQuery(jpql, Long.class)
                             .setParameter("phone", phone)
                             .getSingleResult();
            return count > 0;
        } finally {
            enma.close();
        }
    }
    @Override
    public boolean updatePassword(String email, String newPassword) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            // 1. Tìm user theo email
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            User user = enma.createQuery(jpql, User.class)
                            .setParameter("email", email)
                            .getSingleResult();
            
            if (user != null) {
                // 2. Set mật khẩu mới
                user.setPassword(newPassword);
                // 3. Cập nhật (Commit sẽ tự động lưu thay đổi của đối tượng đang được quản lý)
                enma.merge(user);
                trans.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            return false;
        } finally {
            enma.close();
        }
    }
    
    @Override
    public User findByEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            return enma.createQuery(jpql, User.class)
                       .setParameter("email", email)
                       .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            enma.close();
        }
    }
    @Override
    public void update(User user) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(user); // Hàm merge để update
            
            trans.commit();   // <--- KIỂM TRA KỸ DÒNG NÀY (Nếu thiếu thì DB sẽ không lưu)
            
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
}