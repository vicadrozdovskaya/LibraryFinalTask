package by.htp.drozdovskaya.library.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import by.htp.drozdovskaya.library.controller.ILibraryController;
import by.htp.drozdovskaya.library.dao.creator.AuthorDaoCreator;
import by.htp.drozdovskaya.library.dao.interfaces.IDao;
import by.htp.drozdovskaya.library.entity.Author;
import by.htp.drozdovskaya.library.run.Read;

public class AuthorControllerImpl implements ILibraryController {

	private IDao<Author> authorDao;
	private Read read;

	public AuthorControllerImpl() {
		authorDao = new AuthorDaoCreator().factoryMethod();
		read = new Read();
	}

	@Override
	public void showAll() {
		for (Author a : authorDao.getAll()) {
			System.out.println(a);
		}
	}

	@Override
	public boolean insert() {
		System.out.println("Введите имя автора");
		String name = read.readLine();
		System.out.println("Введите фамилию автора");
		String surname = read.readLine();
		System.out.println("Введите дату рождения автора в формате дд-мм-гггг");
		try {
			Calendar birthDate = Calendar.getInstance();
			String str = read.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			birthDate.setTime(dateFormat.parse(str));
			Author author = new Author();
			author.setName(name);
			author.setSurname(surname);
			author.setBirthDate(birthDate);
			if (authorDao.insert(author)) {
				System.out.println("Автор успешно добавлен");
				return true;
			} else {
				System.out.println("Произошла ошибка при добавлении, попрубуйте снова");
				return false;
			}
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update() {
		showAll();
		System.out.println("Выберите автора для редактирования: введите его ID");
		int id_author = read.readNumber();
		System.out.println("Введите имя автора");
		String name = read.readLine();
		System.out.println("Введите фамилию автора");
		String surname = read.readLine();
		System.out.println("Введите дату рождения автора в формате дд-мм-гггг");
		try {
			Calendar birthDate = Calendar.getInstance();
			String str = read.readLine();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			birthDate.setTime(dateFormat.parse(str));
			Author author = new Author();
			author.setIdAuthor(id_author);
			author.setName(name);
			author.setSurname(surname);
			author.setBirthDate(birthDate);
			if (authorDao.update(author)) {
				System.out.println("Автор успешно изменен");
				return true;
			} else {
				System.out.println("Произошла ошибка при изменении, попрубуйте снова");
				return false;
			}
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete() {
		showAll();
		System.out.println("Выберите автора для удаления: введите его ID");
		int id_author = read.readNumber();
		Author author = authorDao.get(id_author);
		if (authorDao.delete(author)) {
			System.out.println("Автор успешно удален");
			return true;
		} else {
			System.out.println("Произошла ошибка при удалении, попрубуйте снова");
			return false;
		}
	}

}
