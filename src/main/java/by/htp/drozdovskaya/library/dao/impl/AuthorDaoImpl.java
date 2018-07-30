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

import by.htp.drozdovskaya.library.dao.interfaces.AuthorDao;
import by.htp.drozdovskaya.library.entity.Author;



public class AuthorDaoImpl implements AuthorDao {
	
	private static final String SELECT_AUTHOR_BYID = "SELECT * FROM author WHERE id_author = ?";
	private static final String SELECT_ALL_AUTHORS = "SELECT * FROM author";
	private static final String INSERT_AUTHOR_BYID = "INSERT INTO author (name,surname,date_birth)VALUES(?,?,?)";
	private static final String DELETE_AUTHOR_BYID = "DELETE FROM author WHERE id_author = ?";
	private static final String UPDATE_AUTHOR_BYID = "UPDATE author SET name = ? , surname = ? , date_birth = ? WHERE id_author = ?";

	@Override
	public Author get(int id) {
		Author author = null;
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(SELECT_AUTHOR_BYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				author = buildAuthor(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public boolean insert(Author author) {
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(INSERT_AUTHOR_BYID);
			ps.setString(1, author.getName());
			ps.setString(2, author.getSurname());
			ps.setDate(3, new Date(author.getBirthDate().getTimeInMillis()));
//			System.out.println(ps);
			if (ps.executeUpdate() == 1) {
				return true;
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Author author) {
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(UPDATE_AUTHOR_BYID);
			ps.setString(1, author.getName());
			ps.setString(2,author.getSurname());
			ps.setDate(3,new Date(author.getBirthDate().getTimeInMillis()));
			ps.setInt(4, author.getIdAuthor());
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
	public boolean delete(Author author) {
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(DELETE_AUTHOR_BYID);
			ps.setInt(1, author.getIdAuthor());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	private Author buildAuthor(ResultSet rs) throws SQLException {
		Author author = new Author();
		author.setIdAuthor(rs.getInt("id_author"));
		author.setName(rs.getString("name"));
		author.setSurname(rs.getString("surname"));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(rs.getDate("date_birth"));
		author.setBirthDate(calendar);
		return author;
	}

	@Override
	public List<Author> getAll() {
		List<Author> listAuthor = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_AUTHORS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listAuthor.add(buildAuthor(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listAuthor;
	}

}
