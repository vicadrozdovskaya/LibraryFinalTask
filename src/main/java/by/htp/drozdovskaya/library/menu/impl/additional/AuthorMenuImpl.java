package by.htp.drozdovskaya.library.menu.impl.additional;

import by.htp.drozdovskaya.library.menu.impl.MainMenu;

public class AuthorMenuImpl extends MainMenu {
	private static final String MENU = "1 - посмотреть данные об авторах в библиотеке " + "\n" + "2 - добавить автора в библиотеку "
			+ "\n" + "3 - изменить данные об авторе в библиотеку  " + "\n" + "4 - удалить автора из библиотеки "
			+ "\n" + "5 - Выход из программы \n";

	public AuthorMenuImpl() {}
	
	@Override
	public void menu() {
			System.out.println(MENU);
	
	}


}
