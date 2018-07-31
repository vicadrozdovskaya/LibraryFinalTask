package by.htp.drozdovskaya.library.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LibraryCard {

	private int idCard;
	private String numberCard;
	private Calendar dateStart;
	private Calendar dateEnd;
	private int daysOverdue;
	private boolean isReturned;
	private Book book;
	private Employee employee;

	public LibraryCard() {
		this.dateStart = new GregorianCalendar();
		this.dateEnd = new GregorianCalendar();
	}

	

	public LibraryCard(int idCard, String numberCard, Calendar dateStart, Calendar dateEnd, int daysOverdue,
			boolean isReturned, Book book, Employee employee) {
		this.idCard = idCard;
		this.numberCard = numberCard;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.daysOverdue = daysOverdue;
		this.isReturned = isReturned;
		this.book = book;
		this.employee = employee;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public Calendar getDateStart() {
		return dateStart;
	}

	public void setDateStart(Calendar dateStart) {
		this.dateStart = dateStart;
	}

	public Calendar getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Calendar dateEnd) {
		this.dateEnd = dateEnd;
	}
	

	public int getDaysOverdue() {
		return daysOverdue;
	}

	public void setDaysOverdue(int daysOverdue) {
		this.daysOverdue = daysOverdue;
	}

	public boolean isReturned() {
		return isReturned;
	}

	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + daysOverdue;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + idCard;
		result = prime * result + (isReturned ? 1231 : 1237);
		result = prime * result + ((numberCard == null) ? 0 : numberCard.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryCard other = (LibraryCard) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		if (daysOverdue != other.daysOverdue)
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (idCard != other.idCard)
			return false;
		if (isReturned != other.isReturned)
			return false;
		if (numberCard == null) {
			if (other.numberCard != null)
				return false;
		} else if (!numberCard.equals(other.numberCard))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
		String str;
		if (isReturned) str = "да"; else str = "нет";
		return "LibraryCard number Card=" + numberCard + ", start Date=" + formatForDateNow.format(dateStart.getTime())
				+ ", end Date=" + formatForDateNow.format(dateEnd.getTime()) + ", days overdue=" + daysOverdue
				+ ", returned=" + str
				+ ",\nbook=" + book + ",\nemployee=" + employee;
	}

}
