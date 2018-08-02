package by.htp.drozdovskaya.library.dao.mysql.util;

import java.util.Properties;
import java.util.ResourceBundle;

public class MySqlPropertyManager {

	private static final ResourceBundle rb;

	static {
		rb = ResourceBundle.getBundle("db_config");
	}

	public static String getUrl() {
		return rb.getString("db.url");
	}

	public static Properties getProperties() {
		Properties props = new Properties();
		props.put("useUnicode", "true");
		props.put("characterEncoding", "Cp1251");
		props.put("user", rb.getString("db.login"));
		props.put("password", getPassword());
		return props;
	}

	public static String getPassword() {
		return rb.getString("db.pass");
	}

}
