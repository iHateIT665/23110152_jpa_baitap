package vn.iotstar.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@PersistenceContext
public class JPAConfig {
    
    // Khai báo biến static để giữ kết nối, không bị mất đi khi reload trang
    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        // Chỉ tạo nhà máy 1 lần duy nhất khi chạy server
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("dataSource");
        }
        // Các lần sau chỉ việc lấy ra dùng
        return factory.createEntityManager();
    }
    
    // Hàm đóng kết nối khi tắt server (Optional)
    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}