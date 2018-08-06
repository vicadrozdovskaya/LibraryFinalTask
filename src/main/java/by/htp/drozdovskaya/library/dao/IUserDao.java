package by.htp.drozdovskaya.library.dao;

import java.util.List;

import by.htp.drozdovskaya.library.entity.User;

public interface IUserDao extends IDao<User> {

	User get(int id);

	boolean insert(User user);

	boolean update(User user);

	boolean delete(User user);

	List<User> getAll();
	
	User getUser(String login);
}
