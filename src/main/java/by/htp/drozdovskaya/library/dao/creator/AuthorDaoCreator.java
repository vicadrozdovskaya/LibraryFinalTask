package by.htp.drozdovskaya.library.dao.creator;

import by.htp.drozdovskaya.library.dao.impl.AuthorDaoImpl;
import by.htp.drozdovskaya.library.dao.interfaces.IDao;
import by.htp.drozdovskaya.library.entity.Author;

public class AuthorDaoCreator extends DaoCreator<Author> {

	@Override
	public IDao<Author> factoryMethod() {
		return new AuthorDaoImpl();
	}

}
