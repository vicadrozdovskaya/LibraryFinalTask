package by.htp.drozdovskaya.library.menu.impl;

import java.util.List;

import by.htp.drozdovskaya.library.controller.creator.BookControllerCreator;
import by.htp.drozdovskaya.library.dao.interfaces.ReportDao;
import by.htp.drozdovskaya.library.dao.impl.ReportDaoImpl;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.run.Read;

public class BookMenuImpl extends MainMenu {

	private static final String MENU = "1 - посмотреть данные о книгах в библиотеке " + "\n" + "2 - добавить книгу в библиотеку "
			+ "\n" + "3 - изменить данные о книге в библиотеку  " + "\n" + "4 - удалить книгу из библиотеки " + "\n"
			+ "5 - отчет о сотрудниках читавших книги за мес€ц " + "\n" + "6 - ¬ыход из программы \n";
	
	public BookMenuImpl(){
		libraryController = new BookControllerCreator().factoryMethod(); 
	}
	
	@Override
	public void menu() {
		Read read = new Read();
		ReportDao reportDao = new ReportDaoImpl();
		
		while (true) {
			System.out.println(MENU);
			int choice2 = read.readNumber();
			switch (choice2) {
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
				System.out.println("¬ведите номер мес€ца в формате ћћ");
				String month = read.readString();
				List<LibraryCard> listLCards = reportDao.reportEmployeeReadBooks(month);
				int number = 1;
				if (listLCards.isEmpty()) {
					System.out.println("¬ этом мес€це ничего не читали");
				} else {
					System.out.println("ќтчет по сотрудникам");
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
