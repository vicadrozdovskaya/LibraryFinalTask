package by.htp.drozdovskaya.library.data.factorymethod;

import by.htp.drozdovskaya.library.data.factory.AbstractFactory;

public class Creator {

	public static AbstractFactory loadFactory(Factories type) {
		FactoryCreator creator = null;
		switch (type) {
			case COLLECTIONS: {
				creator = new CollectionFactoryCreator();
				break;
			}
			case MYSQL: {
				creator = new MySQLDaoFactoryCreator();
				break;
			}
			default: {
				creator = new MySQLDaoFactoryCreator();
				break;
			}
		}
		return creator.factoryMethod();
	}
}