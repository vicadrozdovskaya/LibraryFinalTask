package by.htp.drozdovskaya.library.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.htp.drozdovskaya.library.entity.Author;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.entity.User;
import by.htp.drozdovskaya.library.logic.AuthorLogic;
import by.htp.drozdovskaya.library.logic.BookLogic;
import by.htp.drozdovskaya.library.logic.EmployeeLogic;
import by.htp.drozdovskaya.library.logic.LibraryCardLogic;
import by.htp.drozdovskaya.library.logic.ReportLogic;
import by.htp.drozdovskaya.library.logic.UserLogic;
import by.htp.drozdovskaya.library.menu.impl.MainMenu;
import by.htp.drozdovskaya.library.menu.impl.additional.AuthorMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.additional.BookMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.additional.EmployeeDataMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.additional.LibraryCardMenuImpl;
import by.htp.drozdovskaya.library.menu.impl.additional.ReportMenuImpl;
import by.htp.drozdovskaya.library.run.Read;

public class LibrarianController {

	private UserLogic userLogic;
	private BookLogic bookLogic;
	private AuthorLogic authorLogic;
	private EmployeeLogic employeeLogic;
	private LibraryCardLogic libraryCardLogic;
	private ReportLogic reportLogic;

	private Read read;

	private MainMenu menu;

	public LibrarianController() {
		this.userLogic = new UserLogic();
		this.bookLogic = new BookLogic();
		this.authorLogic = new AuthorLogic();
		this.employeeLogic = new EmployeeLogic();
		this.libraryCardLogic = new LibraryCardLogic();
		this.reportLogic = new ReportLogic();
		this.read = new Read();

	}

	public void workWithBooks() {
		menu = new BookMenuImpl();
		boolean flag = true;
		while (flag) {
			menu.menu();
			int choice = read.readNumber();
			switch (choice) {
			case 1: {
				this.showListOfBooks();
				break;
			}
			case 2: {
				this.addBook();
				break;
			}
			case 3: {
				this.changeBookData();
				break;
			}
			case 4: {
				this.deleteBook();
				break;
			}
			case 5: {
				flag = false;
				break;
			}
			}
		}
	}

	public void workWithAuthor() {
		menu = new AuthorMenuImpl();
		boolean flag = true;
		while (flag) {
			menu.menu();
			int choice = read.readNumber();
			switch (choice) {
			case 1: {
				this.showListOfAuthors();
				break;
			}
			case 2: {
				this.addAuthor();
				break;
			}
			case 3: {
				this.changeAuthorData();
				break;
			}
			case 4: {
				this.deleteAuthor();
				break;
			}
			case 5: {
				flag = false;
				break;
			}
			}
		}
	}

	public void workWithEmployee() {
		menu = new EmployeeDataMenuImpl();
		boolean flag = true;
		while (flag) {
			menu.menu();
			int choice = read.readNumber();
			switch (choice) {
			case 1: {
				this.showListOfEmployee();
				break;
			}
			case 2: {
				this.addEmployee();
				break;
			}
			case 3: {
				this.changeEmployeeData();
				break;
			}
			case 4: {
				this.deleteEmployee();
				break;
			}
			case 5: {
				flag = false;
				break;
			}
			}
		}
	}

	public void workWithLibrarianCard() {
		menu = new LibraryCardMenuImpl();
		boolean flag = true;
		while (flag) {
			menu.menu();
			int choice = read.readNumber();
			switch (choice) {
			case 1: {
				this.showListOfLibraryCard();
				break;
			}
			case 2: {
				this.addBookInLibraryCard();
				break;
			}
			case 3: {
				this.returnBookToLibrary();
				break;
			}
			case 4: {
				flag = false;
				break;
			}
			}
		}
	}

	public void workWithReports() {
		menu = new ReportMenuImpl();
		boolean flag = true;
		while (flag) {
			menu.menu();
			int choice = read.readNumber();
			switch (choice) {
			case 1: {
				this.showFirstReport();
				break;
			}
			case 2: {
				this.showSecondReport();
				break;
			}
			case 3: {
				this.showThirdReport();
				break;
			}
			case 4: {
				flag = false;
				break;
			}
			}
		}
	}

