package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.entity.Video;
import vn.iotstar.repository.IVideoRepository;
import vn.iotstar.repository.impl.VideoRepositoryImpl; // Import class mới
import vn.iotstar.service.IVideoService;

public class VideoServiceImpl implements IVideoService {

    // Sửa dòng này: Dùng VideoRepositoryImpl
    IVideoRepository videoRepo = new VideoRepositoryImpl(); 

    @Override
    public void insert(Video video) {
        videoRepo.insert(video);
    }

    @Override
    public void update(Video video) {
        videoRepo.update(video);
    }

    @Override
    public void delete(String videoId) throws Exception {
        videoRepo.delete(videoId);
    }

    @Override
    public Video findById(String videoId) {
        return videoRepo.findById(videoId);
    }

    @Override
    public List<Video> findAll() {
        return videoRepo.findAll();
    }

    @Override
    public List<Video> findByTitle(String title) {
        return videoRepo.findByTitle(title);
    }
}