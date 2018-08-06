package by.htp.drozdovskaya.library.dao.mysql.impl;

import static by.htp.drozdovskaya.library.dao.mysql.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.dao.IReportDao;
import by.htp.drozdovskaya.library.dao.IUserDao;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class ReportDaoImpl implements IReportDao {

	private static final String SELECT_EMPLOYEE_READ_BOOK_BYMONTH = "SELECT EMPLOYEE.id_employee, COUNT(LIBRARY_CARD.ID_EMPLOYEE) AS READED_BOOKS FROM LIBRARY_CARD JOIN EMPLOYEE ON LIBRARY_CARD.ID_EMPLOYEE = EMPLOYEE.ID_EMPLOYEE WHERE LIBRARY_CARD.isReturned > 0 AND LIBRARY_CARD.id_employee IN 		\r\n" + 
			"		(\r\n" + 
			"			SELECT distinct employee.id_employee FROM employee join library_card\r\n" + 
			"			On employee.id_employee=library_card.id_employee\r\n" + 
			"			WHERE \r\n" + 
			"			(date_end  	BETWEEN ? AND ?)\r\n" + 
			"			and isReturned = 1\r\n" + 
			"		)\r\n" + 
			"		GROUP BY EMPLOYEE.id_employee HAVING READED_BOOKS BETWEEN ? AND ?";
	private static final String SELECT_BOOKS_POPULAR_READ = "SELECT BOOK.ID_BOOK, BOOK.TITLE, BOOK.QUANTITY, BOOK.ID_AUTHOR, COUNT(*) AS BOOK_TAKEDAWAY FROM LIBRARY_CARD\r\n" + 
			"	JOIN BOOK ON LIBRARY_CARD.ID_BOOK = BOOK.ID_BOOK WHERE LIBRARY_CARD.ID_BOOK IN \r\n" + 
			"	(\r\n" + 
			"		SELECT DISTINCT LIBRARY_CARD.ID_BOOK FROM LIBRARY_CARD WHERE ISRETURNED > 0\r\n" + 
			"	)\r\n" + 
			"	GROUP BY LIBRARY_CARD.ID_BOOK ORDER BY BOOK_TAKEDAWAY DESC, BOOK.TITLE ASC";

	@Override
	public Map<Employee, Integer> employeeReadBooksByMonth(int min, int max) {
		Calendar now = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, -30);
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
		Map<Employee, Integer> employeeMap = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_EMPLOYEE_READ_BOOK_BYMONTH);
			ps.setString(1, formatForDateNow.format(endDate.getTime()));
			ps.setString(2, formatForDateNow.format(now.getTime()));
			ps.setInt(3, min);
			ps.setInt(4, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employeeMap.put(buildEmployee(rs), rs.getInt("READED_BOOKS"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employeeMap;

	}


	private Employee buildEmployee(ResultSet rs) throws SQLException {
		IEmployeeDao emplDao = new EmployeeDaoImpl();
		Employee employee = new Employee();
		employee = emplDao.
				get(rs.getInt("id_employee"));
		return employee;
	}

	@Override
	public Map<Book,Integer> popularBooks() {
		IBookDao iBookDao = new BookDaoImpl();
		Map<Book,Integer> books = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_BOOKS_POPULAR_READ);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				books.put(((BookDaoImpl)iBookDao).buildBook(rs), rs.getInt("BOOK_TAKEDAWAY"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return books;

	}

	@Override
	public Map<Employee, Map<Book, LibraryCard>> readersNotReturnBooks() {
		ILibraryCardDao libCard = new LibraryCardDaoImpl();
		IUserDao iUserDao = new UserDaoImpl();
		return libCard.getReadersWithOverdue(iUserDao.getAll());
	}

	

}
