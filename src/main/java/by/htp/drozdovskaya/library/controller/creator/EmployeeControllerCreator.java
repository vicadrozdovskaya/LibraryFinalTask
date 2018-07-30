package by.htp.drozdovskaya.library.controller.creator;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.controller.impl.EmployeeControllerImpl;

public class EmployeeControllerCreator extends ControllersCreator {

	@Override
	public ILibraryController factoryMethod() {
		return new EmployeeControllerImpl();
	}

}
