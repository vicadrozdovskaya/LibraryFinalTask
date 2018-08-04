package by.htp.drozdovskaya.library.logic;

import java.util.Map;

import by.htp.drozdovskaya.library.dao.ILibraryCardDao;
import by.htp.drozdovskaya.library.dao.IUserDao;
import by.htp.drozdovskaya.library.data.factorymethod.FactoryCreator;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.LibraryCard;
import by.htp.drozdovskaya.library.entity.User;

public class UserLogic {

	private IUserDao userDao;
	private ILibraryCardDao libCardDao;

	public UserLogic() {
		this.userDao = FactoryCreator.getFactory().createUserDao();
		this.libCardDao = FactoryCreator.getFactory().createLibraryCardDao();
	}

	public User authorizeUserByLoginAndPassword(String login, String password) {
		User user = new User();
		User userFromStorage = userDao.getUser(login);

		if (userFromStorage == null)
			return user;

		if (userFromStorage.getPassword().equals(password)) {
			user.setIdUser(userFromStorage.getIdUser()); 
			user.setLogin(userFromStorage.getLogin());
			user.setPassword(userFromStorage.getPassword());
			user.setRole(userFromStorage.getRole());
		}
		return user;
	}

	public Map<Book, LibraryCard> getBooksWithOverdue(User user) {
		Map<Book, LibraryCard> books = libCardDao.getReaderWithOverdue(user);
		return books;
	}
	
	public User createUser(String login, String password, int role) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setRole(role);
		userDao.insert(user);
		return user;
	}
}
