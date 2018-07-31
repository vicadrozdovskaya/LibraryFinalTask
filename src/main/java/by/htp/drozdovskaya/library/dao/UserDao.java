package by.htp.drozdovskaya.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.htp.drozdovskaya.library.entity.User;

public interface UserDao extends IDao<User> {

	User get(int id);

	boolean insert(User user);

	boolean update(User user);

	boolean delete(User user);

	List<User> getAll();
	
	User getUser(ResultSet rs) throws SQLException;
}
