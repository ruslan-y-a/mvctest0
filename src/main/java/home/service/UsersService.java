package home.service;

import java.util.List;

import home.entities.Users;

public interface UsersService {
	public List<Users> findAll();
	public Users save(Users users);
	public Users findById(Long id);
}
