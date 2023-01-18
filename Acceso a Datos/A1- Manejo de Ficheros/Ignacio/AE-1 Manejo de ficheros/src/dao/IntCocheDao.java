package dao;

import java.util.ArrayList;

import beans.Coche;

public interface IntCocheDao {

	public int newCar(Coche coche);
	public Boolean deleteCar(int id);
	public Coche findById(int id);
	public void listCar();
	public ArrayList<Coche> listOb();
}
