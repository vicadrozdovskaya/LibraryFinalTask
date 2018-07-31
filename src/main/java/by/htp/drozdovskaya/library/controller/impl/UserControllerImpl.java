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
		System.out.println("������� �����");
		String login = read.readLine();
		System.out.println("������� ������");
		String password = read.readLine();
		User user = new User();
		user.setLogin(login);
		;
		user.setPassword(password);
		user.setRole(0);
		user.setIdUser(userDao.getAll().size());
		if (userDao.insert(user)) {
			System.out.println("User ������� ��������");
			return true;
		} else {
			System.out.println("��������� ������ ��� ����������, ���������� �����");
			return false;
		}
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("�������� User ��� ��������������: ������� ��� ID");
		int id_user = read.readNumber();
		System.out.println("������� login");
		String login = read.readLine();
		System.out.println("������� password");
		String password = read.readLine();
		System.out.println("������� ����� ����: ��������(0)/������������(1)");
		int role = read.readNumber();
		User user = new User();
		user.setIdUser(id_user);
		user.setLogin(login);
		user.setPassword(password);
		user.setRole(role);
		if (userDao.update(user)) {
			System.out.println("����� ������� �������");
			return true;
		} else {
			System.out.println("��������� ������ ��� ���������, ���������� �����");
			return false;
		}
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("�������� User ��� ��������: ������� ��� ID");
		int id_user = read.readNumber();
		User user = userDao.get(id_user);
		if (userDao.delete(user)) {
			System.out.println("User ������� ������");
			return true;
		} else {
			System.out.println("��������� ������ ��� ��������, ���������� �����");
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
