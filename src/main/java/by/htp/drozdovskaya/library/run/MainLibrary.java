package by.htp.drozdovskaya.library.run;

import by.htp.drozdovskaya.library.controller.AuthorizationController;
import by.htp.drozdovskaya.library.data.factorymethod.FactoryCreator;
import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.menu.impl.MainMenu;
import by.htp.drozdovskaya.library.menu.impl.additional.AuthorMenuImpl;
import by.htp.drozdovskaya.library.run.config.ApplicationConfigurator;

public class MainLibrary {

	public static void main(String[] args) {
		FactoryCreator.initializeFactory(ApplicationConfigurator.getStorageType());
		AuthorizationController authController = new AuthorizationController();
		MainMenu userMenu = new AuthorMenuImpl();

		boolean isAuthorized = false;
		while (!isAuthorized) {
			User user = authController.authorizeUser();
			if (user.getIdUser() < 0) {
				userMenu.continueMenu();
			} else {
				isAuthorized = true;
				userMenu = authController.loadMenuForAuthorizedUser(user);
				do {
					userMenu.menu();
				} while (true);
			}
		}

		
		

	}
}
