package by.htp.drozdovskaya.library.controller.impl;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.dao.IDao;
import by.htp.drozdovskaya.library.dao.creator.EmployeeDaoCreator;
import by.htp.drozdovskaya.library.dao.creator.UserDaoCreator;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.run.Read;

public class EmployeeControllerImpl implements ILibraryController {

	private IDao<Employee> daoEmpl;
	private IDao<User> userDao;
	private Read read;

	public EmployeeControllerImpl() {
		daoEmpl = new EmployeeDaoCreator().factoryMethod();
		userDao = new UserDaoCreator().factoryMethod();
		read = new Read();
	}

	@Override
	public void showAll() {
		for (Employee e : daoEmpl.getAll()) {
			System.out.println(e);
		}
	}

	@Override
	public boolean insert() {
		int last = userDao.getAll().size();
		User user = userDao.getAll().get(last);
		System.out.println("������� ��� ����������");
		String name = read.readLine();
		System.out.println("������� ������� ����������");
		String surname = read.readLine();
		System.out.println("������� ������� ����������");
		String phone = read.readLine();
		System.out.println("������� �������������, � ������� �������� ���������");
		String department = read.readLine();
		Employee employee = new Employee();
		employee.setIdEmployee(user.getIdUser());
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPhone(phone);
		employee.setDepartment(department);
		employee.setUser(user);
		if (daoEmpl.insert(employee)) {
			System.out.println("��������� ������� ��������");
			return true;
		} else {
			System.out.println("��������� ������ ��� ����������, ���������� �����");
			return false;
		}
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("�������� Id ����������, �������� ������ ��������");
		int id_employee = read.readNumber();
		System.out.println("������� ��� ����������");
		String name = read.readLine();
		System.out.println("������� ������� ����������");
		String surname = read.readLine();
		System.out.println("������� ������� ����������");
		String phone = read.readLine();
		System.out.println("������� �������������, � ������� �������� ���������");
		String department = read.readLine();
		Employee employee = new Employee();
		employee.setIdEmployee(id_employee);
		employee.setName(name);
		employee.setSurname(surname);
		employee.setPhone(phone);
		employee.setDepartment(department);
		if (daoEmpl.update(employee)) {
			System.out.println("��������� ������� �������");
			return true;
		} else {
			System.out.println("��������� ������ ��� ���������, ���������� �����");
			return false;
		}
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("�������� Id ����������, �������� ������ ��������");
		int id_employee = read.readNumber();
		Employee employee = new Employee();
		employee.setIdEmployee(id_employee);
		if (daoEmpl.delete(daoEmpl.get(id_employee))) {
			System.out.println("��������� ������� ������");
			return true;
		} else {
			System.out.println("��������� ������ ��� ��������, ���������� �����");
			return false;
		}
	}

}
