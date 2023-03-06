package edix.modelo.entidad;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement (name="libreria")
@XmlType(propOrder = {
        "id",
        "nombre",
        "dueño",
        "direccion",
        "libros"

    })

@Entity
@Table(name = "librerias")
public class Libreria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String dueño;
	private String direccion;
	
	@ManyToMany(mappedBy = "librerias", cascade = CascadeType.PERSIST)
	private List<Libro> libros;
	
	
	public Libreria() {
		super();
	}
	
	
	public Libreria(String nombre, String dueño, String direccion) {
		super();
		this.nombre = nombre;
		this.dueño = dueño;
		this.direccion = direccion;
		
	}
	
	public void addLibro(Libro libro) {
        this.libros.add(libro);
    }

	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDueño() {
		return dueño;
	}


	public void setDueño(String dueño) {
		this.dueño = dueño;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public List<Libro> getLibros() {
		return libros;
	}


	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}


	@Override
	public String toString() {
		return "Libreria [id=" + id + ", nombre=" + nombre + ", dueño=" + dueño + ", direccion=" + direccion
				 + "]";
	}
	
	
	
}
