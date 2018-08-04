package by.htp.drozdovskaya.library.logic;

import java.util.Calendar;
import java.util.List;

import by.htp.drozdovskaya.library.dao.IAuthorDao;
import by.htp.drozdovskaya.library.data.factorymethod.FactoryCreator;
import by.htp.drozdovskaya.library.entity.Author;

public class AuthorLogic {

	private IAuthorDao authorDao;

	public AuthorLogic() {
		this.authorDao = FactoryCreator.getFactory().createAuthorDao();		
	}
	
	public List<Author> getListOfAuthors() {
		return authorDao.getAll();
	}
	
	public Author getAuthor(int idAuthor) {
		return authorDao.get(idAuthor);
	}
	
	public void createAuthor(String name, String surname, Calendar birthDate) {
		Author author = new Author();
		author.setName(name);
		author.setSurname(surname);
		author.setBirthDate(birthDate);
		authorDao.insert(author);
	}
	
	public void updateAuthor(int idAuthor, String name, String surname, Calendar birthDate) {
		Author author = new Author();
		author.setIdAuthor(idAuthor);
		author.setName(name);
		author.setSurname(surname);
		author.setBirthDate(birthDate);
		authorDao.update(author);
	}
	
	public void deleteAuthor(int idAuthor) {
		Author author = new Author();
		author.setIdAuthor(idAuthor);
		authorDao.delete(author);
	}
	
}
