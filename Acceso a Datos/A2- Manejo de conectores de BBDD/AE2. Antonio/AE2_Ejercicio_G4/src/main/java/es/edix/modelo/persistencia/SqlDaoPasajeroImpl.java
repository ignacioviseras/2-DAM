package es.edix.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.edix.modelo.dao.IntPasajeroDao;
import es.edix.modelo.entidad.Coche;
import es.edix.modelo.entidad.Pasajero;

public class SqlDaoPasajeroImpl implements IntPasajeroDao{
	
	
	private Connection conexion;
	
	
	public boolean abrirConexion() {
		String cadenaConexion = "jdbc:mysql://localhost:3306/bbdd";
		String user= "root";
		String pass= "";
		
		try {
			conexion=DriverManager.getConnection(cadenaConexion, user, pass);
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	}
	
	public boolean cerrarConexion() {
		
		
		try {
			conexion.close();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}


	public Boolean deletePassenger(int id) {
		abrirConexion();
		String query = "delete from pasajero where ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1,id);
			
			int afectado= ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al eliminar el pasajero con id: "+ id );
			e.printStackTrace();
			return false;
		}finally {
			cerrarConexion();
		}
		return true;
	}

	public Pasajero findById(int id) {
		abrirConexion();
		String query = "select ID, NOMBRE,EDAD, PESO from pasajero where ID=?";
		Pasajero pa= null;
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa = new Pasajero();
				pa.setId(1);
				pa.setNombre(rs.getString(2));
				pa.setEdad(3);
				pa.setPeso(rs.getDouble(4));
				
			}
			
			
		}catch (SQLException e) {
			System.out.println("Error al buscar el pasajero con id: "+ id );
			e.printStackTrace();
			
		}finally {
			cerrarConexion();
		}
		return pa;
	}

	public ArrayList<Pasajero> passengerList() {
		if(!abrirConexion()) {
			return null;	
		}
		
		List<Pasajero> listapasajero = new ArrayList<Pasajero>();
		String query= "select ID_PASAJERO, NOMBRE, EDAD, PESO, ID_COCHE from pasajero";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				pasajero.setIdCoche(rs.getInt(5));
				
				listapasajero.add(pasajero);
			}
		
			
			
		}catch (SQLException e) {
			System.out.println("Error al listar los pasajeros");
			e.printStackTrace();
			
		}finally {
			cerrarConexion();
		}
		return (ArrayList<Pasajero>) listapasajero;
		
	}

	public Boolean addPassenger(Pasajero pasajero) {
		abrirConexion();
		String query = "insert into pasajero (NOMBRE, EDAD,PESO, ID_COCHE) values (?,?,?,?)";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			ps.setDouble(4, pasajero.getIdCoche());
			
			
			
			int afectados = ps.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al crear el pasajero" );
			return false;
		}finally {
			cerrarConexion();
		}
		return true;
		
		
		
	}

	public Boolean deleteCarPassenger(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Pasajero> passengerCarList() {
		// TODO Auto-generated method stub
		return null;
	}

}
