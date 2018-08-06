package by.htp.drozdovskaya.library.dao.collections;

import java.util.ArrayList;
import java.util.List;

import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.entity.Employee;

public class EmployeeCollectionImpl implements IEmployeeDao {

	@Override
	public List<Employee> getAll() {
		List<Employee> employee = LibraryCollectionsStorage.getEmployee();
		return employee;
	}

	@Override
	public Employee get(int id) {
		List<Employee> employee = LibraryCollectionsStorage.getEmployee();
		Employee returnEmployee = new Employee();
		for (Employee employe: employee) {
			if(employe.getIdEmployee() == id) {
				returnEmployee.setIdEmployee(employe.getIdEmployee());
				returnEmployee.setName(employe.getName());
				returnEmployee.setSurname(employe.getSurname());
				returnEmployee.setPhone(employe.getPhone());
				returnEmployee.setDepartment(employe.getDepartment());
				returnEmployee.setUser(employe.getUser());
			}
		}
		return returnEmployee;
	}

	@Override
	public boolean insert(Employee employe) {
		List<Employee> employee = new ArrayList<>();
		employee.addAll(LibraryCollectionsStorage.getEmployee());
		employe.setIdEmployee(LibraryCollectionsStorage.getNextEmployeeId());
		boolean result = employee.add(employe);
		LibraryCollectionsStorage.setEmployee(employee);
		return result;
	}

	@Override
	public boolean update(Employee employe) {
		List<Employee> employee = new ArrayList<>();
		employee.addAll(LibraryCollectionsStorage.getEmployee());
		for(Employee employeThis: employee) {
			if(employeThis.getIdEmployee() == employe.getIdEmployee()) {
				employeThis.setIdEmployee(employe.getIdEmployee());
				employeThis.setName(employe.getName());
				employeThis.setSurname(employe.getSurname());
				employeThis.setPhone(employe.getPhone());
				employeThis.setUser(employe.getUser());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Employee employe) {
		List<Employee> employee = new ArrayList<>();
		employee.addAll(LibraryCollectionsStorage.getEmployee());
		boolean result = employee.remove(employe);
		LibraryCollectionsStorage.setEmployee(employee);
		return result;
	}


}
