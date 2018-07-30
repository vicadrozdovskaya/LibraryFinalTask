package by.htp.drozdovskaya.library.menu.impl;

import java.util.Scanner;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.menu.IMenu;

public class MainMenu implements IMenu {

	public static final String MENU = "1 - работа с данными о книгах в библиотеке " + "\n"
			+ "2 - работа с данными об авторах в библиотеке " + "\n"
			+ "3 - работа с данными о сотрудниках в библиотеке  " + "\n"
			+ "4 - работа с данными о читательских билетах в библиотеке " + "\n"
			+ "5 - отчет о сотрудниках читавших книги за месяц " + "\n" 
			+ "6 - работа с пользователями библиотеки " + "\n"
			+ "7 - Выход из программы \n";

	protected ILibraryController libraryController;

	public void continueMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nВы хотите продолжить? Д(Да)/Н(Нет)" + "\n");
		String str = sc.nextLine();
		switch (str) {
		case "Н":
		case "н":
			exitMenu();
			break;
		}
	}

	public void exitMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Вы действительно хотите выйти? Д(Да)/Н(Нет)" + "\n");
		String str = sc.nextLine();
		switch (str) {
		case "Д":
		case "д":

			System.out.println("Спасибо, что выбрали нашу библитеку. Приходите к нам ещё");
			System.exit(0);
			break;
		}

	}

	@Override
	public void menu() {
		System.out.println(MENU);
	}

}
