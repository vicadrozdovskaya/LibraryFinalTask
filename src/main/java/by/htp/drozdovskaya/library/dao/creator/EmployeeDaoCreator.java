package by.htp.drozdovskaya.library.dao.creator;

import by.htp.drozdovskaya.library.dao.impl.EmployeeDaoImpl;
import by.htp.drozdovskaya.library.dao.interfaces.IDao;
import by.htp.drozdovskaya.library.entity.Employee;

public class EmployeeDaoCreator extends DaoCreator<Employee> {

	@Override
	public IDao<Employee> factoryMethod() {
		return new EmployeeDaoImpl();
	}

}
