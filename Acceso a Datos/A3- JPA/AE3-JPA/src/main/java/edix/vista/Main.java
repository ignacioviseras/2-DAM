package edix.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import edix.modelo.entidad.Autor;
import edix.modelo.entidad.Editorial;
import edix.modelo.entidad.Libreria;
import edix.modelo.entidad.Libro;


public class Main {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction et;
	public static final Scanner scanner = new Scanner(System.in);
	
	public static String nextLine() {
		return scanner.nextLine();
		
	}
	
	public static void main(String[] args) {
		abrirConexion();
		
		Boolean continua = true;
		añadirDatos();
		System.out.println("======== Menu ========");
		while(continua == true) {
			System.out.println("1. Lista libros con editorial y autor\n"+
								"2. Lista de autores con sus libros\n"+
								"3. Lista de librerias con sus libros\n"+
								"4. Lista de libros y la libreria a la que pertenecen\n"+
								"5. Salir\n");
			String opcion = nextLine();
			if(opcion.equals("1")) {
				
				listaLibros();
			}else if(opcion.equals("2")) {
				
				listaAutores();
			}else if(opcion.equals("3")) {
				
				listaLibrerias();
			}else if(opcion.equals("4")) {
				
				listaLibrosEnLibreria();
			}else if(opcion.equals("5")) {
				System.out.println("Saliendo del programa");
				cerrarConexion();
				continua = false;
			}
			
		}
		
		
		
	}
	
	
	public static boolean abrirConexion() {
		//comprobaremos si se puede establecer la comunicacion cn la bd
		try {
			emf = Persistence.createEntityManagerFactory("bbdd2");
			em = emf.createEntityManager();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	
	public static boolean cerrarConexion() {
		try {
			em.close();
			emf.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static void añadirDatos() {
		
		Editorial ed1 = new Editorial("SM", "calle el cuco");
		Editorial ed2 = new Editorial("Anaya ", "calle caiman");
		
		Libreria libreria1 = new Libreria("Casa del Libro", "Nicolás María Urgoiti", "C/ Gran Vía, 29, 28013");
		Libreria libreria2 = new Libreria("La Central", "Antonio Ramírez", "Rda. de Atocha, 2, 28012");
		
		Autor aut1 = new Autor("Junji", "ito", new Date(31,7,63));//1 4 7
		Autor aut2 = new Autor("Michel", "Houellebecq", new Date(26,2,56));//2 5 8
		Autor aut3 = new Autor("Stephen", "King", new Date(21,9,47));//3 6
		
		Libro libro1 = new Libro("Uzumaki", 23.75);
		Libro libro2 = new Libro("Serotonina", 9.49);
		Libro libro3 = new Libro("El resplandor", 6.64);
		Libro libro4 = new Libro("Tomie", 23.75);
		Libro libro5 = new Libro("Sumisión", 12.34);
		Libro libro6 = new Libro("Cuento de hadas", 14.34);
		Libro libro7 = new Libro("Gyo", 21.75);
		Libro libro8 = new Libro("Aniquilación", 9.49);

		//añadiendo los datos de los libros
		
		//		libro1
		libro1.setAutor(aut1);
		aut1.addLibro(libro1);
		libro1.setEditorial(ed1);
		ed1.addLibro(libro1);
		
		//		libro2
		libro2.setAutor(aut2);
		aut2.addLibro(libro2);
		libro2.setEditorial(ed2);
		ed2.addLibro(libro2);
		
		//		libro3
		libro3.setAutor(aut3);
		aut3.addLibro(libro3);
		libro3.setEditorial(ed1);
		ed1.addLibro(libro3);
		
		//		libro4
		libro4.setAutor(aut1);
		aut1.addLibro(libro4);
		libro4.setEditorial(ed1);
		ed1.addLibro(libro4);
		
		//		libro5
		libro5.setAutor(aut2);
		aut2.addLibro(libro5);
		libro5.setEditorial(ed2);
		ed2.addLibro(libro5);
		
		//		libro6
		libro6.setAutor(aut3);
		aut3.addLibro(libro6);
		libro6.setEditorial(ed1);
		ed1.addLibro(libro6);
		
		//		libro7
		libro7.setAutor(aut1);
		aut1.addLibro(libro7);
		libro7.setEditorial(ed1);
		ed1.addLibro(libro7);
		
		//		libro8
		libro8.setAutor(aut2);
		aut2.addLibro(libro8);
		libro8.setEditorial(ed1);
		ed1.addLibro(libro8);
		
		//
		
		List<Libreria> lstLibreria1 = new ArrayList<>();
		List<Libreria> lstLibreria2 = new ArrayList<>();
		List<Libro> lstlibros1 = new ArrayList<>();
		List<Libro> lstlibros2 = new ArrayList<>();
		
		lstLibreria1.add(libreria1);
		lstlibros1.add(libro1);
		lstlibros1.add(libro3);
		lstlibros1.add(libro5);
		lstlibros1.add(libro7);
		
		lstLibreria2.add(libreria2);
		lstlibros2.add(libro2);
		lstlibros2.add(libro4);
		lstlibros2.add(libro6);
		lstlibros2.add(libro8);
		
		libro1.setLibrerias(lstLibreria1);
		libro3.setLibrerias(lstLibreria1);
		libro5.setLibrerias(lstLibreria1);
		libro7.setLibrerias(lstLibreria1);
		libreria1.setLibros(lstlibros1);
		
		
		libro2.setLibrerias(lstLibreria2);
		libro4.setLibrerias(lstLibreria2);
		libro6.setLibrerias(lstLibreria2);
		libro8.setLibrerias(lstLibreria2);
		libreria2.setLibros(lstlibros2);

		//comprobamos que se pueda persistir los datos a la bd usando el metodo del dao
		if(add(aut1) || add(aut2) || add(aut3)) {
			System.out.println("Se añadieron los datos a la bd");
		}else{
			System.out.println("Error al añadir los datos a la bd");
		}
		
		
	}
	
	
	public static boolean add(Autor autor) {
		try {
			et = em.getTransaction();
			et.begin();
			em.persist(autor);
			et.commit();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//lista libros con editorial y autor
	@SuppressWarnings("unchecked")
	public static void listaLibros(){
		try {
			List<Libro> lstLibro = em.createQuery("from Libro libros").getResultList();
			System.out.println("\n-------------Lista de Libros-------------");
			for(Libro libro : lstLibro) {
				System.out.println("Titulo " + libro.getTitulo() + 
						"\n\tAutor " + libro.getAutor().getNombre() + 
						" Editorial " + libro.getEditorial().getNombre());
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error al sacar la lista");
		}
	}

	//lista de autores con sus libros
	@SuppressWarnings("unchecked")
	public static void listaAutores() {
		try {
			List<Autor> lstAutor = em.createQuery("from Autor autores").getResultList();
			System.out.println("\n-------------Lista de Autores-------------");
			for(Autor autor : lstAutor) {
				System.out.println("Nombre " + autor.getNombre());
				for(Libro libro: autor.getListalibrosAutor()) {
					System.out.println("\tLibro " + libro.getTitulo());
					
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error al sacar la lista");
		}
	}
	
	
	//lista de librerias con sus libros
	@SuppressWarnings("unchecked")
	public static void listaLibrerias() {
		try {
			List<Libreria> lstLibreria = em.createQuery("from Libreria librerias").getResultList();
			System.out.println("\n-------------Lista de Librerias-------------");
			for(Libreria libreria: lstLibreria) {
				System.out.println("Nombre " + libreria.getNombre());
				for(Libro libro: libreria.getLibros()) {
					System.out.println("\tLibro " + libro.getTitulo());
					
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error al sacar la lista");
		}
	}
	
	
	//lista de libros y la libreria a la que pertenecen
	@SuppressWarnings("unchecked")
	public static void listaLibrosEnLibreria() {
		try {
			List<Libro> lstLibro = em.createQuery("from Libro libros").getResultList();
			System.out.println("\n-------------Lista de Libros en Librerias-------------");
			for(Libro libro: lstLibro) {
				System.out.println("Nombre " + libro.getTitulo());
				for(Libreria libreria: libro.getLibrerias()) {
					System.out.println("\tLibreria " + libreria.getNombre());
					
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error al sacar la lista");
		}
	}
	
	
}
