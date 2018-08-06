package by.htp.drozdovskaya.library.dao.collections;

import java.util.ArrayList;
import java.util.List;

import by.htp.drozdovskaya.library.dao.IUserDao;
import by.htp.drozdovskaya.library.entity.User;

public class UserCollectionImpl implements IUserDao {

	@Override
	public User get(int id) {
		List<User> users = LibraryCollectionsStorage.getUsers();
		User returnUser = new User();
		for (User user : users) {
			if (user.getIdUser() == id) {
				returnUser.setIdUser(user.getIdUser());
				returnUser.setLogin(user.getLogin());
				returnUser.setPassword(user.getPassword());
				returnUser.setRole(user.getRole());
			}
		}
		return returnUser;
	}

	@Override
	public boolean insert(User user) {
		List<User> users = new ArrayList<>();
		users.addAll(LibraryCollectionsStorage.getUsers());
		user.setIdUser(LibraryCollectionsStorage.getNextUserId());
		boolean result = users.add(user);
		LibraryCollectionsStorage.setUsers(users);
		return result;
	}

	@Override
	public boolean update(User user) {
		List<User> users = new ArrayList<>();
		users.addAll(LibraryCollectionsStorage.getUsers());
		for (User userThis : users) {
			if (userThis.getIdUser() == user.getIdUser()) {
				userThis.setIdUser(user.getIdUser());
				userThis.setLogin(user.getLogin());
				userThis.setPassword(user.getPassword());
				userThis.setRole(user.getRole());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(User user) {
		List<User> users = new ArrayList<>();
		users.addAll(LibraryCollectionsStorage.getUsers());
		boolean result = users.remove(user);
		LibraryCollectionsStorage.setUsers(users);
		return result;
	}

	@Override
	public List<User> getAll() {
		List<User> users = LibraryCollectionsStorage.getUsers();
		return users;
	}

	@Override
	public User getUser(String login) {
		List<User> users = LibraryCollectionsStorage.getUsers();
		User returnUser = new User();
		for (User user : users) {
			if (user.getLogin().equals(login)) {
				returnUser.setIdUser(user.getIdUser());
				returnUser.setLogin(user.getLogin());
				returnUser.setPassword(user.getPassword());
				returnUser.setRole(user.getRole());
			}
		}
		return returnUser;
	}

}
