package by.htp.drozdovskaya.library.menu.impl;

import by.htp.drozdovskaya.library.controller.creator.UserControllerCreator;
import by.htp.drozdovskaya.library.run.Read;

public class UserMenuImpl extends MainMenu {

	private static final String MENU = "1 - посмотреть данные о пользователях в библиотеке " + "\n"
			+ "2 - изменить данные о пользователе в библиотеке  " + "\n" + "3 - Выход из программы \n";

	
	public UserMenuImpl() {
		libraryController = new UserControllerCreator().factoryMethod();	
	}


	@Override
	public void menu() {
		Read read = new Read();
		 
		while (true) {
			System.out.println(MENU);
			int choice = read.readNumber();
			switch (choice) {
			case 1:
				libraryController.showAll();
				break;

//			case 2:
//				libraryController.insert();
//				break;
			case 2:
				libraryController.update();
				break;
//			case 4:
//				libraryController.delete();
//				break;
			case 3:
				super.exitMenu();
				break;
			}
			break;
		}
	}

}
