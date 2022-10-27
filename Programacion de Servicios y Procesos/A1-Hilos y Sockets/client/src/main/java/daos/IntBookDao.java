package daos;

import java.util.List;

import beans.Book;

public interface IntBookDao {
	
	List<Book> booksList ();
	Book searchOne(int isbn);
	Book searchTittle ();

}
