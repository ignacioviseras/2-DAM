package dao;

import java.util.List;

import beans.Users;

public interface IntUsersDao {

	public List<Users> userList();
	public String findByName(String name);
}
