package by.htp.drozdovskaya.library.run.config;

import java.util.ResourceBundle;

public class  ApplicationConfigurator {
	
	private static final ResourceBundle rb;

	static {
		rb = ResourceBundle.getBundle("application");
	}

	public static int getStorageType() {
		return Integer.parseInt(rb.getString("storage_type"));
	}

}
