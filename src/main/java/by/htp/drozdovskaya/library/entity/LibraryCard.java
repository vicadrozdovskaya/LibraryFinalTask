package by.htp.drozdovskaya.library.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LibraryCard {

	private int idCard;
	private Calendar dateStart;
	private Calendar dateEnd;
	private Book book;
	private Employee employee;

	public LibraryCard() {
		this.dateStart = new GregorianCalendar();
		this.dateEnd = new GregorianCalendar();
	}

	public LibraryCard(int idCard, Calendar dateStart, Calendar dateEnd, Book book, Employee employee) {
		this.idCard = idCard;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.book = book;
		this.employee = employee;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
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
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + idCard;
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
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (idCard != other.idCard)
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy");
		return "LibraryCard idCard=" + idCard + ", start Date=" + formatForDateNow.format(dateStart.getTime())
				+ ", end Date=" + formatForDateNow.format(dateEnd.getTime()) 
				+ ",\nbook=" + book + ",\nemployee=" + employee;
	}

}
