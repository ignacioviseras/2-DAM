package es.edix.modelo.dao;


import es.edix.modelo.entidad.Coche;

public interface IntCocheDao {
	
	public boolean abrirConexion();
	public boolean cerrarConexion();
	public int newCar(Coche coche);
	public void modifyCar(Coche coche);
	public void deleteCar(int id);
	public Coche findById(int id);
	public void listOb();

}