	private void showListOfBooks() {
		List<Book> books = bookLogic.getListOfBooks();
		for (Book book : books) {
			System.out.println(book.getIdBook() + " " + book.getTitle() + " " + book.getQuantity() + " ��.");
		}
	}

	private void showListOfAuthors() {
		List<Author> authors = authorLogic.getListOfAuthors();
		for (Author author : authors) {
			System.out.println(author);
		}
	}

	private void addBook() {
		System.out.println("������� �������� �����");
		String title = read.readLine();
		System.out.println("������� ���������� ����");
		int quantity = read.readNumber();
		showListOfAuthors();
		System.out.println("�������� ������ �����: ������� ��� ID");
		int idAuthor = read.readNumber();
		bookLogic.createBook(title, quantity, idAuthor);
	}

	private void changeBookData() {
		System.out.println("�������� ����� ��� ��������������: ������� �� ID");
		this.showListOfBooks();
		int idBook = read.readNumber();
		System.out.println("������� �������� �����");
		String title = read.readLine();
		System.out.println("������� ���������� ����");
		int quantity = read.readNumber();
		showListOfAuthors();
		System.out.println("�������� ������ �����: ������� ��� ID");
		int idAuthor = read.readNumber();
		bookLogic.updateBook(idBook, title, quantity, idAuthor);
	}

	private void deleteBook() {
		System.out.println("�������� ����� ��� ��������: ������� �� ID");
		this.showListOfBooks();
		int idBook = read.readNumber();
		bookLogic.deleteBook(idBook);
	}

