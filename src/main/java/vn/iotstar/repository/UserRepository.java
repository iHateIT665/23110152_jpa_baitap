package vn.iotstar.repository;

import java.util.List;

import vn.iotstar.entity.User;

public interface UserRepository {

	List<User> findAll();

	int count();

	List<User> findAll(int page, int pagesize);

	List<User> findByFullname(String fullname);

	void delete(int id) throws Exception;

	void update(User user);

	void insert(User user);

	User findById(int id);

	User findByEmail(String email);

	User findByUsername(String username);

	boolean checkExistPhone(String phone);

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	boolean updatePassword(String email, String newPassword);

}
