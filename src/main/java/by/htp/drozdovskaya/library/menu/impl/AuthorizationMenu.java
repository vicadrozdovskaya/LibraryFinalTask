package by.htp.drozdovskaya.library.menu.impl;

import by.htp.drozdovskaya.library.controller.IAuthorizationController;
import by.htp.drozdovskaya.library.controller.creator.UserControllerCreator;
import by.htp.drozdovskaya.library.run.Read;

public class AuthorizationMenu{
	
	private IAuthorizationController authorizationController;
	
	
	public AuthorizationMenu() {
		authorizationController = new UserControllerCreator().authorizationMethod();
	}
	
	public boolean isAuthorized() {
		Read read = new Read();
		System.out.println("ƒл€ работы необходимо авторизоватьс€: \n ¬ведите логин:");
		String login = read.readLine();
		System.out.println("¬ведите пароль:");
		String password = read.readLine();
		return authorizationController.authorization(login, password);
	}


}
