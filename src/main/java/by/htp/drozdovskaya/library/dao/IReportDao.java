package by.htp.drozdovskaya.library.dao;

import java.util.Map;

import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public interface IReportDao {

	public Map<Book,Integer> popularBooks();
	public Map<Employee, Map<Book, LibraryCard>> readersNotReturnBooks();
	public Map<Employee, Integer> employeeReadBooksByMonth(int min, int max);
}
