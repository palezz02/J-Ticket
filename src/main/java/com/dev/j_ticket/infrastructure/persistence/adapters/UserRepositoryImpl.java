package com.dev.j_ticket.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dev.j_ticket.domain.models.User;
import com.dev.j_ticket.domain.repositories.UserRepository;
import com.dev.j_ticket.infrastructure.persistence.jpa.SpringDataUserRepository;

/**
 * Persistence Adapter for User entities.
 * This class implements the EventRepository by wrapping the 
 * SpringDataEventRepository. It acts as a bridge between the 
 * domain layer and the Spring Data JPA infrastructure, ensuring that 
 * the business logic is decoupled from the underlying ORM framework.
 */
@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private final SpringDataUserRepository jpaRepo;

	public UserRepositoryImpl(SpringDataUserRepository jpaRepo) {
		this.jpaRepo = jpaRepo;
	}

	/**
     * Delegates the lookup of an user by email to the JPA repository.
     * @param email, the unique database identifier for user.
     */
	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

    /**
     * Fetches all users by delegating the call to the JPA repository.
     * @return a list of all users entities found in the database.
     */
	@Override
	public List<User> findAll() {
		return jpaRepo.findAll();
	}

	/**
     * Executes the persistence of a user to the PostgreSQL database.
     * @param the user to persist.
     * @return the saved user including the generated database primary key.
     */
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
