package by.htp.drozdovskaya.library.menu.impl.additional;

import by.htp.drozdovskaya.library.menu.impl.MainMenu;

public class ReportMenuImpl extends MainMenu {

	private static final String MENU = "1 - отчёт о читателях, у которых имеется задолженность по возврату книг " + "\n" + "2 - отчёт о прочитанных книгах  "
			+ "\n" + "3 - отчёт о сотрудниках, которые прочитали не менее 2-х и не более 8-ми книг за месяц " + "\n" + "4 - Выход из программы \n";

	public ReportMenuImpl(){	}
	@Override
	public void menu() {
		System.out.println(MENU);
	}

}
