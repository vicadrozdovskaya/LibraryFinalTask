package by.htp.drozdovskaya.library.dao.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.dao.IReportDao;
import by.htp.drozdovskaya.library.dao.IUserDao;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class ReportCollectionImpl implements IReportDao {

	@Override
	public Map<Book, Integer> popularBooks() {
		IBookDao iBookDao = new BookCollectionImpl();
		ILibraryCardDao iLibCardDao = new LibraryCardCollectionImpl();		
		
		List<LibraryCard> listLibCards = new ArrayList<>();
		listLibCards.addAll(iLibCardDao.getAll());
	
		
		Map<Book,Integer> books = new HashMap<>();
		int counter = 0;
		for(LibraryCard bookInCard: listLibCards) {
			if(bookInCard.isReturned()) {
				Book book = iBookDao.get(bookInCard.getBook().getIdBook());
				if(!books.containsKey(book)) {
					counter = 1;
				}
				else{
					counter = books.get(book);
					counter++;					
				}
				books.put(book, counter);
			}
		}
			
		
		
		return books;
	}

	@Override
	public Map<Employee, Map<Book, LibraryCard>> readersNotReturnBooks() {
		ILibraryCardDao libCard = new LibraryCardCollectionImpl();
		IUserDao iUserDao = new UserCollectionImpl();
		return libCard.getReadersWithOverdue(iUserDao.getAll());
	}

	@Override
	public Map<Employee, Integer> employeeReadBooksByMonth(int min, int max) {
		IEmployeeDao iEmployeeDao = new EmployeeCollectionImpl();
		ILibraryCardDao iLibCardDao = new LibraryCardCollectionImpl();		
		
		List<LibraryCard> listLibCards = new ArrayList<>();
		listLibCards.addAll(iLibCardDao.getAll());
	
		
		Map<Employee,Integer> employeeReadBooks = new HashMap<>();
		int counter = 0;
		for(LibraryCard bookInCard: listLibCards) {
			if(bookInCard.isReturned()) {
				Employee employe = iEmployeeDao.get(bookInCard.getEmployee().getIdEmployee());
				if(!employeeReadBooks.containsKey(employe)) {
					counter = 1;
				}
				else{
					counter = employeeReadBooks.get(employe);
					counter++;					
				}
				employeeReadBooks.put(employe, counter);
			}
		}
		Set<Employee> keySet = employeeReadBooks.keySet();
		for(Employee employe: keySet) {
			if(employeeReadBooks.get(employe) < 2 || employeeReadBooks.get(employe) > 8) {
				employeeReadBooks.remove(employe);
			}
		}
		return employeeReadBooks;
	}

}
