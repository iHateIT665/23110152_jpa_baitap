package vn.iotstar.Dao;

import vn.iotstar.Dao.impl.AbstractEntityDao;
import vn.iotstar.entity.Video;

public class VideoDao extends AbstractEntityDao<Video> {

    public VideoDao() {
        super(Video.class);
    }
    
    // Ví dụ viết thêm hàm tìm Video được xem nhiều nhất
}