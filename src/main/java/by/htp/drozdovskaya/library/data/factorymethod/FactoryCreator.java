package by.htp.drozdovskaya.library.data.factorymethod;

import by.htp.drozdovskaya.library.data.factory.AbstractFactory;

public class FactoryCreator {

	private static final int DEFAULT_FACTORY_TYPE = 1;
	private static AbstractFactory FACTORY;

	public static AbstractFactory getFactory() {
		if (FACTORY == null) {
			initializeFactory(DEFAULT_FACTORY_TYPE);
		}
		return FACTORY;
	}

	public static void initializeFactory(int type) {
		AbstractFactoryCreator creator = null;
		Factories factoryType = Factories.INSTANCE.getTypeForCode(type);
		switch (factoryType) {
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
		FACTORY = creator.factoryMethod();
	}
}