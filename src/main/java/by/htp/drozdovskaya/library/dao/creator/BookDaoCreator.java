package by.htp.drozdovskaya.library.dao.creator;

import by.htp.drozdovskaya.library.dao.IDao;
import by.htp.drozdovskaya.library.dao.impl.BookDaoImpl;
import by.htp.drozdovskaya.library.entity.Book;

public class BookDaoCreator extends DaoCreator<Book> {

	@Override
	public IDao<Book> factoryMethod() {
		return new BookDaoImpl();
	}

}
