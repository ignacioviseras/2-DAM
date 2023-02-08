package es.edix.modelo.dao;

import java.util.ArrayList;

import es.edix.modelo.entidad.Coche;

public interface IntCocheDao {
	
	public boolean abrirConexion();
	public boolean cerrarConexion();
	public int newCar(Coche coche);
	public boolean modifyCar(Coche coche);
	public Boolean deleteCar(int id);
	public Coche findById(int id);
	public ArrayList<Coche> listOb();

}
