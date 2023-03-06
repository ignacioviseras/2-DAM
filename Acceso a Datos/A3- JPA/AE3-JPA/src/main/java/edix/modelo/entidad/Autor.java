package edix.modelo.entidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement (name="autore")
@XmlType(propOrder = {
        "id",
        "nombre",
        "apellidos",
        "fecha_nac",
        "listalibrosAutor"
    })

@Entity
@Table(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellidos;
	
	@Temporal(TemporalType.DATE)
	private Date fecha_nac;
	
	@OneToMany(mappedBy= "autor" , cascade = CascadeType.ALL)
	private List<Libro> listalibrosAutor;
	
	
	public Autor() {
		super();
		listalibrosAutor = new ArrayList<>();
	}
	
	public Autor(String nombre, String apellidos, Date fecha_nac) {
		super();
		listalibrosAutor = new ArrayList<>();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_nac = fecha_nac;
	}
	
	
	public void addLibro(Libro libro) {
        this.listalibrosAutor.add(libro);
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public List<Libro> getListalibrosAutor() {
		return listalibrosAutor;
	}

	public void setListalibrosAutor(List<Libro> listalibrosAutor) {
		this.listalibrosAutor = listalibrosAutor;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nac=" + fecha_nac
				+ "]";
	}
	
	
	
}
