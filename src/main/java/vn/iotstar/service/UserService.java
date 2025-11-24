package vn.iotstar.service;
//import java
import java.util.List;

import vn.iotstar.entity.User;

public interface UserService {


	List<User> findAll();

	User login(String username, String password);

	User findByUsername(String username);

	boolean register(String username, String password, String email, String fullname, String phone);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	boolean updatePassword(String email, String newPassword);

	User findByEmail(String email);

	void update(User currentUser);

	User findById(int userId);
}
