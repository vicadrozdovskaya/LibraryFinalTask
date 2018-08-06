package by.htp.drozdovskaya.library.dao.collections;

import java.util.ArrayList;
import java.util.List;

import by.htp.drozdovskaya.library.dao.IAuthorDao;
import by.htp.drozdovskaya.library.entity.Author;

public class AuthorCollectionImpl implements IAuthorDao {

	@Override
	public Author get(int id) {
		List<Author> authors = LibraryCollectionsStorage.getAuthors();
		Author returnAuthor = new Author();
		for (Author author : authors) {
			if (author.getIdAuthor() == id) {
				returnAuthor.setIdAuthor(author.getIdAuthor());
				returnAuthor.setName(author.getName());
				returnAuthor.setSurname(author.getSurname());
				returnAuthor.setBirthDate(author.getBirthDate());
			}
		}
		return returnAuthor;
	}

	@Override
	public boolean insert(Author author) {
		List<Author> authors = new ArrayList<>();
		authors.addAll(LibraryCollectionsStorage.getAuthors());
		author.setIdAuthor(LibraryCollectionsStorage.getNextAuthorId());
		boolean result = authors.add(author);
		LibraryCollectionsStorage.setAuthors(authors);
		return result;
	}

	@Override
	public boolean update(Author author) {
		List<Author> authors = new ArrayList<>();
		authors.addAll(LibraryCollectionsStorage.getAuthors());
		for (Author authorThis : authors) {
			if(authorThis.getIdAuthor() == author.getIdAuthor()) {
				authorThis.setIdAuthor(author.getIdAuthor());
				authorThis.setName(author.getName());
				authorThis.setSurname(author.getSurname());
				authorThis.setBirthDate(author.getBirthDate());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(Author author) {
		List<Author> authors = new ArrayList<>();
		authors.addAll(LibraryCollectionsStorage.getAuthors());
		boolean result = authors.remove(author);
		LibraryCollectionsStorage.setAuthors(authors);
		return result;
	}

	@Override
	public List<Author> getAll() {
		List<Author> authors = LibraryCollectionsStorage.getAuthors();
		return authors;
	}

}
