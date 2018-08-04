package by.htp.drozdovskaya.library.menu.impl;

import by.htp.drozdovskaya.library.controller.LibrarianController;
import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.run.Read;

public class LibrarianMenuImpl extends MainMenu {
	
	private static final String MENU = "1 - работа с данными о книгах в библиотеке " + "\n"
			+ "2 - работа с данными об авторах в библиотеке " + "\n"
			+ "3 - работа с данными о сотрудниках в библиотеке  " + "\n"
			+ "4 - работа с данными о читательских билетах в библиотеке " + "\n"
			+ "5 - отчеты " + "\n" 
			+ "6 - Выход из программы \n";
	
	private LibrarianController librarianController;
	
	public LibrarianMenuImpl(User user) {
		super(user);
		this.librarianController = new LibrarianController();		
	}
	
	@Override
	public void menu() {
		while (true) {
			System.out.println(MENU);
			Read read = new Read();
			int choice = read.readNumber();
			switch (choice) {
			case 1: {
				librarianController.workWithBooks();
				break;
			}
			case 2: {
				librarianController.workWithAuthor();
				break;
			}
			case 3: {
				librarianController.workWithEmployee();
				break;
			}
			case 4: {
				librarianController.workWithLibrarianCard();
				break;
			}
			case 5: {
				librarianController.workWithReports();
				break;
			}
			case 6: {
				super.exitMenu();
				break;
			}
			}
		}
		
	}

}
