package com.revature.repository;

import com.revature.model.User;

public interface UserDAO {

	User getUser(long id);

	User getUser(String name);

	boolean updateUser(User u, double n);
}
