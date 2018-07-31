package by.htp.drozdovskaya.library.dao.creator;

import by.htp.drozdovskaya.library.dao.IDao;;;

public abstract class DaoCreator<T> {

	public abstract IDao<T> factoryMethod();
}
