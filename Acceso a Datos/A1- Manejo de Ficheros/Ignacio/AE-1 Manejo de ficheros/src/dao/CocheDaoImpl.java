package dao;

import java.util.ArrayList;

import beans.Coche;

public class CocheDaoImpl implements IntCocheDao{

	private ArrayList<Coche> carList;
	
	public CocheDaoImpl() {
		carList = new ArrayList<Coche>();
	}
	
	/**
	 * los coches se cargan del fichero
	 */
	public void loadCar(Object readObject) {
		carList.add((Coche) readObject);
		
	}
	
	/**
	 * Metodo para CREAR un coche nuevo
	 * @return 1 en caso de que EXISTA
	 *  <br> 
	 * 		 0 en caso de que SE CREE
	 */
	@Override
	public int newCar(Coche coche) {
		for (Coche c : carList) {
			if(c.getId() == coche.getId() || c.getMatricula().equals(coche.getMatricula()))
				return 1;
		}
		carList.add(coche);
		return 0;
	}

	/**
	 * Metodo para BORRAR un coche
	 * @param id <li>ID del coche que se borrara
	 * @return True -> Se borro el coche
	 *  <br> 
	 * 		 False -> NO se borro el coche
	 */
	@Override
	public Boolean deleteCar(int id) {
		for (Coche c : carList) {
			if(c.getId() == id) {
				carList.remove(c);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para BUSCAR un coche

	 * @param id <li>ID del coche que se quiere buscar
	 * 
	 * @return <li>Devuelve el objeto en caso de que exista
	 * 			<li> Null en caso de que NO exista
	 */
	@Override
	public Coche findById(int id) {
		for (Coche coche : carList) {
			if(id == coche.getId()) {
				return coche;
			}
		}
		return null;
	}

	/**
	 * Metodo para LISTAR los coches
	 * @return Devuelve la lista de coches
	 */
	@Override
	public void listCar() {
		for (Coche c : carList) {
			System.out.println(c);
		}
		System.out.println("");
	}
	
	@Override
	public ArrayList<Coche> listOb() {
		// TODO Auto-generated method stub
		return carList;
	}
	
}
