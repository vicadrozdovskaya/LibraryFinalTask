package by.htp.drozdovskaya.library.data.factory;

import java.util.Map;

import by.htp.drozdovskaya.library.dao.IAuthorDao;
import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.dao.IReportDao;
import by.htp.drozdovskaya.library.dao.IUserDao;
import by.htp.drozdovskaya.library.dao.mysql.impl.AuthorDaoImpl;
import by.htp.drozdovskaya.library.dao.mysql.impl.BookDaoImpl;
import by.htp.drozdovskaya.library.dao.mysql.impl.EmployeeDaoImpl;
import by.htp.drozdovskaya.library.dao.mysql.impl.LibraryCardDaoImpl;
import by.htp.drozdovskaya.library.dao.mysql.impl.ReportDaoImpl;
import by.htp.drozdovskaya.library.dao.mysql.impl.UserDaoImpl;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class MySQLFactory extends AbstractFactory {

	@Override
	public IEmployeeDao createEmployeeDao() {
		return new EmployeeDaoImpl();
	}

	@Override
	public IBookDao createBookDao() {
		return new BookDaoImpl();
	}

	@Override
	public IAuthorDao createAuthorDao() {
		return new AuthorDaoImpl();
	}

	@Override
	public IUserDao createUserDao() {
		return new UserDaoImpl();
	}

	@Override
	public ILibraryCardDao createLibraryCardDao() {
		return new LibraryCardDaoImpl();
	}

	@Override
	public IReportDao createReportDao() {
		return new ReportDaoImpl();

	}

}
