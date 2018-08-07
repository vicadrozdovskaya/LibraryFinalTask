package by.htp.drozdovskaya.library.dao.collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import by.htp.drozdovskaya.library.entity.Author;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.entity.User;

public class LibraryCollectionsStorage {

	private static LibraryCollectionsStorage INSTANCE;
	private int nextBookIdValue;
	private int nextAuthorIdValue;
	private int nextUserIdValue;
	private int nextEmployeeIdValue;
	private int nextLibraryCardIdValue;

	private List<Book> books;
	private List<Author> authors;
	private List<Employee> employee;
	private List<User> users;
	private List<LibraryCard> libraryCards;

	private LibraryCollectionsStorage() {
		this.books = new ArrayList<>();
		this.authors = new ArrayList<>();
		this.employee = new ArrayList<>();
		this.users = new ArrayList<>();
		this.libraryCards = new ArrayList<>();
		initLibraryCollections();

		this.nextBookIdValue = getMaxIdForBook();
		this.nextAuthorIdValue = getMaxIdForAuthor();
		this.nextEmployeeIdValue = getMaxIdForEmployee();
		this.nextLibraryCardIdValue = getMaxIdForLibraryCard();
		this.nextUserIdValue = getMaxIdForUser();
	}

	private static LibraryCollectionsStorage getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LibraryCollectionsStorage();
		}
		return INSTANCE;
	}

	public static List<Book> getBooks() {
		return getInstance().books;
	}

	public static void setBooks(List<Book> books) {
		getInstance().books.clear();
		getInstance().books.addAll(books);
	}

	public static int getNextBookId() {
		getInstance().nextBookIdValue++;
		return getInstance().nextBookIdValue;
	}

	public int getMaxIdForBook() {
		int id = 0;
		for (Book book : this.books) {
			if (book.getIdBook() > id)
				id = book.getIdBook();
		}
		return id;
	}

	public static int getNextAuthorId() {
		getInstance().nextAuthorIdValue++;
		return getInstance().nextAuthorIdValue;
	}

	public int getMaxIdForAuthor() {
		int id = 0;
		for (Author author : this.authors) {
			if (author.getIdAuthor() > id)
				id = author.getIdAuthor();
		}
		return id;
	}

	public static int getNextUserId() {
		getInstance().nextUserIdValue++;
		return getInstance().nextUserIdValue;
	}

	public int getMaxIdForUser() {
		int id = 0;
		for (User user : this.users) {
			if (user.getIdUser() > id)
				id = user.getIdUser();
		}
		return id;
	}

	public static int getNextEmployeeId() {
		getInstance().nextEmployeeIdValue++;
		return getInstance().nextEmployeeIdValue;
	}

	public int getMaxIdForEmployee() {
		int id = 0;
		for (Employee employee : this.employee) {
			if (employee.getIdEmployee() > id)
				id = employee.getIdEmployee();
		}
		return id;
	}

	public static int getNextLibraryCardId() {
		getInstance().nextLibraryCardIdValue++;
		return getInstance().nextLibraryCardIdValue;
	}

	public int getMaxIdForLibraryCard() {
		int id = 0;
		for (LibraryCard libraryCard : this.libraryCards) {
			if (libraryCard.getIdCard() > id)
				id = libraryCard.getIdCard();
		}
		return id;
	}

	public static List<User> getUsers() {
		return getInstance().users;
	}

	public static void setUsers(List<User> users) {
		getInstance().users.clear();
		getInstance().users.addAll(users);
	}

	public static List<Author> getAuthors() {
		return getInstance().authors;
	}

	public static void setAuthors(List<Author> authors) {
		getInstance().authors.clear();
		getInstance().authors.addAll(authors);
	}

	public static List<Employee> getEmployee() {
		return getInstance().employee;
	}

	public static void setEmployee(List<Employee> employee) {
		getInstance().employee.clear();
		getInstance().employee.addAll(employee);
	}

	public static List<LibraryCard> getLibraryCards() {
		return getInstance().libraryCards;
	}

	public static void setLibraryCards(List<LibraryCard> libraryCards) {
		getInstance().libraryCards.clear();
		getInstance().libraryCards.addAll(libraryCards);
	}

	private void initLibraryCollections() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Calendar date1 = new GregorianCalendar();
			date1.setTime(format.parse("06-06-1776"));
			this.authors.add(new Author(1, "Александр", "Пушкин", date1));
			Calendar date2 = new GregorianCalendar();
			date2.setTime(format.parse("22-06-1964"));
			this.authors.add(new Author(2, "Dan", "Brown", date2));
			Calendar date3 = new GregorianCalendar();
			date3.setTime(format.parse("08-07-1957"));
			this.authors.add(new Author(3, "Eckel", "Bruce", date3));
			Calendar date4 = new GregorianCalendar();
			date4.setTime(format.parse("12-05-1892"));
			this.authors.add(new Author(4, "Джон", "Толкиен", date4));
			this.books.add(new Book(1, "Евгений Онегин", 4, this.authors.get(0)));
			this.books.add(new Book(2, "Дубровский", 2, this.authors.get(0)));
			this.books.add(new Book(3, "Цифровая крепость", 2, this.authors.get(1)));
			this.books.add(new Book(4, "Точка обмана", 2, this.authors.get(1)));
			this.books.add(new Book(5, "Ангелы и Демоны", 2, this.authors.get(1)));
			this.books.add(new Book(6, "Thinking in Java, 4th Edition", 2, this.authors.get(2)));
			this.books.add(new Book(7, "Хоббит", 3, this.authors.get(3)));
			this.books.add(new Book(8, "Властелин колец: Хранители", 3, this.authors.get(3)));
			this.users.add(new User(0, "library", "library1", 1));
			this.users.add(new User(1, "NK0001", "password1", 0));
			this.users.add(new User(2, "DB0002", "password2", 0));
			this.users.add(new User(3, "OS0003", "password3", 0));
			this.users.add(new User(4, "AD0004", "password4", 0));
			this.users.add(new User(5, "KB0005", "password5", 0));
			Calendar dateStart1 = new GregorianCalendar();
			dateStart1.setTime(format.parse("15-07-2018"));
			Calendar dateEnd1 = new GregorianCalendar();
			dateEnd1.setTime(format.parse("18-07-2018"));
			this.employee.add(
					new Employee(1, "Надежда", "Климашевич", "+375296892541", "отдел маркетинга", this.users.get(1)));
			this.employee
					.add(new Employee(2, "Дмитрий", "Бук", "+375294853215", "отдел разработки ПО", this.users.get(2)));
			this.employee
					.add(new Employee(3, "Ольга", "Самусева", "+375298541782", "отдел маркетинга", this.users.get(3)));
			this.employee.add(
					new Employee(4, "Артём", "Дроздовский", "+375291965983", "отдел разработки ПО", this.users.get(4)));
			this.employee
					.add(new Employee(5, "Ксения", "Боболя", "+375293689388", "отдел дизайнеров", this.users.get(5)));
			Calendar dateStart2 = new GregorianCalendar();
			dateStart2.setTime(format.parse("01-07-2018"));
			Calendar dateEnd2 = new GregorianCalendar();
			dateEnd2.setTime(format.parse("10-07-2018"));
			Calendar dateEnd3 = new GregorianCalendar();
			dateEnd3.setTime(format.parse("31-07-2018"));
			Calendar dateEnd4 = new GregorianCalendar();
			dateEnd4.setTime(format.parse("25-07-2018"));
			this.libraryCards.add(new LibraryCard(1, "NK0001", dateStart1, dateEnd1, 0, true, this.books.get(1),
					this.employee.get(0)));
			this.libraryCards.add(new LibraryCard(3, "NK0001", dateStart2, dateEnd2, 0, true, this.books.get(3),
					this.employee.get(0)));
			this.libraryCards.add(new LibraryCard(2, "AD0004", dateStart2, dateEnd3, 0, false, this.books.get(3),
					this.employee.get(3)));
			this.libraryCards.add(new LibraryCard(4, "DB0002", dateStart2, dateEnd4, 0, false, this.books.get(5),
					this.employee.get(1)));
			this.libraryCards.add(new LibraryCard(5, "DB0002", dateStart1, dateEnd3, 0, false, this.books.get(6),
					this.employee.get(1)));
			this.libraryCards.add(new LibraryCard(6, "DB0002", dateStart2, dateEnd3, 0, false, this.books.get(4),
					this.employee.get(1)));
			this.libraryCards.add(new LibraryCard(7, "AD0004", dateStart2, dateEnd4, 0, true, this.books.get(3),
					this.employee.get(3)));
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}

}
