package es.edix.modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.edix.modelo.dao.IntCocheDao;
import es.edix.modelo.entidad.Coche;


public class SqlDaoImpl implements IntCocheDao{
	
	//Primero realizaremos los metodos de abrir y cerrar la base de datos:
	
	
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
	
		
		
		public int newCar(Coche coche) {
			
			abrirConexion();
			String query = "insert into coche (MATRICULA, MARCA,MODELO,COLOR) values (?,?,?,?)";
			
			try {
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setString(1, coche.getMatricula());
				ps.setString(2, coche.getMarca());
				ps.setString(3, coche.getModelo());
				ps.setString(4, coche.getColor());
				
				ps.executeUpdate();
				
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al crear el coche" );
				return 0;
			}finally {
				cerrarConexion();
			}
			return 1;
			
			
			
		}

		public void deleteCar(int id) {
			abrirConexion();
			String query = "delete from coche where ID = ?";
			try {
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setInt(1,id);
				
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al eliminar el coche con id: "+ id );
				e.printStackTrace();
			}finally {
				cerrarConexion();
			}
			System.out.println("Coche borrado exitosamente \n");
		}

		public Coche  findById(int id) {
			abrirConexion();
			String query = "select ID, MATRICULA, MARCA, MODELO, COLOR from coche where ID=?";
			Coche coche= null;
			try {
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMatricula(rs.getString(2));
					coche.setMarca(rs.getString(3));
					coche.setModelo(rs.getString(4));
					coche.setColor(rs.getString(5));
				}
				
				
				
				
			}catch (SQLException e) {
				System.out.println("Error al buscar el coche con id: "+ id );
				e.printStackTrace();
				
			}finally {
				cerrarConexion();
			}
			return coche;
			
			
		}

		

		public void listOb() {
			if(!abrirConexion()) {
				System.out.println("Error al conectar cn la bd");	
			}
			
			String query= "select ID, MATRICULA, MARCA, MODELO, COLOR from coche";
			try {
				PreparedStatement ps = conexion.prepareStatement(query);
				
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					
					Coche coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMatricula(rs.getString(2));
					coche.setMarca(rs.getString(3));
					coche.setModelo(rs.getString(4));
					coche.setColor(rs.getString(5));
					
					System.out.println(coche);
				}	
				System.out.println();
				
			}catch (SQLException e) {
				System.out.println("Error al listar los coches");
				e.printStackTrace();
				
			}finally {
				cerrarConexion();
			}
			
		}

		public void modifyCar(Coche coche) {
			abrirConexion();
			String query = "update coche set MATRICULA=?, MARCA=?,MODELO=?,COLOR=? where ID=?";
			try {
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setString(1, coche.getMatricula());
				ps.setString(2, coche.getMarca());
				ps.setString(3, coche.getModelo());
				ps.setString(4, coche.getColor());
				ps.setObject(5, coche.getId());
				
				int afectados = ps.executeUpdate();
				if(afectados==0) {
					System.out.println("No se modifico el coche \n");
				}else {
					System.out.println("Coche modificado \n");
				}
				
			}catch (SQLException e) {
				System.out.println("Error al modificar el coche con id: "+ coche.getId() );
				e.printStackTrace();
			}finally {
				cerrarConexion();
			}
			
		}
	

	
}
