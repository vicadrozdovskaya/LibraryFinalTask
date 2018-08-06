package by.htp.drozdovskaya.library.dao.collections;

import java.util.ArrayList;
import java.util.List;

import by.htp.drozdovskaya.library.dao.IBookDao;
import by.htp.drozdovskaya.library.entity.Book;

public class BookCollectionImpl implements IBookDao {

	public BookCollectionImpl() {
	}

	@Override
	public Book get(int id) {
		List<Book> books = LibraryCollectionsStorage.getBooks();
		Book returnBook = new Book();
		for (Book book : books) {
			if (book.getIdBook() == id) {
				returnBook.setIdBook(book.getIdBook());
				returnBook.setQuantity(book.getQuantity());
				returnBook.setTitle(book.getTitle());
				returnBook.setAuthor(book.getAuthor());
			}
		}
		return returnBook;
	}

	@Override
	public List<Book> getAll() {
		List<Book> books = LibraryCollectionsStorage.getBooks();
		return books;
	}

	@Override
	public boolean insert(Book book) {
		List<Book> books = new ArrayList<>();
		books.addAll(LibraryCollectionsStorage.getBooks());
		book.setIdBook(LibraryCollectionsStorage.getNextBookId());
		boolean result = books.add(book);
		LibraryCollectionsStorage.setBooks(books);
		return result;
	}

	@Override
	public boolean delete(Book book) {
		List<Book> books = new ArrayList<>();
		books.addAll(LibraryCollectionsStorage.getBooks());
		boolean result = books.remove(book);
		LibraryCollectionsStorage.setBooks(books);
		return result;
	}

	@Override
	public boolean update(Book book) {
		List<Book> books = new ArrayList<>();
		books.addAll(LibraryCollectionsStorage.getBooks());
		for (Book bookThis : books) {
			if (bookThis.getIdBook() == book.getIdBook()) {
				bookThis.setIdBook(book.getIdBook());
				bookThis.setQuantity(book.getQuantity());
				bookThis.setTitle(book.getTitle());
				bookThis.setAuthor(book.getAuthor());
				LibraryCollectionsStorage.setBooks(books);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Book> findNotReturnBooksByEmployee(int id) {
		return null;
	}

}
