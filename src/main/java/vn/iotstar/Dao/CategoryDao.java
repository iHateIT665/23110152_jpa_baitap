package vn.iotstar.Dao;

import vn.iotstar.Dao.impl.AbstractEntityDao;
import vn.iotstar.entity.Category;

public class CategoryDao extends AbstractEntityDao<Category> {

    public CategoryDao() {
        super(Category.class);
    }
    
    // Bạn có thể viết thêm các hàm riêng cho Category ở đây nếu cần
    // Ví dụ: Tìm category theo tên chẳng hạn
}