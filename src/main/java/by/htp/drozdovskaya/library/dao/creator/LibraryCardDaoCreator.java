package by.htp.drozdovskaya.library.dao.creator;

import by.htp.drozdovskaya.library.dao.IDao;
import by.htp.drozdovskaya.library.dao.impl.LibraryCardDaoImpl;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class LibraryCardDaoCreator extends DaoCreator<LibraryCard> {

	@Override
	public IDao<LibraryCard> factoryMethod() {
		return new LibraryCardDaoImpl();
	}

}
