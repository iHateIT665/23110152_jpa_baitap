package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.entity.Category;
import vn.iotstar.repository.ICategoryRepository;
import vn.iotstar.repository.impl.CategoryRepositoryImpl;
import vn.iotstar.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

    // Gọi xuống tầng Repository (Tầng thao tác dữ liệu)
    ICategoryRepository cateRepo = new CategoryRepositoryImpl();

    @Override
    public List<Category> findAll() {
        return cateRepo.findAll();
    }

    @Override
    public Category findById(int id) {
        return cateRepo.findById(id);
    }

    @Override
    public void insert(Category category) {
        cateRepo.insert(category);
    }

    @Override
    public void update(Category category) {
        cateRepo.update(category);
    }

    @Override
    public void delete(int id) throws Exception {
        cateRepo.delete(id);
    }

    @Override
    public List<Category> findByName(String keyword) {
        return cateRepo.findByName(keyword);
    }
    @Override
    public List<Category> findByManagerId(int managerId) {
        return cateRepo.findByManagerId(managerId);
        
    }
}