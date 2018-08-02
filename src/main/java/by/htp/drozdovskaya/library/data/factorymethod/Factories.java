package by.htp.drozdovskaya.library.data.factorymethod;

public enum Factories {
	MYSQL(1), COLLECTIONS(2);

	private int code;

	private Factories(int code) {
		this.code = code;
	}
	public int getType() {
		return this.code;
	}
}
