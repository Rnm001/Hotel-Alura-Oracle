package modelodatos;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import conexiones.PruebaConexion;
import conexiones.newSQLConection;

public class ControlDatosEstadia {

	private final Date FechaEntrada = null;
	private final Date FechaSalida = null;
	private final String Valor = null;
	private final String FormaPago = null;
	private PruebaConexion conectar;
	private ModeloDatosEstadia modeloEstadia;
	public Connection conn;
	
	public ControlDatosEstadia(Connection conn) {
		this.conn = conn;
	}
	
	//public void ControlDatosEstadia() {
		//conectar = new PruebaConexion();
		//modelo = new ModeloDatos(null, null, null, null);
	//}
	

	public void insertarReserva(ModeloDatosEstadia modelo) {
	
		PreparedStatement ps;
		String sql;
		modeloEstadia.setFechaEntrada(FechaEntrada);
		modeloEstadia.setFechaSalida(FechaSalida);
		modeloEstadia.setValor(Valor);
		modeloEstadia.setFormaPago(FormaPago);
		

		
		try{
		    conn = conectar.getConnection();
			sql = "INSERT INTO reservas(FechaEntrada, FechaSalida, Valor, FormaPago) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setDate(1, modelo.getFechaEntrada());
			ps.setDate(2, modelo.getFechaSalida());
			ps.setString(3, modelo.getValor());
			ps.setString(4, modelo.getFormaPago());
			
			
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han insertado los datos");
			
		}catch(SQLException e){
		   JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
		}
		
	}
}
	