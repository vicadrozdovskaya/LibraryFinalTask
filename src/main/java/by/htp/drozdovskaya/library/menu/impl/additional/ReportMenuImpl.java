package by.htp.drozdovskaya.library.menu.impl.additional;

import by.htp.drozdovskaya.library.menu.impl.MainMenu;

public class ReportMenuImpl extends MainMenu {

	private static final String MENU = "1 - ����� � ���������, � ������� ������� ������������� �� �������� ���� " + "\n" + "2 - ����� � ����������� ������  "
			+ "\n" + "3 - ����� � �����������, ������� ��������� �� ����� 2-� � �� ����� 8-�� ���� �� ����� " + "\n" + "4 - ����� �� ��������� \n";

	public ReportMenuImpl(){	}
	@Override
	public void menu() {
		System.out.println(MENU);
	}

}
