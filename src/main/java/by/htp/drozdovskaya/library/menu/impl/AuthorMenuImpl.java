package by.htp.drozdovskaya.library.menu.impl;

import by.htp.drozdovskaya.library.controller.creator.AuthorControllerCreator;
import by.htp.drozdovskaya.library.run.Read;



public class AuthorMenuImpl extends MainMenu {
	private static final String MENU = "1 - ���������� ������ �� ������� � ���������� " + "\n" + "2 - �������� ������ � ���������� "
			+ "\n" + "3 - �������� ������ �� ������ � ����������  " + "\n" + "4 - ������� ������ �� ���������� "
			+ "\n" + "5 - ����� �� ��������� \n";

	public AuthorMenuImpl() {
		libraryController = new AuthorControllerCreator().factoryMethod(); 
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
				super.exitMenu();
				break;
			}
			break;
		}
		
	}


}
