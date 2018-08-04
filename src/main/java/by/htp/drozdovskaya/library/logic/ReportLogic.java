package by.htp.drozdovskaya.library.logic;

import java.util.Map;

import by.htp.drozdovskaya.library.dao.IReportDao;
import by.htp.drozdovskaya.library.data.factorymethod.FactoryCreator;
import by.htp.drozdovskaya.library.entity.Book;
import by.htp.drozdovskaya.library.entity.Employee;
import by.htp.drozdovskaya.library.entity.LibraryCard;

public class ReportLogic {

	private IReportDao reportDao;

	public ReportLogic() {
		reportDao = FactoryCreator.getFactory().createReportDao();
	}

	public Map<Employee, Map<Book, LibraryCard>> generateFirstReportData() {
		return reportDao.readersNotReturnBooks();
	}

	public Map<Book, Integer> generateSecondReportData() {
		return reportDao.popularBooks();

	}

	public Map<Employee, Integer> generateThirdReportData() {
		return reportDao.employeeReadBooksByMonth(2, 8);

	}

}
