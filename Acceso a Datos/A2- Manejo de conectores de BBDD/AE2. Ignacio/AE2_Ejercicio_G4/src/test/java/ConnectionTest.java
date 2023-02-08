import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	private static Connection conexion;
	
	public static void main(String[] args) {
		
	
			if( abrirConexion()== true) {
				
				System.out.println("Conexion realizada a bbdd hecha correctamente");
				
			}else {
				System.out.println("Conexion fallida");
				
			
			}
		
	}
	
	
	public static boolean abrirConexion() {
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

}
