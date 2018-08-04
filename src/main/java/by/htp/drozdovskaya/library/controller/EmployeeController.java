package by.htp.drozdovskaya.library.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.logic.BookLogic;
import by.htp.drozdovskaya.library.logic.UserLogic;

public class EmployeeController {

	private UserLogic userLogic;
	private BookLogic bookLogic;

	public EmployeeController() {
		this.userLogic = new UserLogic();
		this.bookLogic = new BookLogic();

	}

	public void showListOfBooks() {
		List<Book> books = bookLogic.getListOfBooks();
		for (Book book : books) {
			System.out.println(book.getIdBook() + " " + book.getTitle() + " " + book.getQuantity() + " шт.");
		}
	}

	public void showDetailOfBook(int idBook) {
		Book book = bookLogic.getBook(idBook);
		System.out.println(book.getAuthor().getName() + " " + book.getAuthor().getSurname() + " " + book.getTitle()
				+ " " + book.getQuantity() + " шт. ");

	}

	public void showNotificationAboutDebts(User user) {
		Map<Book, LibraryCard> books = userLogic.getBooksWithOverdue(user);
		Set<Book> keySet = books.keySet();
		if (!books.isEmpty()) {
			System.out.println("Книги с просрочкой");
			for (Book b : keySet) {
				System.out.println("Книга: " + b.getTitle() + " Просрочка=" + books.get(b).getDaysOverdue());
			}
		} else {
			System.out.println("Книг с просроченным днем сдачи нет");
		}
	}
}
