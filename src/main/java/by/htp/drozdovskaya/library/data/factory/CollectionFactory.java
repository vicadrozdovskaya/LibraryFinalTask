package by.htp.drozdovskaya.library.data.factory;

import by.htp.drozdovskaya.library.dao.IAuthorDao;
import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.dao.IReportDao;
import by.htp.drozdovskaya.library.dao.IUserDao;

public class CollectionFactory extends AbstractFactory {

	@Override
	public IEmployeeDao createEmployeeDao() {
		return null;
	}

	@Override
	public IBookDao createBookDao() {
		return null;
	}

	@Override
	public IAuthorDao createAuthorDao() {
		return null;
	}

	@Override
	public IUserDao createUserDao() {
		return null;
	}

	@Override
	public ILibraryCardDao createLibraryCardDao() {
		return null;
	}

	@Override
	public IReportDao createReportDao() {
		return null;
	}

}
