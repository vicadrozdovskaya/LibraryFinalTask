package by.htp.drozdovskaya.library.data.factorymethod;

import by.htp.drozdovskaya.library.data.factory.AbstractFactory;
import by.htp.drozdovskaya.library.data.factory.MySQLFactory;

public class MySQLDaoFactoryCreator extends AbstractFactoryCreator{

	@Override
	public AbstractFactory factoryMethod() {
		return new MySQLFactory();
	}

}
