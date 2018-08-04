package by.htp.drozdovskaya.library.logic;

import java.util.List;

import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.data.factorymethod.FactoryCreator;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.User;

public class EmployeeLogic {

	private IEmployeeDao employeDao;

	public EmployeeLogic() {
		this.employeDao = FactoryCreator.getFactory().createEmployeeDao();		
	}
	
	public List<Employee> getListOfEmployee() {
		return employeDao.getAll();
	}
	
	public Employee getEmployee(int idEmploye) {
		return employeDao.get(idEmploye);
	}
	
	public void createEmployee(String name, String surname, String phone, String department, User user ) {
		Employee employee = new Employee();
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPhone(phone);
		employee.setDepartment(department);
		employee.setUser(user);
		employeDao.insert(employee);
	}
	
	public void updateEmployee(int idEmploye, String name, String surname, String phone, String department) {
		Employee employee = new Employee();
		employee.setIdEmployee(idEmploye);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPhone(phone);
		employee.setDepartment(department);
		employeDao.update(employee);
	}
	
	public void deleteEmployee(int idEmploye) {
		Employee employee = new Employee();
		employee.setIdEmployee(idEmploye);
		employeDao.delete(employee);
	}
}
