package by.htp.drozdovskaya.library.dao.collections;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.entity.User;

public class LibraryCardCollectionImpl implements ILibraryCardDao {

	@Override
	public LibraryCard get(int id) {
		List<LibraryCard> libraryCards = LibraryCollectionsStorage.getLibraryCards();
		LibraryCard returnLibraryCard = new LibraryCard();
		for (LibraryCard libraryCard : libraryCards) {
			if (libraryCard.getIdCard() == id) {
				returnLibraryCard.setIdCard(libraryCard.getIdCard());
				returnLibraryCard.setDateStart(libraryCard.getDateStart());
				returnLibraryCard.setDateEnd(libraryCard.getDateEnd());
				returnLibraryCard.setDaysOverdue(libraryCard.getDaysOverdue());
				returnLibraryCard.setNumberCard(libraryCard.getNumberCard());
				returnLibraryCard.setReturned(libraryCard.isReturned());
				returnLibraryCard.setBook(libraryCard.getBook());
				returnLibraryCard.setEmployee(libraryCard.getEmployee());
			}
		}
		return returnLibraryCard;
	}

	@Override
	public boolean insert(LibraryCard lCard) {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		lCard.setIdCard(LibraryCollectionsStorage.getNextLibraryCardId());
		boolean result = libraryCards.add(lCard);
		LibraryCollectionsStorage.setLibraryCards(libraryCards);
		return result;
	}

	@Override
	public boolean update(LibraryCard lCard) {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		for (LibraryCard lCardThis : libraryCards) {
			if (lCardThis.getIdCard() == lCard.getIdCard()) {
				lCardThis.setIdCard(lCard.getIdCard());
				lCardThis.setDateStart(lCard.getDateStart());
				lCardThis.setDateEnd(lCard.getDateEnd());
				lCardThis.setDaysOverdue(lCard.getDaysOverdue());
				lCardThis.setNumberCard(lCard.getNumberCard());
				lCardThis.setReturned(lCard.isReturned());
				lCardThis.setBook(lCard.getBook());
				lCardThis.setEmployee(lCard.getEmployee());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(LibraryCard lCard) {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		boolean result = libraryCards.remove(lCard);
		LibraryCollectionsStorage.setLibraryCards(libraryCards);
		return result;
	}

	@Override
	public List<LibraryCard> getAll() {
		List<LibraryCard> libraryCards = LibraryCollectionsStorage.getLibraryCards();
		return libraryCards;
	}

	@Override
	public List<LibraryCard> findCardsByEmployee(int id) {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		List<LibraryCard> libraryCardsByEmploye = new ArrayList<>();
		for (LibraryCard lCardThis : libraryCards) {
			if (lCardThis.getEmployee().getIdEmployee() == id) {
				libraryCardsByEmploye.add(lCardThis);
			}
		}
		return libraryCardsByEmploye;
	}

	@Override
	public LibraryCard findCardByEmployeeAndBook(int idEmployee, int idBook) {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		LibraryCard libraryCard = new LibraryCard();
		for (LibraryCard lCardThis : libraryCards) {
			if (lCardThis.getEmployee().getIdEmployee() == idEmployee && lCardThis.getBook().getIdBook() == idBook) {
				libraryCard = lCardThis;
			}
		}
		return libraryCard;
	}

	@Override
	public Map<Employee, Map<Book, LibraryCard>> getReadersWithOverdue(List<User> users) {
		calculateOverdue();
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		Map<Book, LibraryCard> bookMap = new HashMap<>();
		Map<Employee, Map<Book, LibraryCard>> userMap = new HashMap<>();
		for (User u : users) {
			bookMap = getReaderWithOverdue(u);
			if (!bookMap.isEmpty()) {
				for (LibraryCard libraryCard : libraryCards) {
					if (libraryCard.getEmployee().getIdEmployee() == u.getIdUser())
						userMap.put(libraryCard.getEmployee(), bookMap);
				}
			}
		}
		return userMap;
	}

	@Override
	public Map<Book, LibraryCard> getReaderWithOverdue(User user) {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		Map<Book, LibraryCard> bookMap = new HashMap<>();
		for (LibraryCard lCardThis : libraryCards) {
			if (lCardThis.getDaysOverdue() > 0 && lCardThis.getNumberCard().equals(user.getLogin())) {
				bookMap.put(lCardThis.getBook(), lCardThis);
			}
		}

		return bookMap;
	}

	@Override
	public void calculateOverdue() {
		ZonedDateTime zdt1 = ZonedDateTime.now();
		for (LibraryCard lC : getNotReturnCards()) {
			if (!lC.isReturned()) {
				Period period = Period.between(
						lC.getDateEnd().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						zdt1.toLocalDate());
				int k = period.getDays();
				if (k > 0) {
					lC.setDaysOverdue(k);
					updateOverdue(lC);
				}
			}
		}
	}

	private List<LibraryCard> getNotReturnCards() {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		List<LibraryCard> libraryCardsNotReturned = new ArrayList<>();
		for (LibraryCard lCardThis : libraryCards) {
			if (!lCardThis.isReturned()) {
				libraryCardsNotReturned.add(lCardThis);
			}
		}
		return libraryCardsNotReturned;
	}

	private boolean updateOverdue(LibraryCard lCard) {
		List<LibraryCard> libraryCards = new ArrayList<>();
		libraryCards.addAll(LibraryCollectionsStorage.getLibraryCards());
		for (LibraryCard lCardThis : libraryCards) {
			if (lCardThis.getIdCard() == lCard.getIdCard()) {
				lCardThis.setDaysOverdue(lCard.getDaysOverdue());
				return true;
			}
		}
		return false;
	}

}
