package by.htp.drozdovskaya.library.menu.impl.additional;

import by.htp.drozdovskaya.library.menu.impl.MainMenu;

public class EmployeeDataMenuImpl extends MainMenu {

	public static final String MENU = "1 - посмотреть данные о сотрудниках в библиотеке " + "\n"
			+ "2 - добавить сотрудника в библиотеку " + "\n" + "3 - изменить данные о сотруднике в библиотеку  " + "\n"
			+ "4 - удалить сотрудника из библиотеки " + "\n" + "5 - Выход из программы \n";

	public EmployeeDataMenuImpl() {
	}

	@Override
	public void menu() {
		System.out.println(MENU);
	}

}
