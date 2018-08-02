package by.htp.drozdovskaya.library.dao;

import java.util.List;
import java.util.Map;

import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.entity.User;

public interface ILibraryCardDao extends IDao<LibraryCard> {

	LibraryCard get(int id);

	boolean insert(LibraryCard lCard);

	boolean update(LibraryCard lCard);

	boolean delete(LibraryCard lCard);

	List<LibraryCard> getAll();
	
	List<LibraryCard> findCardsByEmployee(int id);
	
	LibraryCard findCardByEmployeeAndBook(int id_employee, int id_book);
	
	Map<Employee,Map<Book, LibraryCard>> getReadersWithOverdue(List<User> users);
	
	Map<Book, LibraryCard> getReaderWithOverdue(User user);
	
	void calculateOverdue();
}
