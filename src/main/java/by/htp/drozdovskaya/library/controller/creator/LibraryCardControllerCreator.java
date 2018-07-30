package by.htp.drozdovskaya.library.controller.creator;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.controller.impl.LibraryCardControllerImpl;

public class LibraryCardControllerCreator extends ControllersCreator {

	@Override
	public ILibraryController factoryMethod() {
		return new LibraryCardControllerImpl();
	}

}
