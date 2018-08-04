package by.htp.drozdovskaya.library.data.factorymethod;

import by.htp.drozdovskaya.library.data.factory.AbstractFactory;
import by.htp.drozdovskaya.library.data.factory.CollectionFactory;

public class CollectionFactoryCreator extends AbstractFactoryCreator {

	@Override
	public AbstractFactory factoryMethod() {
		return new CollectionFactory();
	}

}
