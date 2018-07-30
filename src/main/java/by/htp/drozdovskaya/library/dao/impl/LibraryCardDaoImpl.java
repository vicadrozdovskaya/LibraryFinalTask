package by.htp.drozdovskaya.library.dao.impl;

import static by.htp.drozdovskaya.library.dao.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.drozdovskaya.library.dao.interfaces.BookDao;
import by.htp.drozdovskaya.library.dao.interfaces.EmployeeDao;
import by.htp.drozdovskaya.library.dao.interfaces.LibraryCardDao;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class LibraryCardDaoImpl implements LibraryCardDao {

	private static final String SELECT_LIBCARD_BYID = "SELECT * FROM library_card WHERE id_card = ?";
	private static final String SELECT_ALL_LIBCARDS = "SELECT * FROM library_card";
	private static final String INSERT_LIBCARD_BYID = "INSERT INTO ibrary_card (date_start,date_end,id_book,id_employee)VALUES(?,?,?,?)";
	private static final String DELETE_LIBCARD_BYID = "DELETE FROM ibrary_card WHERE id_card = ?";
	private static final String UPDATE_LIBCARD_BYID = "UPDATE ibrary_card SET date_start = ? , date_end = ? , id_book = ? , id_employee = ? WHERE id_card = ?";

	@Override
	public LibraryCard get(int id) {
		LibraryCard libCard = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARD_BYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				libCard = buildLibraryCard(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return libCard;
	}

	@Override
	public boolean insert(LibraryCard lCard) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(INSERT_LIBCARD_BYID);
			ps.setDate(1, new Date(lCard.getDateStart().getTimeInMillis()));
			ps.setDate(2, new Date(lCard.getDateEnd().getTimeInMillis()));
			ps.setInt(3, lCard.getBook().getIdBook());
			ps.setInt(4, lCard.getEmployee().getIdEmployee());
			// System.out.println(ps);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(LibraryCard lCard) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_LIBCARD_BYID);
			ps.setDate(1, new Date(lCard.getDateStart().getTimeInMillis()));
			ps.setDate(2, new Date(lCard.getDateEnd().getTimeInMillis()));
			ps.setInt(3, lCard.getBook().getIdBook());
			ps.setInt(4, lCard.getEmployee().getIdEmployee());
			ps.setInt(5, lCard.getIdCard());
			System.out.println(ps);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(LibraryCard lCard) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(DELETE_LIBCARD_BYID);
			ps.setInt(1, lCard.getIdCard());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<LibraryCard> getAll() {
		List<LibraryCard> listLibCard = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_LIBCARDS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listLibCard.add(buildLibraryCard(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listLibCard;
	}

	private LibraryCard buildLibraryCard(ResultSet rs) throws SQLException {
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
		employee = emplDao.get(rs.getInt("id_employee"));
		libCard.setEmployee(employee);
		return libCard;
	}

}
