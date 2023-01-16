package daos;

import java.util.ArrayList;
import java.util.List;

import beans.Coche;

public interface IntCocheDao {
	
	public int newCar (Coche coche);
	public boolean deleteCar (int id);	
	public Coche findById (int id);
	public ArrayList<Coche> listaCoches ();
	
	

}
