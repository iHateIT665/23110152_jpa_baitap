package vn.iotstar.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.Dao.impl.AbstractEntityDao;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.entity.User;

public class UserDao extends AbstractEntityDao<User> {

    // Constructor gọi đến cha để xác định mình làm việc với bảng User
    public UserDao() {
        super(User.class);
    }

    // Hàm đổi mật khẩu (Viết dựa trên hình ảnh bạn gửi)
    public void changePassword(String email, String oldPassword, String newPassword) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        
        try {
            trans.begin();
            
            // 1. Tìm user theo email
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("email", email);
            
            User user = query.getSingleResult();
            
            if (user == null) {
                throw new Exception("Email không tồn tại!");
            }
            
            // 2. Kiểm tra mật khẩu cũ có đúng không
            // (Lưu ý: Nếu mật khẩu trong DB đã mã hóa thì phải dùng hàm check mã hóa, ở đây so sánh thường)
            if (!user.getPassword().equals(oldPassword)) {
                throw new Exception("Mật khẩu cũ không chính xác!");
            }
            
            // 3. Thiết lập mật khẩu mới
            user.setPassword(newPassword);
            
            // 4. Cập nhật vào database
            enma.merge(user);
            
            trans.commit();
            
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace(); // In lỗi ra console để kiểm tra
            throw e; // Ném lỗi ra ngoài để Controller bắt được và thông báo cho user
        } finally {
            enma.close();
        }
    }
    
    // Hàm tìm user theo email (Rất cần thiết cho chức năng Đăng nhập)
    public User findByEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("email", email);
            
            return query.getSingleResult();
        } catch (Exception e) {
            // Không tìm thấy user hoặc lỗi khác
            return null;
        } finally {
            enma.close();
        }
    }
}