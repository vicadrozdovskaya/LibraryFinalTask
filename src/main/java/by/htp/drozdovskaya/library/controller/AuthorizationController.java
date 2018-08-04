package by.htp.drozdovskaya.library.controller;

import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.logic.UserLogic;
import by.htp.drozdovskaya.library.menu.impl.EmployeeMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.LibrarianMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.MainMenu;
import by.htp.drozdovskaya.library.run.Read;

public class AuthorizationController {
	private UserLogic userLogic;

	public AuthorizationController() {
		this.userLogic = new UserLogic();
	}

	public User authorizeUser() {
		Read read = new Read();
		System.out.println("Для работы необходимо авторизоваться: \n Введите логин:");
		String login = read.readLine();
		System.out.println(" Введите пароль:");
		String password = read.readLine();
		User user = userLogic.authorizeUserByLoginAndPassword(login, password);
		if (user.getIdUser() < 0) {
			System.out.println("Пользователя не существует");
		}
		return user;
	}

	public MainMenu loadMenuForAuthorizedUser(User user) {
		MainMenu menu;
		if (user.getRole() == 0) {
			menu = new EmployeeMenuImpl(user);
		} else {
			menu = new LibrarianMenuImpl(user);
		}
		return menu;
	}
}
