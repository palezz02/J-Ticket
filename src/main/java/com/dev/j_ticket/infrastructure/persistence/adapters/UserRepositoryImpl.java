package com.dev.j_ticket.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dev.j_ticket.domain.models.User;
import com.dev.j_ticket.domain.repositories.UserRepository;
import com.dev.j_ticket.infrastructure.persistence.jpa.SpringDataUserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private final SpringDataUserRepository jpaRepo;

	public UserRepositoryImpl(SpringDataUserRepository jpaRepo) {
		this.jpaRepo = jpaRepo;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> findAll() {
		return jpaRepo.findAll();
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
