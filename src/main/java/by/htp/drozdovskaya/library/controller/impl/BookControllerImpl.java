package by.htp.drozdovskaya.library.controller.impl;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.dao.IDao;
import by.htp.drozdovskaya.library.dao.creator.AuthorDaoCreator;
import by.htp.drozdovskaya.library.dao.creator.BookDaoCreator;
import by.htp.drozdovskaya.library.entity.Author;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.run.Read;

public class BookControllerImpl implements ILibraryController {

	private IDao<Book> dao;
	private IDao<Author> authorDao;
	private Book book;
	private Read read;

	public BookControllerImpl() {
		dao = new BookDaoCreator().factoryMethod();
		authorDao = new AuthorDaoCreator().factoryMethod();
		read = new Read();
		book = new Book();
	}

	@Override
	public void showAll() {
		for (Book b : dao.getAll()) {
			System.out.println(b);
		}
	}

	@Override
	public boolean insert() {
		System.out.println("Введите название книги");
		String title = read.readLine();
		System.out.println("Введите количество книг");
		int quantity = read.readNumber();
		for (Author a : authorDao.getAll()) {
			System.out.println(a);
		}
		System.out.println("Выберите автора книги: введите его ID");
		int id_author = read.readNumber();
		Author author = authorDao.get(id_author);
		book.setTitle(title);
		book.setQuantity(quantity);
		book.setAuthor(author);
		if (dao.insert(book) == true) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("Выберите книгу для редактирования: введите ее ID");
		int id_book = read.readNumber();
		System.out.println("Введите название книги");
		String title = read.readLine();
		System.out.println("Введите количество книг");
		int quantity = read.readNumber();
		for (Author a : authorDao.getAll()) {
			System.out.println(a);
		}
		System.out.println("Выберите автора книги: введите его ID");
		int id_author = read.readNumber();
		Author author = authorDao.get(id_author);
		book.setIdBook(id_book);
		book.setQuantity(quantity);
		book.setTitle(title);
		book.setAuthor(author);
		if (dao.update(book)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("Выберите книгу для удаления: введите ее ID");
		int id_book = read.readNumber();
		book.setIdBook(id_book);
		if (dao.update(book)) {
			return true;
		} else {
			return false;
		}
	}

}
