package edix.modelo.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colegios")
public class Academia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_academia;
	private String nombre_academia, direccion;
	
	@ManyToMany (mappedBy = "listaAcademias", cascade = CascadeType.PERSIST)
	private List<Profesor> listaProfesores;
	
	
	

}
