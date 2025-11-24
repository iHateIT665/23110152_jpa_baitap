package vn.iotstar.repository;

import java.util.List;
import vn.iotstar.entity.Video;

public interface IVideoRepository {
    void insert(Video video);
    void update(Video video);
    void delete(String videoId) throws Exception;
    Video findById(String videoId);
    List<Video> findAll();
    List<Video> findByTitle(String title);
    List<Video> findByCategoryId(int categoryId);
}