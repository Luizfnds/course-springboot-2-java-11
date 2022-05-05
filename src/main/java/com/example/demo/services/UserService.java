package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.resources.exception.DatabaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User FindById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User user){
		return repository.save(user);
	}

	public void delete(Long id){
			try {
				repository.deleteById(id);
			} catch (EmptyResultDataAccessException e){
				throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException e){
                throw new DatabaseException(e.getMessage());
            }
		}

	public User update(Long id, User user){
		try {
			User entity = repository.getOne(id);
			updateData(entity, user);
			return repository.save(entity);
		} catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
}
