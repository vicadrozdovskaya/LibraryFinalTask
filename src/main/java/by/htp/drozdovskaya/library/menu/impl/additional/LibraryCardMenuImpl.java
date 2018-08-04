package by.htp.drozdovskaya.library.menu.impl.additional;

import by.htp.drozdovskaya.library.menu.impl.MainMenu;

public class LibraryCardMenuImpl extends MainMenu {
	
	private static final String MENU = "1 - посмотреть данные о читательских билетах в библиотеке " + "\n" + "2 - добавить книгу в читательский билет "
			+ "\n" + "3 - вернуть книгу " + "\n" + "4 - Выход из программы \n";

	public LibraryCardMenuImpl(){	}
	
	@Override
	public void menu() {
			System.out.println(MENU);
	}


}
