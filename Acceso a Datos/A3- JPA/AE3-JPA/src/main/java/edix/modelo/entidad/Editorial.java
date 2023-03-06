package edix.modelo.entidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement (name="editorial")
@XmlType(propOrder = {
        "id",
        "nombre",
        "direccion",
        "libros"
    	})

@Entity
@Table(name="editoriales")
public class Editorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String direccion;
	
	
	@OneToMany(mappedBy = "editorial", cascade=CascadeType.ALL)
	private List<Libro> libros;
	
	public Editorial() {
		super();
		libros = new ArrayList<>();
	}
	
	public Editorial(String nombre, String direccion) {
		super();
		libros = new ArrayList<>();
		this.nombre = nombre;
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
		return "Editorial [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion +"]";
	}
	
	
}
