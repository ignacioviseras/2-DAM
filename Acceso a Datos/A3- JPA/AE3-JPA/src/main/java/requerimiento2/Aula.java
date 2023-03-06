package requerimiento2;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="aulas")
public class Aula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_aula;
	
	@OneToOne
	@JoinColumn(name = "fk_letra_clase", referencedColumnName = "id_alumno")
	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn (name = "Fk_id_profesor", referencedColumnName = "id_profesor")
	private Profesor profesor;
	
}
