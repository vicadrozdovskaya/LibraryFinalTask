package by.htp.drozdovskaya.library.controller.impl;

import java.util.List;

import by.htp.drozdovskaya.library.controller.IAuthorizationController;
import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.dao.IDao;
import by.htp.drozdovskaya.library.dao.creator.UserDaoCreator;
import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.run.Read;

public class UserControllerImpl implements ILibraryController, IAuthorizationController {

	private IDao<User> userDao;
	private Read read;

	public UserControllerImpl() {
		userDao = new UserDaoCreator().factoryMethod();
		read = new Read();
	}

	@Override
	public void showAll() {
		for (User u : userDao.getAll()) {
			System.out.println(u);
		}
	}

	@Override
	public boolean insert() {
		System.out.println("Введите логин");
		String login = read.readLine();
		System.out.println("Введите пароль");
		String password = read.readLine();
		User user = new User();
		user.setLogin(login);
		;
		user.setPassword(password);
		user.setRole(0);
		user.setIdUser(userDao.getAll().size());
		if (userDao.insert(user)) {
			System.out.println("User успешно добавлен");
			return true;
		} else {
			System.out.println("Произошла ошибка при добавлении, попрубуйте снова");
			return false;
		}
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("Выберите User для редактирования: введите его ID");
		int id_user = read.readNumber();
		System.out.println("Введите login");
		String login = read.readLine();
		System.out.println("Введите password");
		String password = read.readLine();
		System.out.println("Введите число роли: читатель(0)/библиотекарь(1)");
		int role = read.readNumber();
		User user = new User();
		user.setIdUser(id_user);
		user.setLogin(login);
		user.setPassword(password);
		user.setRole(role);
		if (userDao.update(user)) {
			System.out.println("Автор успешно изменен");
			return true;
		} else {
			System.out.println("Произошла ошибка при изменении, попрубуйте снова");
			return false;
		}
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("Выберите User для удаления: введите его ID");
		int id_user = read.readNumber();
		User user = userDao.get(id_user);
		if (userDao.delete(user)) {
			System.out.println("User успешно удален");
			return true;
		} else {
			System.out.println("Произошла ошибка при удалении, попрубуйте снова");
			return false;
		}
	}

	@Override
	public boolean authorization(String login, String password) {
		List<User> users = userDao.getAll();
		for (User u : users) {
			if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

}
