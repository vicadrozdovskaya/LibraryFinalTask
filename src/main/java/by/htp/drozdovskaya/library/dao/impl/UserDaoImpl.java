package by.htp.drozdovskaya.library.dao.impl;

import static by.htp.drozdovskaya.library.dao.util.MySqlPropertyManager.getProperties;
import static by.htp.drozdovskaya.library.dao.util.MySqlPropertyManager.getUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.drozdovskaya.library.dao.UserDao;
import by.htp.drozdovskaya.library.entity.User;

public class UserDaoImpl implements UserDao{
	
	private static final String SELECT_USER_BYID = "SELECT * FROM user WHERE id_user = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";
	private static final String INSERT_USER_BYID = "INSERT INTO user (id_user,login,password,role)VALUES(?,?,?,?)";
	private static final String DELETE_USER_BYID = "DELETE FROM user WHERE id_user = ?";
	private static final String UPDATE_USER_BYID = "UPDATE user SET login = ? , password = ? , role = ? WHERE id_user = ?";

	@Override
	public List<User> getAll() {
		List<User> listUser = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listUser.add(buildUser(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return listUser;
	}

	@Override
	public User get(int id) {
		User user = null;
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(SELECT_USER_BYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = buildUser(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean insert(User user) {
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(INSERT_USER_BYID);
			ps.setInt(1, user.getIdUser());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getRole());
			if (ps.executeUpdate() == 1) {
				return true;
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(User user) {
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(UPDATE_USER_BYID);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getRole());
			ps.setInt(4, user.getIdUser());
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
	public boolean delete(User user) {
		try(Connection conn = DriverManager.getConnection(getUrl(), getProperties())){
			PreparedStatement ps = conn.prepareStatement(DELETE_USER_BYID);
			ps.setInt(1, user.getIdUser());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	private User buildUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setIdUser(rs.getInt("id_user"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setRole(rs.getInt("role"));
		return user;
	}

	@Override
	public User getUser(ResultSet rs) throws SQLException {
		return buildUser(rs);
	}

}
