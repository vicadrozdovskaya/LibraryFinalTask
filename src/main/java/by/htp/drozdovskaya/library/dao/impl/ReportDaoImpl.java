package by.htp.drozdovskaya.library.dao.impl;

import static by.htp.drozdovskaya.library.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.drozdovskaya.library.dao.BookDao;
import by.htp.drozdovskaya.library.dao.EmployeeDao;
import by.htp.drozdovskaya.library.dao.ReportDao;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class ReportDaoImpl implements ReportDao {

	private static final String SELECT_LIBCARDS_BYMONTH = "SELECT * FROM library_card join employee "
			+ "On library_card.id_employee=employee.id_employee WHERE"
			+ "(date_start LIKE ?) or (date_end LIKE ?) ORDER BY employee.department ASC, employee.name ASC";

	@Override
	public List<LibraryCard> reportEmployeeReadBooks(String month) {

		List<LibraryCard> listLibCard = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARDS_BYMONTH);
			ps.setString(1, "%-" + month + "-%");
			ps.setString(2, "%-" + month + "-%");
			// System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listLibCard.add(buildLibraryCards(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listLibCard;
	}

	private LibraryCard buildLibraryCards(ResultSet rs) throws SQLException {
		LibraryCard libCard = new LibraryCard();
		libCard.setIdCard(rs.getInt("id_card"));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(rs.getDate("date_start"));
		libCard.setDateStart(calendar);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(rs.getDate("date_end"));
		libCard.setDateEnd(calendar2);
		BookDao bookDao = new BookDaoImpl();
		Book book = bookDao.get(rs.getInt("id_book"));
		libCard.setBook(book);
		EmployeeDao emplDao = new EmployeeDaoImpl();
		Employee employee = new Employee();
		employee = emplDao.getEmployee(rs);
		libCard.setEmployee(employee);
		return libCard;
	}

}
