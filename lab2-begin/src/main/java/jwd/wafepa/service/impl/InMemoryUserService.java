package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jwd.wafepa.model.User;
import jwd.wafepa.service.UserService;

@Service
public class InMemoryUserService implements UserService {

	private Map<Long, User> users = new HashMap<>();
	private long nextId = 1L;

	@Override
	public User findOne(Long id) {
		return users.get(id);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<User>(users.values());
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(nextId);
			nextId += 1L;
		}
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public List<User> save(List<User> users) {
		List<User> usersRet = new ArrayList<>();
		for (User a : users) {
			User saved = save(a);
			if (saved != null) {
				usersRet.add(saved);
			}
		}

		return usersRet;
	}

	@Override
	public User delete(Long id) {
		User user = users.get(id);
		if (user != null) {
			users.remove(id);
		}
		return user;
	}

	@Override
	public void delete(List<Long> ids) {
		for (Long id : ids) {
			delete(id);
		}
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		List<User> usersRet = new ArrayList<>();
		for (User u : users.values()) {
			if (u.getFirstName().equals(firstName)) {
				usersRet.add(u);
			}
		}
		return usersRet;
	}

}
