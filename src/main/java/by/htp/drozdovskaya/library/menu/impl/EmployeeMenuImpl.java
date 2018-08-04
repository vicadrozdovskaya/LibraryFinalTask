package by.htp.drozdovskaya.library.menu.impl;

import by.htp.drozdovskaya.library.controller.EmployeeController;
import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.run.Read;

public class EmployeeMenuImpl extends MainMenu {

	private static final String MENU = "1 - посмотреть каталог книг " + "\n"
			+ "2 - просмотреть подробную информацию о конкретной книге  " + "\n" + "3 - Выход из программы \n";

	private boolean isNotificationShowed = false;

	private EmployeeController employeeController;

	public EmployeeMenuImpl(User user) {
		super(user);
		employeeController = new EmployeeController();
	}

	@Override
	public void menu() {
		while (true) {
			notifyUserAboutDebt();
			System.out.println(MENU);
			Read read = new Read();
			int choice = read.readNumber();
			switch (choice) {
			case 1: {
				employeeController.showListOfBooks();
				break;
			}
			case 2: {
				System.out.println("Введите код книги");
				int idBook = read.readNumber();
				employeeController.showDetailOfBook(idBook);
				break;
			}
			case 3: {
				super.exitMenu();
				break;
			}
			}
		}

	}

	private void notifyUserAboutDebt() {
		if (!isNotificationShowed) {
			employeeController.showNotificationAboutDebts(this.getUser());
			isNotificationShowed = true;
		}
	}
}
