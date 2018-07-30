package by.htp.drozdovskaya.library.run;

import java.util.Locale;
import java.util.Scanner;

public class Read {

	Scanner sc;

	public Read() {
		Locale locale = new Locale("ru", "RU");
		this.sc = new Scanner(System.in, "windows-1251");
		sc.useLocale(locale);
	}

	public int readNumber() {
		System.out.println("¬ведите число");		
		int a = 0;
		if(sc.hasNext()) {
		
			a = Integer.parseInt(sc.next());
		}
		sc.nextLine();
		return a;
	}

	public String readString() {

		return sc.next();
	}
	public String readLine() {
		String str = new String();
		sc.hasNext();
			str += sc.nextLine();
		return str;
	}

}
