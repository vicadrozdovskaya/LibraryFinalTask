package by.htp.drozdovskaya.library.data.factory;

import by.htp.drozdovskaya.library.dao.IAuthorDao;
import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.dao.IReportDao;
import by.htp.drozdovskaya.library.dao.IUserDao;

public abstract class AbstractFactory {
	
	public abstract IEmployeeDao createEmployeeDao();
	public abstract IBookDao createBookDao();
	public abstract IAuthorDao createAuthorDao();
	public abstract IUserDao createUserDao();
	public abstract ILibraryCardDao createLibraryCardDao();
	public abstract IReportDao createReportDao();

}
