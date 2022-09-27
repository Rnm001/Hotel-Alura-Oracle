package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.xdevapi.JsonString;



public class newSQLConection {
	    private Connection conn = null;

	    public newSQLConection(){
	        String urlDatabase = "jdbc:mysql://localhost/hotel_alura?TimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String user = "root", pass = "aaaa1234";

	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(urlDatabase, user, pass);

	        } catch (SQLException ex){
	            System.out.println("Excepción: "+ ex.getMessage());
	        } catch (ClassNotFoundException ex){
	            System.out.println("Excepción no encontró driver: "+ ex.getMessage());
	        }
	    }

	    public Connection getConnection(){
	        return this.conn;
	    }

	    public void desconectarBD(){
	        System.out.println("Cerrar conexión a base de datos");
	        if(conn != null){
	            try {
	                conn.close();

	            } catch (SQLException ex) {
	                System.out.println("No se realizó la desconexión: " + ex.getMessage());
	            }
	        }
	    }

	}

