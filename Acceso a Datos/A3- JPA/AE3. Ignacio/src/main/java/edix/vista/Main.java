package edix.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	
	public static void main(String[] args) {
		abrirConexion();
		
		
		añadirDatos();
		listaLibros();
		listaAutores();
		listaLibrerias();
		listaLibrosEnLibreria();
		
		
		cerrarConexion();
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
		
		Libreria libreria1 = new Libreria("todoLibros", "juan", "calle perdida 27");
		Libreria libreria2 = new Libreria("libreate", "paco", "calle pesadilla, 112");
		
		Autor aut1 = new Autor("Junji ", "ito", new Date(10,18,23));
		Autor aut2 = new Autor("mario", "garcia ", new Date(99,1,9));
		Autor aut3 = new Autor("mario ", "sanche", new Date(90,3,30));
		
		Libro libro1 = new Libro("los amantes odiados", 20.21);
		Libro libro2 = new Libro("la celestina", 32.00);
		Libro libro3 = new Libro("destino final", 15.24);
		Libro libro4 = new Libro("donnie danko", 12.50);
		Libro libro5 = new Libro("El libro de los amores ridículos", 7.50);
		Libro libro6 = new Libro("el hombre en busqueda de sentido", 11.20);
		Libro libro7 = new Libro("el señor de los anillos", 5.50);
		Libro libro8 = new Libro("heroes", 9.99);

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
		libro7.setAutor(aut3);
		aut3.addLibro(libro7);
		libro7.setEditorial(ed1);
		ed1.addLibro(libro7);
		
		//		libro8
		libro8.setAutor(aut3);
		aut3.addLibro(libro8);
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
						" Autor " + libro.getAutor().getNombre() + 
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
					System.out.println("Libro " + libro.getTitulo());
					
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
					System.out.println("Libro " + libro.getTitulo());
					
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
					System.out.println("Libreria " + libreria.getNombre());
					
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error al sacar la lista");
		}
	}
	
	
}
