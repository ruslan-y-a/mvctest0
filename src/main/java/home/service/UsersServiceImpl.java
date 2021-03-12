package home.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import home.entities.Users;
import home.repositories.UsersRepo;

@Service
@Component
public class UsersServiceImpl implements UsersService{
	@Autowired UsersRepo usersRepo;
	
	@Override
	public List<Users> findAll(){
		List<Users> list = new LinkedList<>();
		usersRepo.findAll().forEach(x -> list.add(x));
		return list;
	}
	@Override
	public Users save(Users users) {
		return usersRepo.save(users);
	}
	@Override
	public Users findById(Long id) {
		return usersRepo.findById(id).orElse(null);
	}
}
