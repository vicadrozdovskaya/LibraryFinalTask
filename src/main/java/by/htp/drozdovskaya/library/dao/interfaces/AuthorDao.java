package by.htp.drozdovskaya.library.dao.interfaces;

import java.util.List;

import by.htp.drozdovskaya.library.entity.Author;

public interface AuthorDao extends IDao<Author> {

	Author get(int id);

	boolean insert(Author author);

	boolean update(Author author);

	boolean delete(Author author);

	List<Author> getAll();

}
