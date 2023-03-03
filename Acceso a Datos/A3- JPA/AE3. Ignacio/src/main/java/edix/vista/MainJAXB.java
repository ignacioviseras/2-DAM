package edix.vista;

import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import edix.modelo.entidad.Autor;
import edix.modelo.entidad.Editorial;
import edix.modelo.entidad.Libreria;
import edix.modelo.entidad.Libro;

public class MainJAXB {

	
	private static JAXBContext contexto;
	private static Marshaller m;
	
	public static void main(String[] args) {
		
		Contexto();
		
		try {

            m = contexto.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            Editorial ed1 = new Editorial("SM", "calle el cuco");
    		Editorial ed2 = new Editorial("Anaya", "calle caiman");
    		
    		Libreria libreria1 = new Libreria("todoLibros", "juan", "calle perdida 27");
    		Libreria libreria2 = new Libreria("libreate", "paco", "calle pesadilla, 112");
    		
    		Autor aut1 = new Autor("carlos", "perez", new Date(10,18,23));
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
            

            System.out.println("\n"+"----------LIBRERIAS----------"+ "\n");
            m.marshal(libreria1, System.out);
            m.marshal(libreria2, System.out);

            System.out.println("\n"+ "----------LIBROS----------"+ "\n");
            m.marshal(libro1, System.out);
            m.marshal(libro2, System.out);
            m.marshal(libro3, System.out);
            m.marshal(libro4, System.out);
            m.marshal(libro5, System.out);
            m.marshal(libro6, System.out);
            m.marshal(libro7, System.out);
            m.marshal(libro8, System.out);

            System.out.println("\n"+"----------AUTORES----------"+ "\n");
            m.marshal(aut1, System.out);
            m.marshal(aut2, System.out);
            m.marshal(aut3, System.out);
            System.out.println("\n"+"----------EDITORIALES----------"+ "\n");
            m.marshal(ed1, System.out);
            m.marshal(ed2, System.out);

        } catch (JAXBException e) {
            System.out.println("Error convertiendo el objeto a formato XML");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
	}
	
	
	public static void Contexto() {
		try {
            contexto= JAXBContext.newInstance(Autor.class);

        }catch (JAXBException e) {
            System.out.println("Error creando el contexto");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
	}

}
