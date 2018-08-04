package by.htp.drozdovskaya.library.logic;

import java.util.List;

import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.data.factorymethod.FactoryCreator;
import by.htp.drozdovskaya.library.entity.Author;
import by.htp.drozdovskaya.library.entity.Book;

public class BookLogic {
	
	private IBookDao bookDao;
	
	public BookLogic() {
		this.bookDao = FactoryCreator.getFactory().createBookDao();
	}
	
	public List<Book> getListOfBooks() {
		return bookDao.getAll();
	}
	
	public Book getBook(int idBook) {
		return bookDao.get(idBook);
	}
	
	public void createBook(String title, int quantity, int idAuthor) {
		Book book = new Book();
		Author author = new Author();
		author.setIdAuthor(idAuthor);
		book.setTitle(title);
		book.setQuantity(quantity);
		book.setAuthor(author);
		bookDao.insert(book);
	}
	
	public void updateBook(int idBook, String title, int quantity, int idAuthor) {
		Book book = new Book();
		Author author = new Author();
		author.setIdAuthor(idAuthor);
		book.setIdBook(idBook);
		book.setQuantity(quantity);
		book.setTitle(title);
		book.setAuthor(author);
		bookDao.update(book);
	}
	
	public void deleteBook(int idBook) {
		Book book = new Book();
		book.setIdBook(idBook);
		bookDao.delete(book);
	}
	
	public boolean checkQuantityBooksByEmployee(int idEmployee){
		if (bookDao.findNotReturnBooksByEmployee(idEmployee).size() >= 3) {
			return true;
		}
		return false;
	}

}
