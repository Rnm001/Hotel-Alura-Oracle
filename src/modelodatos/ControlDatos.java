package modelodatos;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import conexiones.PruebaConexion;


public class ControlDatos {

	private PruebaConexion conectar;
	private ModeloDatos modelo;
	private Connection con;
	
	public ControlDatos() {
		conectar = new PruebaConexion();
		modelo = new ModeloDatos(null, null, null, null);
	}

	public void insertarHuesped(String Nombre, String Apellido, Long FechaNacimiento, String Nacionalidad, Integer Telefono  ) {
	
		PreparedStatement ps;
		String sql;
		modelo.setNombre(Nombre);
		modelo.setApellido(Apellido);
		modelo.setFechaNacimiento(FechaNacimiento);
		modelo.setNacionalidad(Nacionalidad);
		modelo.setTelefono(Telefono);
		
		try{
		    con = conectar.getConnection();
			sql = "INSERT INTO hotel_alura.huespedes VALUES (null, Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono).values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
		//"INSERT INTO hotel_alura.huespedes VALUES (null, 'Paula', 'Hesse', 19910209, 'Argentina', 1234567890, null)")
			ps.setString(1, modelo.getNombre());
			ps.setString(2, modelo.getApellido());
			ps.setLong(3, modelo.getFechaNacimiento());
			ps.setString(4, modelo.getNacionalidad());
			ps.setInt(5, modelo.getTelefono());
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han insertado los datos");
			
		}catch(SQLException e){
		   JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
		}
		
	}
}
	