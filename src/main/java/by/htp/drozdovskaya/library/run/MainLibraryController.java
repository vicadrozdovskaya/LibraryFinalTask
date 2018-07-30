package by.htp.drozdovskaya.library.run;

import by.htp.drozdovskaya.library.menu.IMenu;
import by.htp.drozdovskaya.library.menu.impl.AuthorMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.AuthorizationMenu;
import by.htp.drozdovskaya.library.menu.impl.BookMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.EmployeeMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.MainMenu;
import by.htp.drozdovskaya.library.menu.impl.UserMenuImpl;


public class MainLibraryController {

	public static void main(String[] args) {

		Read read = new Read();
		MainMenu mMenu = new MainMenu();
		IMenu bmenu = new BookMenuImpl();
		AuthorizationMenu authorizationMenu = new AuthorizationMenu();
		while(true) {
		
		if( authorizationMenu.isAuthorized()) {

		do {
			mMenu.menu();			
			int choice = read.readNumber();
			switch (choice) {

			case 1:
				bmenu.menu();
				mMenu.continueMenu();
				break;
			case 2:
				IMenu amenu = new AuthorMenuImpl();
					amenu.menu();					
					mMenu.continueMenu();
				break;

			case 3:
				IMenu eMenu = new EmployeeMenuImpl();
				eMenu.menu();
				mMenu.continueMenu();

				break;
			case 4:

				mMenu.continueMenu();
				break;
			case 5:

				mMenu.continueMenu();
				break;
			case 6:
				IMenu uMenu = new UserMenuImpl();
				uMenu.menu();
				mMenu.continueMenu();
				break;
			case 7:
				mMenu.exitMenu();

			}
		} while (true);
		}else {
			System.out.println("такого пользователя нет");
			mMenu.continueMenu();
		}
		}
	}

}
