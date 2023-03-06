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
