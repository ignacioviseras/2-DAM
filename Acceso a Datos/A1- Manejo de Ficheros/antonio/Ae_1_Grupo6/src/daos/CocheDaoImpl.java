package daos;

import java.util.ArrayList;
import java.util.Scanner;

import beans.Coche;

public class CocheDaoImpl implements IntCocheDao{
	
	private ArrayList<Coche> listaCoches;
	private ArrayList<Coche> aux;
	
	
	public CocheDaoImpl() {
		 listaCoches = new ArrayList<Coche>();
		 aux = new ArrayList<Coche>();
	}
	
	public void loadCoches () {
		
	}

	
	
	@Override
	public int newCar(Coche coche) {
	  if(listaCoches.contains(coche)) {
        return 1;
	  }
	  listaCoches.add(coche);
	  return 0;
	}

	@Override
	public boolean deleteCar(int id) {
		Coche aux= new Coche();
		aux.setId(id);
		int pos= listaCoches.indexOf(aux);
		if (pos == -1)
			return false;
		else
			listaCoches.remove(pos);
			return true;
	}

	@Override
	public Coche findById(int id) {
		Coche aux= new Coche();
		aux.setId(id);
		int pos= listaCoches.indexOf(aux);
		if (pos == -1)
			return null;
		else
			return listaCoches.get(pos);
		
		
	}

	@Override
	public ArrayList<Coche> listaCoches() {
		// TODO Auto-generated method stub
		return listaCoches;
	}
	
	

}
