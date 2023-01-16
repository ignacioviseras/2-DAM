package daos;

import java.util.List;

import beans.Coche;

public interface IntCocheDao {
	
	public void newCar ();
	public boolean deleteCar (int id);	
	public Coche findById (int id);
	public List<Coche> listaCoches ();
	
	

}
