package by.htp.drozdovskaya.library.menu.impl;

import java.util.Scanner;

import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.menu.IMenu;

public abstract class MainMenu implements IMenu {	

	private User user;

	public MainMenu() {}
	public MainMenu(User user) {
		this.user = user;
	}

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

	public User getUser() {
		return user;
	}

	

}
