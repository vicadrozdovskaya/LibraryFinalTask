package by.htp.drozdovskaya.library.menu.impl;

import java.util.List;

import by.htp.drozdovskaya.library.controller.creator.LibraryCardControllerCreator;
import by.htp.drozdovskaya.library.dao.impl.ReportDaoImpl;
import by.htp.drozdovskaya.library.dao.interfaces.ReportDao;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.run.Read;

public class LibraryCardMenuImpl extends MainMenu {
	
	private static final String MENU = "1 - посмотреть данные о читательских билетах в библиотеке " + "\n" + "2 - добавить книгу в читательский билет "
			+ "\n" + "3 - изменить данные в читательском билете " + "\n" + "4 - удалить читательский билет из библиотеки " + "\n"
			+ "5 - отчет о сотрудниках читавших книги за месяц " + "\n" + "6 - Выход из программы \n";
	
	public LibraryCardMenuImpl(){
		libraryController = new LibraryCardControllerCreator().factoryMethod(); 
	}
	
	@Override
	public void menu() {
		Read read = new Read();
		ReportDao reportDao = new ReportDaoImpl();
		
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
				System.out.println("Введите номер месяца в формате ММ");
				String month = read.readString();
				List<LibraryCard> listLCards = reportDao.reportEmployeeReadBooks(month);
				int number = 1;
				if (listLCards.isEmpty()) {
					System.out.println("В этом месяце ничего не читали");
				} else {
					System.out.println("Отчет по сотрудникам");
					for (LibraryCard e : listLCards) {
						System.out.println(number);
						System.out.println(e);
						number++;
					}
				}
				break;
			case 6:
				super.exitMenu();
				break;

			}
			break;
		}

	
	}


}
