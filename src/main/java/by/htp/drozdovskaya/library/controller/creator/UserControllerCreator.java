package by.htp.drozdovskaya.library.controller.creator;

import by.htp.drozdovskaya.library.controller.IAuthorizationController;
import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.controller.impl.UserControllerImpl;

public class UserControllerCreator extends ControllersCreator {

	@Override
	public ILibraryController factoryMethod() {
		return new UserControllerImpl();
	}
	public IAuthorizationController authorizationMethod() {
		return new UserControllerImpl();
	}

}
