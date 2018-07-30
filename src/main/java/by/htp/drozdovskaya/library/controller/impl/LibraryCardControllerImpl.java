package by.htp.drozdovskaya.library.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.dao.interfaces.IDao;
import by.htp.drozdovskaya.library.dao.creator.BookDaoCreator;
import by.htp.drozdovskaya.library.dao.creator.EmployeeDaoCreator;
import by.htp.drozdovskaya.library.dao.creator.LibraryCardDaoCreator;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.run.Read;

public class LibraryCardControllerImpl implements ILibraryController {

	private IDao<LibraryCard> libraryCardDao;
	private IDao<Book> bookDao;
	private IDao<Employee> employeeDao;
	private Read read;

	public LibraryCardControllerImpl() {
		libraryCardDao = new LibraryCardDaoCreator().factoryMethod();
		bookDao = new BookDaoCreator().factoryMethod();
		employeeDao = new EmployeeDaoCreator().factoryMethod();
		read = new Read();
	}

	@Override
	public void showAll() {
		for (LibraryCard lCard : libraryCardDao.getAll()) {
			System.out.println(lCard);
		}
	}

	@Override
	public boolean insert() {
		LibraryCard libraryCard = new LibraryCard();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, 30);
		libraryCard.setDateStart(Calendar.getInstance());
		libraryCard.setDateEnd(endDate);
		for (Book b : bookDao.getAll()) {
			System.out.println(b);
		}
		System.out.println("Выберите книгу для редактирования: введите ее ID");
		int id_book = read.readNumber();
		for (Employee e : employeeDao.getAll()) {
			System.out.println(e);
		}
		System.out.println("Выберите сотрудника: введите его ID");
		int id_employee = read.readNumber();
		libraryCard.setBook(bookDao.get(id_book));
		libraryCard.setEmployee(employeeDao.get(id_employee));
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
		if (libraryCardDao.insert(libraryCard)) {
			System.out.println(libraryCard.getEmployee().getName() + " взял(а) книгу " + libraryCard.getBook().getTitle()
					+ " до " + formatForDateNow.format(libraryCard.getDateEnd().getTime()));
			return true;
		} else {
			System.out.println("Произошла ошибка при добавлении, попрубуйте снова");
			return false;
		}

	}

	@Override
	public boolean update() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

}
