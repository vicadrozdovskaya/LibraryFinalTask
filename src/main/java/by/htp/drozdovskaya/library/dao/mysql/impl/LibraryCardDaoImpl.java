package by.htp.drozdovskaya.library.dao.mysql.impl;

import static by.htp.drozdovskaya.library.dao.mysql.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.entity.User;

public class LibraryCardDaoImpl implements ILibraryCardDao {

	private static final String SELECT_LIBCARD_BYID = "SELECT * FROM library_card WHERE id_card = ?";
	private static final String SELECT_LIBCARDS_BYEMPLOYEE = "SELECT * FROM library_card WHERE id_employee = ?";
	private static final String SELECT_LIBCARDS_BYEMPLOYEE_AND_BOOK = "SELECT * FROM library_card WHERE id_employee = ? and id_book =?";
	private static final String SELECT_ALL_LIBCARDS = "SELECT * FROM library_card";
	private static final String INSERT_LIBCARD_BYID = "INSERT INTO library_card (date_start,date_end,days_overdue,isReturned,id_book,id_employee)VALUES(?,?,?,?,?,?)";
	private static final String DELETE_LIBCARD_BYID = "DELETE FROM library_card WHERE id_card = ?";
	private static final String UPDATE_LIBCARD_BYID = "UPDATE library_card SET date_start = ? , date_end = ? ,days_overdue = ?,isReturned = ?, id_book = ? , id_employee = ? WHERE id_card = ?";
	private static final String UPDATE_LIBRARUCARD_OVERDUE = "UPDATE library_card SET days_overdue = ?  WHERE id_card = ?";
	private static final String SELECT_NOTRETURN_LIBCARDS = "SELECT * FROM library_card WHERE isReturned = 0";
	private static final String SELECT_NOTRETURN_LIBCARDS_BYUSER = "SELECT * FROM library_card  WHERE isReturned = 0 AND card_number = ? AND days_overdue>0";

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
			ps.setInt(3, lCard.getDaysOverdue());
			ps.setBoolean(4, lCard.isReturned());
			ps.setInt(5, lCard.getBook().getIdBook());
			ps.setInt(6, lCard.getEmployee().getIdEmployee());
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
			ps.setInt(3, lCard.getDaysOverdue());
			ps.setBoolean(4, lCard.isReturned());
			ps.setInt(5, lCard.getBook().getIdBook());
			ps.setInt(6, lCard.getEmployee().getIdEmployee());
			ps.setInt(7, lCard.getIdCard());
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
		libCard.setDaysOverdue(rs.getInt("days_overdue"));
		libCard.setReturned(rs.getBoolean("isReturned"));
		libCard.setNumberCard(rs.getString("card_number"));
		IBookDao iBookDao = new BookDaoImpl();
		Book book = iBookDao.get(rs.getInt("id_book"));
		libCard.setBook(book);
		IEmployeeDao emplDao = new EmployeeDaoImpl();
		Employee employee = new Employee();
		employee = emplDao.get(rs.getInt("id_employee"));
		libCard.setEmployee(employee);
		return libCard;
	}

	@Override
	public Map<Employee, Map<Book, LibraryCard>> getReadersWithOverdue(List<User> users) {
		calculateOverdue();
		IEmployeeDao emplDao = new EmployeeDaoImpl();
		Employee employee = new Employee();
		Map<Book, LibraryCard> bookMap = new HashMap<>();
		Map<Employee, Map<Book, LibraryCard>> userMap = new HashMap<>();
		for (User u : users) {
			bookMap = getReaderWithOverdue(u);
			if ( !bookMap.isEmpty()) {
				employee = emplDao.get(u.getIdUser());
			userMap.put(employee, bookMap);
			}
		}
		return userMap;
	}

	private List<LibraryCard> getNotReturnCards() {
		List<LibraryCard> libCards = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_NOTRETURN_LIBCARDS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				libCards.add(buildLibraryCard(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return libCards;
	}

	@Override
	public Map<Book, LibraryCard> getReaderWithOverdue(User user) {
		LibraryCard libCard = null;
		Map<Book, LibraryCard> bookMap = new HashMap<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_NOTRETURN_LIBCARDS_BYUSER);
			ps.setString(1, user.getLogin());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {	
				libCard = buildLibraryCard(rs);
				bookMap.put(libCard.getBook(), libCard);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bookMap;
	}
	private boolean updateOverdue(LibraryCard lCard) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_LIBRARUCARD_OVERDUE);
			ps.setInt(1,lCard.getDaysOverdue());
			ps.setInt(2, lCard.getIdCard());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void calculateOverdue() {
		ZonedDateTime zdt1 = ZonedDateTime.now();
		for (LibraryCard lC : getNotReturnCards()) {
			if (!lC.isReturned()) {
				Period period = Period.between(
						lC.getDateEnd().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						zdt1.toLocalDate());
				int k = period.getDays();
				if (k > 0) {
					lC.setDaysOverdue(k);
					updateOverdue(lC);
				}
			}
		}
	}

	@Override
	public List<LibraryCard> findCardsByEmployee(int id) {
		List<LibraryCard> libCards = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARDS_BYEMPLOYEE);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				libCards.add(buildLibraryCard(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return libCards;
	}
	
	@Override
	public LibraryCard findCardByEmployeeAndBook(int id_employee, int id_book) {
		LibraryCard libCard = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_LIBCARDS_BYEMPLOYEE_AND_BOOK);
			ps.setInt(1, id_employee);
			ps.setInt(2, id_book);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				libCard = buildLibraryCard(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return libCard;
	}

}
