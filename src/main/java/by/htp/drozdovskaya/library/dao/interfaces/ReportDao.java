package by.htp.drozdovskaya.library.dao.interfaces;

import java.util.List;

import by.htp.drozdovskaya.library.entity.LibraryCard;

public interface ReportDao {

	public List<LibraryCard> reportEmployeeReadBooks(String month);
}
