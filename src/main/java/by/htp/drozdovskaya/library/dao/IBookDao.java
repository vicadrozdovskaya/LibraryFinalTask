package by.htp.drozdovskaya.library.dao;

import java.util.List;

import by.htp.drozdovskaya.library.entity.Book;

public interface IBookDao extends IDao<Book> {

	Book get(int id);

	List<Book> getAll();

	boolean insert(Book book);

	boolean delete(Book book);

	boolean update(Book book);
	
	List<Book> findNotReturnBooksByEmployee(int id);

}
