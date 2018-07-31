package by.htp.drozdovskaya.library.dao;

import java.util.List;

public interface IDao<T> {

	List<T> getAll();
	T get(int id);
	boolean insert(T t);
	boolean update(T t);
	boolean delete(T t);
}
