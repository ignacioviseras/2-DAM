package edix.modelo.Entities;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="profesores")
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_profesor;
	private String nombre;
	private String apellido;
	
	@OneToMany (mappedBy = "aula", cascade = CascadeType.PERSIST)
	private Aula aula;
	
	
	
	@ManyToMany (mappedBy = "listaProfesores", cascade = CascadeType.PERSIST)
	private List<Academia> listaAcademias;
	

}