	private void addAuthor() {
		System.out.println("������� ��� ������");
		String name = read.readLine();
		System.out.println("������� ������� ������");
		String surname = read.readLine();
		System.out.println("������� ���� �������� ������ � ������� ��-��-����");
		Calendar birthDate = Calendar.getInstance();
		try {

			String str = read.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			birthDate.setTime(dateFormat.parse(str));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		authorLogic.createAuthor(name, surname, birthDate);
	}

	private void changeAuthorData() {
		System.out.println("�������� ������ ��� ��������������: ������� ��� ID");
		this.showListOfAuthors();
		int idAuthor = read.readNumber();
		System.out.println("������� ��� ������");
		String name = read.readLine();
		System.out.println("������� ������� ������");
		String surname = read.readLine();
		System.out.println("������� ���� �������� ������ � ������� ��-��-����");
		Calendar birthDate = Calendar.getInstance();
		try {

			String str = read.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			birthDate.setTime(dateFormat.parse(str));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		authorLogic.updateAuthor(idAuthor, name, surname, birthDate);
	}

	private void deleteAuthor() {
		System.out.println("�������� ������ ��� ��������: ������� ��� ID");
		this.showListOfAuthors();
		int idAuthor = read.readNumber();
		authorLogic.deleteAuthor(idAuthor);
	}

	private void showListOfEmployee() {
		List<Employee> employee = employeeLogic.getListOfEmployee();
		for (Employee employe : employee) {
			System.out.println(employe.getIdEmployee() + " " + employe.getName() + " " + employe.getSurname() + " "
					+ employe.getPhone() + " " + employe.getDepartment());
		}
	}

	private User addUser() {
		System.out.println("������� login");
		String login = read.readLine();
		System.out.println("������� password");
		String password = read.readLine();
		System.out.println("������� ����� ����: ��������(0)/������������(1)");
		int role = read.readNumber();
		return userLogic.createUser(login, password, role);
	}

	private void addEmployee() {
		User user = this.addUser();
		System.out.println("������� ��� ����������");
		String name = read.readLine();
		System.out.println("������� ������� ����������");
		String surname = read.readLine();
		System.out.println("������� ������� ����������");
		String phone = read.readLine();
		System.out.println("������� �������������, � ������� �������� ���������");
		String department = read.readLine();
		employeeLogic.createEmployee(name, surname, phone, department, user);
	}

	private void changeEmployeeData() {
		System.out.println("�������� Id ����������, �������� ������ ��������");
		this.showListOfEmployee();
		int idEmploye = read.readNumber();
		System.out.println("������� ��� ����������");
		String name = read.readLine();
		System.out.println("������� ������� ����������");
		String surname = read.readLine();
		System.out.println("������� ������� ����������");
		String phone = read.readLine();
		System.out.println("������� �������������, � ������� �������� ���������");
		String department = read.readLine();
		employeeLogic.updateEmployee(idEmploye, name, surname, phone, department);
	}

	private void deleteEmployee() {
		System.out.println("�������� Id ����������, �������� ������ ��������");
		this.showListOfEmployee();
		int idEmploye = read.readNumber();
		employeeLogic.deleteEmployee(idEmploye);
	}

	private void showListOfLibraryCard() {
		List<LibraryCard> libraryCards = libraryCardLogic.getListOfBooks();
		for (LibraryCard libraryCard : libraryCards) {
			System.out.println(libraryCard);
		}
	}

	private void addBookInLibraryCard() {
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, 30);
		System.out.println("�������� ����������: ������� ��� ID");
		this.showListOfEmployee();
		int idEmployee = read.readNumber();
		if (!bookLogic.checkQuantityBooksByEmployee(idEmployee)) {
			if (!libraryCardLogic.checkOverdueByEmployee(idEmployee)) {
				this.showListOfBooks();
				System.out.println("�������� �����: ������� �� ID");
				int idBook = read.readNumber();
				libraryCardLogic.createLibraryCard(endDate, idBook, idEmployee);
			} else {
				System.out.println("� ����� ���������� ���� ��������� �� �������� ����");
			}
		} else {
			System.out.println("� ����� ���������� ��� 3 ����� �� ����� ");
		}
	}

	private void showBooksByEmploye(int idEmployee) {
		List<Book> books = libraryCardLogic.getBooksByEmployee(idEmployee);
		for (Book book : books) {
			System.out.println(book.getIdBook() + " " + book.getTitle() + " " + book.getQuantity() + " ��.");
		}
	}

	private void returnBookToLibrary() {
		System.out.println("�������� ����������: ������� ��� ID");
		this.showListOfEmployee();
		int idEmployee = read.readNumber();
		System.out.println("�������� �����: ������� �� ID");
		this.showBooksByEmploye(idEmployee);
		int idBook = read.readNumber();
		libraryCardLogic.returnBookToLibrary(idBook, idEmployee);
	}

	private void showFirstReport() {
		Map<Employee, Map<Book, LibraryCard>> data = reportLogic.generateFirstReportData();
		System.out.println(
				"\n����� � ���������, � ������� ������� ������������� �� �������� ����\n-------------------------------------------------------------------");
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

		Set<Employee> keySetEmployee = data.keySet();
		for (Employee e : keySetEmployee) {
			System.out.println(e.getName() + " " + e.getSurname() + " " + e.getPhone());

			Set<Book> keySet = data.get(e).keySet();
			Map<Book, LibraryCard> books = data.get(e);
			for (Book b : keySet) {
				System.out.println("�����: " + b.getTitle() + " ���� ������ �����: "
						+ formatForDateNow.format(books.get(b).getDateStart().getTime()) + " ���������="
						+ books.get(b).getDaysOverdue() + " �.");
			}

		}
	}

	private void showSecondReport() {
		Map<Book, Integer> books = reportLogic.generateSecondReportData();
		System.out.println("\n����� � ����������� ������\n--------------------------------------------------");
		Set<Book> keySet = books.keySet();
		for (Book b : keySet) {
			System.out.println(b.getTitle() + " " + books.get(b) + " ��� ���������");
		}
	}

	private void showThirdReport() {
		Map<Employee, Integer> employeeMap = reportLogic.generateThirdReportData();
		System.out.println(
				"\n����� � �����������, ������� ��������� �� ����� 2-� � �� ����� 8-�� ���� �� �����\n--------------------------------------------------");
		Set<Employee> keySetEmployee = employeeMap.keySet();
		for (Employee e : keySetEmployee) {
			System.out.println(e.getName() + " " + e.getSurname() + " " + e.getPhone() + " " + e.getDepartment()
					+ " ���������� ����������� ����: " + employeeMap.get(e) + " ��.");
		}
	}
}
