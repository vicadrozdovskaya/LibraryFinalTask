package by.htp.drozdovskaya.library.dao.creator;

import by.htp.drozdovskaya.library.dao.IDao;
import by.htp.drozdovskaya.library.dao.impl.UserDaoImpl;
import by.htp.drozdovskaya.library.entity.User;

public class UserDaoCreator extends DaoCreator<User> {

	@Override
	public IDao<User> factoryMethod() {
		return new UserDaoImpl();
	}

}
