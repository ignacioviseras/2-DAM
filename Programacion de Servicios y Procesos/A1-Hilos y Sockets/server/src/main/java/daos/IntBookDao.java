package daos;

import java.util.List;

import beans.Book;

public interface IntBookDao {

	List<Book> booksList();

	Book searchOne(int isbn);

	String findByIsbn(int isbn);

	String findByTitle(String tittle);

	int addBook(Book book);

	List<Book> findByAuthor(String Author);
	
}
