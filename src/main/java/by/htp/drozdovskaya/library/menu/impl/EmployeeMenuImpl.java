package by.htp.drozdovskaya.library.menu.impl;

import by.htp.drozdovskaya.library.controller.creator.EmployeeControllerCreator;
import by.htp.drozdovskaya.library.run.Read;

public class EmployeeMenuImpl extends MainMenu {

	public static final String MENU = "1 - посмотреть данные о сотрудниках в библиотеке " + "\n"
			+ "2 - добавить сотрудника в библиотеку " + "\n" + "3 - изменить данные о сотруднике в библиотеку  " + "\n"
			+ "4 - удалить сотрудника из библиотеки " + "\n" + "5 - отчет о сотрудниках читавших книги за месяц " + "\n"
			+ "6 - Выход из программы \n";

	public EmployeeMenuImpl() {

		libraryController = new EmployeeControllerCreator().factoryMethod();
	}

	@Override
	public void menu() {
		Read read = new Read();

		while (true) {
			System.out.println(MENU);
			int choiceEmpl = read.readNumber();
			switch (choiceEmpl) {
			case 1:
				libraryController.showAll();
				break;
			case 2:
				libraryController.insert();
				break;
			case 3:
				libraryController.update();
				break;
			case 4:
				libraryController.delete();
				break;
			case 5:

				break;
			case 6:
				super.exitMenu();
				break;
			}

			break;
		}

	}

}
