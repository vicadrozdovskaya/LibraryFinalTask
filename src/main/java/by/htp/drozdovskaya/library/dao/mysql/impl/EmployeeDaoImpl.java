package by.htp.drozdovskaya.library.dao.mysql.impl;

import static by.htp.drozdovskaya.library.dao.mysql.util.MySqlPropertyManager.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.IUserDao;
import by.htp.drozdovskaya.library.entity.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

	private static final String SELECT_EMPLOYEE_BYID = "SELECT * FROM employee WHERE id_employee = ?";
	private static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM employee";
	private static final String INSERT_EMPLOYEE_BYID = "INSERT INTO employee (id_employee,name,surname,phone,department)VALUES(?,?,?,?,?)";
	private static final String DELETE_EMPLOYEE_BYID = "DELETE FROM employee WHERE id_employee = ?";
	private static final String UPDATE_EMPLOYEE_BYID = "UPDATE employee SET name = ? , surname = ? , phone = ? , department = ? WHERE id_employee = ?";

	@Override
	public Employee get(int id) {
		Employee employee = null;
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_EMPLOYEE_BYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employee = buildEmployee(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public boolean insert(Employee employee) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(INSERT_EMPLOYEE_BYID);
			ps.setInt(1, employee.getIdEmployee());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getSurname());
			ps.setString(4, employee.getPhone());
			ps.setString(5, employee.getDepartment());
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
	public boolean update(Employee employee) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(UPDATE_EMPLOYEE_BYID);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getSurname());
			ps.setString(3, employee.getPhone());
			ps.setString(4, employee.getDepartment());
			ps.setInt(5, employee.getIdEmployee());
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
	public boolean delete(Employee employee) {
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(DELETE_EMPLOYEE_BYID);
			ps.setInt(1, employee.getIdEmployee());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> listEmployee = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(getUrl(), getProperties())) {
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_EMPLOYEE);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listEmployee.add(buildEmployee(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listEmployee;
	}

	private Employee buildEmployee(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setIdEmployee(rs.getInt("id_employee"));
		employee.setName(rs.getString("name"));
		employee.setSurname(rs.getString("surname"));
		employee.setPhone(rs.getString("phone"));
		employee.setDepartment(rs.getString("department"));
		IUserDao iUserDao = new UserDaoImpl();
		employee.setUser(iUserDao.get(rs.getInt("id_employee")));
		return employee;
	}

	@Override
	public Employee getEmployee(ResultSet rs) throws SQLException {
		return buildEmployee(rs);
	}

}
