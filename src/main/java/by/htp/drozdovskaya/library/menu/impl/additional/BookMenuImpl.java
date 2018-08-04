package by.htp.drozdovskaya.library.menu.impl.additional;

import by.htp.drozdovskaya.library.menu.impl.MainMenu;


public class BookMenuImpl extends MainMenu {

	private static final String MENU = "1 - посмотреть данные о книгах в библиотеке " + "\n"
			+ "2 - добавить книгу в библиотеку " + "\n" + "3 - изменить данные о книге в библиотеку  " + "\n"
			+ "4 - удалить книгу из библиотеки " + "\n"
			+ "5 - Выход из программы \n";

	public BookMenuImpl() {
	}

	@Override
	public void menu() {
		System.out.println(MENU);
	}

}
