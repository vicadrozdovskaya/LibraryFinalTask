package by.htp.drozdovskaya.library.controller.creator;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.controller.impl.AuthorControllerImpl;

public class AuthorControllerCreator extends ControllersCreator {

	@Override
	public ILibraryController factoryMethod() {
		return new AuthorControllerImpl();
	}

}
