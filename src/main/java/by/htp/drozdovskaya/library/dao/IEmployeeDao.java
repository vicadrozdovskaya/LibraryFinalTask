package by.htp.drozdovskaya.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.drozdovskaya.library.entity.Employee;

public interface IEmployeeDao extends IDao<Employee> {

	Employee getEmployee(ResultSet rs) throws SQLException;



}
