package modelodatos;

import java.sql.Date;

public class ModeloDatos {

	private Integer id;
	private String Nombre;
	private String Apellido;
	private Long FechaNacimiento;
	private String Nacionalidad;
	private Integer Telefono;
	public Date FechaEntrada;
	public Date FechaSalida;
	public String Valor;
	public String FormaPago;
	
	public ModeloDatos(Date FechaEntrada, Date FechaSalida, String Valor, String FormaPago) {
		super();
		this.FechaEntrada = FechaEntrada;
		this.FechaSalida = FechaSalida;
		this.Valor = Valor;
		this.FormaPago = FormaPago;
		
	}
	
	
	public Date getFechaEntrada() {
		return FechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		FechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return FechaSalida;
	}
	public void setFechaSalida(Date i) {
		FechaSalida = i;
	}
	public String getValor() {
		return Valor;
	}
	public void setValor(String valor) {
		Valor = valor;
	}
	public String getFormaPago() {
		return FormaPago;
	}
	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public Long getFechaNacimiento() {
		return FechaNacimiento;
	}
	public void setFechaNacimiento(Long fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return Nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}
	public Integer getTelefono() {
		return Telefono;
	}
	public void setTelefono(Integer telefono) {
		Telefono = telefono;
	}
	public void setFechaEntrada(int i) {
		// TODO Auto-generated method stub
		
	}
	
	
}

