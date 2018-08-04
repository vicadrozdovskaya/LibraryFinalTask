package by.htp.drozdovskaya.library.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.data.factorymethod.FactoryCreator;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class LibraryCardLogic {

	private ILibraryCardDao libraryCardDao;
	private IBookDao bookDao;
	
	public LibraryCardLogic() {
		this.libraryCardDao = FactoryCreator.getFactory().createLibraryCardDao();
		this.bookDao = FactoryCreator.getFactory().createBookDao();
	}
	
	public List<LibraryCard> getListOfBooks() {
		return libraryCardDao.getAll();
	}
	
	public LibraryCard getLibraryCard(int idlibraryCard) {
		return libraryCardDao.get(idlibraryCard);
	}
	
	public void createLibraryCard(Calendar endDate, int idBook, int idEmployee) {
		LibraryCard libraryCard = new LibraryCard();
		Book book = new Book();
		Employee employe = new Employee();
		libraryCard.setDateStart(new GregorianCalendar());
		libraryCard.setDateEnd(endDate);
		libraryCard.setDaysOverdue(0);
		libraryCard.setReturned(false);
		book.setIdBook(idBook);
		employe.setIdEmployee(idEmployee);
		libraryCard.setBook(book);
		libraryCard.setEmployee(employe);
		libraryCardDao.insert(libraryCard);
	}
	
	public void returnBookToLibrary(int idBook, int idEmployee) {
		libraryCardDao.calculateOverdue();
		LibraryCard libraryCard = libraryCardDao.findCardByEmployeeAndBook(idEmployee, idBook);
		libraryCard.setReturned(true);
		libraryCard.setDateEnd(new GregorianCalendar());
		libraryCardDao.update(libraryCard);
	}
	
	public boolean checkOverdueByEmployee(int idEmployee) {
		for (LibraryCard lc : libraryCardDao.findCardsByEmployee(idEmployee)) {
			if (lc.getDaysOverdue() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public List<Book> getBooksByEmployee(int idEmployee){
		List<LibraryCard> libCards = libraryCardDao.findCardsByEmployee(idEmployee);
		List<Book> books = new ArrayList<>();
		for(LibraryCard libraryCard: libCards ) {
			books.add(libraryCard.getBook());
		}
		return books;
	}
	
	
}
