package daos;

import java.util.ArrayList;
import java.util.List;

import beans.Book;

public class BookDaoImpl implements IntBookDao{
	

 private List<Book>lista;
 
 
	 
	 public BookDaoImpl() {
		 lista= new ArrayList<Book>();
		 cargarDatos();
	 }


	
 
 
 private void cargarDatos() {
	 	Book l1= new Book (01, "El coco 1", "antonio machado1", 11);
		Book l2= new Book (02, "El coco 2", "antonio machado2", 12);
		Book l3= new Book (03, "El coco 3", "antonio machado3", 13);
		Book l4= new Book (04, "El coco 4", "antonio machado4", 14);
		Book l5= new Book (05, "El coco 5", "antonio machado5", 15);
 }
	
	@Override
	public List<Book> booksList() {
		
		return lista;
	}
	@Override
	public Book searchOne(int isbn) {
		Book aux = new Book();
		aux.setISBN(isbn);
		int pos= lista.indexOf(aux);
		if (pos==-1)
		return null;
		else return lista.get(pos);
	}
	@Override
	public Book searchTittle() {
		
		return null;
	}
	
	
	
	
		


}
