package edix.modelo.entidad;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement (name="libro")
@XmlType(propOrder = {
        "id",
        "titulo",
        "precio",
        "editorial",
        "autor",
        "librerias"
        
		})

@Entity
@Table(name = "libros")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private double precio;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "fk_id_edito", referencedColumnName = "id")
	private Editorial editorial;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_id_autor", referencedColumnName = "id")   
	private Autor autor;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "libreria_libro",
            joinColumns = @JoinColumn(name = "fk_id_libro", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "fk_id_libreria", referencedColumnName = "id"))
    private List<Libreria> librerias;
    
    public Libro() {
    	super();
    }
    
    public Libro(String titulo, double precio) {
    	super();
    	this.titulo = titulo;
    	this.precio = precio;
    	this.librerias = new ArrayList<>();
    }
    
    
    public void addLibreria(Libreria libreria) {
        this.librerias.add(libreria);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Libreria> getLibrerias() {
		return librerias;
	}

	public void setLibrerias(List<Libreria> librerias) {
		this.librerias = librerias;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", precio=" + precio + "]";
	}
    
    
}
