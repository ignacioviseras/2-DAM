package es.edix.modelo.dao;




import es.edix.modelo.entidad.Pasajero;

public interface IntPasajeroDao {
	
	public void deletePassenger(int id);
	public Pasajero findById(int id);
	public void passengerList();
	public void addPassenger (Pasajero pasajero);
	public void deleteCarPassenger(int id);
	public void passengerCarList(int id);
	

}