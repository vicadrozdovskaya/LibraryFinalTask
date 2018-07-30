package by.htp.drozdovskaya.library.dao.impl;

import java.sql.Connection;
import static by.htp.drozdovskaya.library.dao.util.MySqlPropertyManager.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.drozdovskaya.library.dao.interfaces.AuthorDao;
import by.htp.drozdovskaya.library.dao.interfaces.BookDao;
import by.htp.drozdovskaya.library.entity.Author;
import by.htp.drozdovskaya.library.entity.Book;

public class BookDaoImpl implements BookDao {

	private static final String SELECT_BOOK_BYID = "SELECT * FROM book WHERE id_book = ?";
	private static final String SELECT_ALL_BOOK = "SELECT * FROM book";
	private static final String INSERT_BOOK_BYID = "INSERT INTO book (title, quantity,id_author)VALUES(?,?,?)";
	private static final String DELETE_BOOK_BYID = "DELETE FROM book WHERE id_book = ?";
	private static final String UPDATE_BOOK_BYID = "UPDATE book SET title = ? , quantity = ?, id_author = ? WHERE id_book = ?";

	@Override
	public Book get(int id) {
		Book book = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_BOOK_BYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				book = buildBook(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return book;
	}

	@Override
	public List<Book> getAll() {
		List<Book> listBook = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_BOOK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listBook.add(buildBook(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listBook;
	}

	@Override
	public boolean insert(Book book) {

		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(INSERT_BOOK_BYID);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getQuantity());
			ps.setInt(3, book.getAuthor().getIdAuthor());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Book book) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(DELETE_BOOK_BYID);
			ps.setInt(1, book.getIdBook());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Book book) {

		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_BOOK_BYID);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getIdBook());
			ps.setInt(3, book.getAuthor().getIdAuthor());
			ps.setInt(4, book.getIdBook());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Book buildBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setIdBook(rs.getInt("id_book"));
		book.setTitle(rs.getString("title"));
		book.setQuantity(rs.getInt("quantity"));
		AuthorDao dao = new AuthorDaoImpl();
		Author author = dao.get(rs.getInt("id_author"));
		book.setAuthor(author);
		return book;
	}

}
