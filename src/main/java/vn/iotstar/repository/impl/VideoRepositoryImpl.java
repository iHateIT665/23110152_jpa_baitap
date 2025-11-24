package vn.iotstar.repository.impl; // Chú ý package đuôi .impl

import java.util.List; // Import List để không bị lỗi

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.entity.Video;
import vn.iotstar.repository.IVideoRepository;

// 1. Phải implements IVideoRepository
// 2. Nên đặt tên là VideoRepositoryImpl thay vì VideoRepository
public class VideoRepositoryImpl implements IVideoRepository {
	@Override
	public List<Video> findByCategoryId(int categoryId) {
	    EntityManager enma = JPAConfig.getEntityManager();
	    try {
	        // Câu lệnh JPQL lấy video theo categoryId
	        String jpql = "SELECT v FROM Video v WHERE v.category.categoryId = :cateId";
	        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
	        query.setParameter("cateId", categoryId);
	        return query.getResultList();
	    } finally {
	        enma.close();
	    }
	}
    @Override
    public void insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(video);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(video);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(String videoId) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Video video = enma.find(Video.class, videoId);
            if (video != null) {
                enma.remove(video);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Video findById(String videoId) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Video.class, videoId);
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.createNamedQuery("Video.findAll", Video.class).getResultList();
    }

    // --- Hàm gây lỗi của bạn đã được sửa ---
    @Override
    public List<Video> findByTitle(String title) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :title";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

	
}