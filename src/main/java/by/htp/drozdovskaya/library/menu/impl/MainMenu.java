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
		System.out.println("\n�� ������ ����������? �(��)/�(���)" + "\n");
		String str = sc.nextLine();
		switch (str) {
		case "�":
		case "�":
			exitMenu();
			break;
		}
	}

	public void exitMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�� ������������� ������ �����? �(��)/�(���)" + "\n");
		String str = sc.nextLine();
		switch (str) {
		case "�":
		case "�":

			System.out.println("�������, ��� ������� ���� ���������. ��������� � ��� ���");
			System.exit(0);
			break;
		}

	}

	public User getUser() {
		return user;
	}

	

}
