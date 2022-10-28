package daos;

import java.util.ArrayList;
import java.util.List;

import beans.Book;

public class BookDaoImpl implements IntBookDao {

	private ArrayList<Book> lista;
	private ArrayList<Book> listaAux;

	public BookDaoImpl() {
		lista = new ArrayList<Book>();
		listaAux = new ArrayList<Book>();
		loadBooks();
	}

	private void loadBooks() {
		lista.add(new Book(1, "El coco 1", "antonio machado", 11));
		lista.add(new Book(2, "El coco 2", "antonio machado", 12));
		lista.add(new Book(3, "El coco 3", "antonio machado", 13));
		lista.add(new Book(4, "El coco 4", "antonio machado", 14));
		lista.add(new Book(5, "El coco 5", "antonio machado", 15));
	}

	@Override
	public List<Book> booksList() {

		return lista;
	}

	@Override
	public Book searchOne(int isbn) {
		Book aux = new Book();
		aux.setISBN(isbn);
		int pos = lista.indexOf(aux);
		if (pos == -1)
			return null;
		else
			return lista.get(pos);
	}

	
	@Override
	public String findByTittle(String tittle) {
		for (Book book : lista) {
			if (book.getTittle().equals(tittle)) {
				return book.toString();
			}
		}
		return null;
	}

	

	@Override
	public List<Book> findByAuthor(String author) {//muestra todos los eventos que esten activos
        listaAux.clear();//lispiamos toda la lista para que asi no se guarden los datos de otras acciones

        for(Book book : lista){//recorremos toda la lista de eventos
            if(book.getAutor().equals(author)) {//buscando si esta activo
                listaAux.add(book);//de ser asi añadimos todo el evento a la lista auxiliar creada al principio
            }
        }
        return listaAux;//cuando termine de buscar en toda la lista se devolvera el aux con los valores solicitados
    }

	@Override
	public String findByIsbn(int isbn) {// busca por isbn y muestra cuando lo encuentre
		for (Book book : lista) {

			System.out.println("[foreach]" + book.toString());
			if (book.getISBN() == isbn) {
				return book.toString();
			}
		}
		return null;
	}
	
	
	@Override
    public int addBook(Book book) {//da de alta en caso de no existir
        if(lista.contains(book)) {
            return 1;
        }
            lista.add(book);
        
        return 0;
    } 
}
