package by.htp.drozdovskaya.library.data.factorymethod;

public enum Factories {
	INSTANCE(-1), MYSQL(1), COLLECTIONS(2);

	private int code;

	private Factories(int code) {
		this.code = code;
	}

	private int getType() {
		return this.code;
	}

	public Factories getTypeForCode(int code) {
		for (Factories type : Factories.values()) {
			if (type.getType() == code) {
				return type;
			}
		}
		return Factories.MYSQL;
	}

}
