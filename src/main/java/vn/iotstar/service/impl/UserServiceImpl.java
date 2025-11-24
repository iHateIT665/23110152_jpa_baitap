package vn.iotstar.service.impl;

import java.util.List;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.repository.impl.UserRepositoryImpl;
import vn.iotstar.service.UserService;

public class UserServiceImpl implements UserService {
    
    // Khai báo Repository để gọi xuống tầng dữ liệu
    UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User login(String username, String password) {
        User user = this.findByUsername(username);
        
        // Kiểm tra password
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        try {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password); 
            newUser.setEmail(email);
            newUser.setFullname(fullname);
            newUser.setPhone(phone);
            
            newUser.setRoleid(3); 
            newUser.setStatus(1); 
            newUser.setImages("default.png"); 
            newUser.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
            
            userRepository.insert(newUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userRepository.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userRepository.checkExistPhone(phone);
    }
  
    @Override
    public boolean updatePassword(String email, String newPassword) {
        return userRepository.updatePassword(email, newPassword);
    }

    // --- SỬA LẠI 2 HÀM NÀY ---

    @Override
    public void update(User user) {
        // Gọi xuống Repository để lưu vào DB
        userRepository.update(user); 
    }

    @Override
    public User findById(int id) {
        // Gọi xuống Repository để lấy dữ liệu
        return userRepository.findById(id);
    }
}