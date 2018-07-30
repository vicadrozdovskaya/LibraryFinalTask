package by.htp.drozdovskaya.library.controller.creator;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.controller.impl.BookControllerImpl;

public class BookControllerCreator extends ControllersCreator {

	@Override
	public ILibraryController factoryMethod() {
		return new BookControllerImpl();
	}

}
