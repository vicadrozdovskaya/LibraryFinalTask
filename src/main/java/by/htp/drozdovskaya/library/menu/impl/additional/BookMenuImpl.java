package by.htp.drozdovskaya.library.menu.impl.additional;

import by.htp.drozdovskaya.library.menu.impl.MainMenu;


public class BookMenuImpl extends MainMenu {

	private static final String MENU = "1 - ���������� ������ � ������ � ���������� " + "\n"
			+ "2 - �������� ����� � ���������� " + "\n" + "3 - �������� ������ � ����� � ����������  " + "\n"
			+ "4 - ������� ����� �� ���������� " + "\n"
			+ "5 - ����� �� ��������� \n";

	public BookMenuImpl() {
	}

	@Override
	public void menu() {
		System.out.println(MENU);
	}

}
