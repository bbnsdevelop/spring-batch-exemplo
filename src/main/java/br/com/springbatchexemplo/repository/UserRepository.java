package br.com.springbatchexemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springbatchexemplo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	

}
