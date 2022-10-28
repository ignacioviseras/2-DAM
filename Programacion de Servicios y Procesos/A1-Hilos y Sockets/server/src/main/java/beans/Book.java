package beans;

import java.io.Serializable;

public class Book implements Serializable {
	private int ISBN;
	private String tittle;
	private String autor;
	private int price;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int iSBN, String tittle, String autor, int price) {

		this.ISBN = iSBN;
		this.tittle = tittle;
		this.autor = autor;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Libro [ISBN=" + ISBN + ", titulo=" + tittle + ", autor=" + autor + ", precio=" + price + "]";
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		this.ISBN = iSBN;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
