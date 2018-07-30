package by.htp.drozdovskaya.library.dao.interfaces;

import java.util.List;

import by.htp.drozdovskaya.library.entity.LibraryCard;

public interface LibraryCardDao extends IDao<LibraryCard> {

	LibraryCard get(int id);

	boolean insert(LibraryCard lCard);

	boolean update(LibraryCard lCard);

	boolean delete(LibraryCard lCard);

	List<LibraryCard> getAll();
}
