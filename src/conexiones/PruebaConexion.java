package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.JsonString;

public class PruebaConexion {



	public static void main(String[] args ) throws SQLException {
	
		
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/hotel_alura?TimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", 
				"root", 
				"aaaa1234");
		
		Statement statement = con.createStatement();
		
		//boolean result = statement.execute("INSERT INTO hotel_alura.huespedes VALUES (null, 'Paula', 'Hesse', 19910209, 'Argentina', 1234567890, null)");
		boolean result = statement.execute("SELECT id, Nombre, Apellido, FechaNacimiento, Nacionalidad Telefono FROM hotel_alura.huespedes ");
		boolean result2 = statement.execute("SELECT id, FechaEntrada, FechaSalida, Valor, FormaPago FROM hotel_alura.reservas ");
	
		
		
		
		
		System.out.println(result);
		System.out.println(result2);
		System.out.println("Cerrando la conexion");
		
		con.close();

		
		
		}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
		

		
		}
	}
	

