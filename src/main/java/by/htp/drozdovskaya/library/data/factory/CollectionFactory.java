package by.htp.drozdovskaya.library.data.factory;

import by.htp.drozdovskaya.library.dao.IAuthorDao;
import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.dao.IEmployeeDao;
import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.dao.IReportDao;
import by.htp.drozdovskaya.library.dao.IUserDao;
import by.htp.drozdovskaya.library.dao.collections.AuthorCollectionImpl;
import by.htp.drozdovskaya.library.dao.collections.BookCollectionImpl;
import by.htp.drozdovskaya.library.dao.collections.EmployeeCollectionImpl;
import by.htp.drozdovskaya.library.dao.collections.LibraryCardCollectionImpl;
import by.htp.drozdovskaya.library.dao.collections.ReportCollectionImpl;
import by.htp.drozdovskaya.library.dao.collections.UserCollectionImpl;

public class CollectionFactory extends AbstractFactory {

	@Override
	public IEmployeeDao createEmployeeDao() {
		return new EmployeeCollectionImpl();
	}

	@Override
	public IBookDao createBookDao() {
		return new BookCollectionImpl();
	}

	@Override
	public IAuthorDao createAuthorDao() {
		return new AuthorCollectionImpl();
	}

	@Override
	public IUserDao createUserDao() {
		return new UserCollectionImpl();
	}

	@Override
	public ILibraryCardDao createLibraryCardDao() {
		return new LibraryCardCollectionImpl();
	}

	@Override
	public IReportDao createReportDao() {
		return new ReportCollectionImpl();
	}

}
