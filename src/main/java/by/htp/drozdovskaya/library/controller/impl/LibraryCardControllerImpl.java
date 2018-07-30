package by.htp.drozdovskaya.library.controller.impl;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.dao.interfaces.LibraryCardDao;
import by.htp.drozdovskaya.library.dao.impl.LibraryCardDaoImpl;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.run.Read;

public class LibraryCardControllerImpl implements ILibraryController {

	private LibraryCardDao daoLibCard;
	private Read read;

	public LibraryCardControllerImpl() {
		daoLibCard = new LibraryCardDaoImpl();
		read = new Read();
	}

	@Override
	public void showAll() {
		for (LibraryCard lCard : daoLibCard.getAll()) {
			System.out.println(lCard);
		}
	}

	@Override
	public boolean insert() {
		return false;
	}

	@Override
	public boolean update() {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

}
