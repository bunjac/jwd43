package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.User;

public interface UserService {

	User findOne(Long id);

	List<User> findAll();

	User save(User user);

	Page<User> findAll(int page);

	// za korisnika se u ovom primeru (bez
	// specijalnog razloga) koristi
	// varijanta brisanja koja NE vraća entitet
	void delete(Long id);

}
