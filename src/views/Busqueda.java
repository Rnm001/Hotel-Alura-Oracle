package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import conexiones.newSQLConection;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	private TableRowSorter trsfiltro;
	private TableRowSorter trsfiltro1;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public ResultSet getConsultaReservas() {
		// JTable datos = null;
		// DefaultTableModel modelo = (DefaultTableModel) datos.getModel();
		while (modelo.getRowCount() > 0)
			modelo.removeRow(0);
		newSQLConection cc = new newSQLConection();
		Connection conn = cc.getConnection();
		int id;
		String valor;
		Date fechae;
		Date fechas;
		String fpago;
		String fpago1 = "No Pago";

		ResultSet rs = null;
		try {
			Statement stm = (Statement) conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM Hotel_alura.Reservas");
			modelo.addRow(new Object[] { "ID", "Fecha de Entrada", "Fecha de Salida", "Valor", "Forma de Pago" });

			while (rs.next()) {
				id = rs.getInt("id");
				fechae = rs.getDate("FechaEntrada");
				fechas = rs.getDate("FechaSalida");
				valor = rs.getString("Valor");
				fpago = rs.getString("FormaPago");
				System.out.println(fechae);
				System.out.println(fechas);
				System.out.println(valor);
				System.out.println(fpago);

				modelo.addRow(new Object[] { id, fechae, fechas, valor, fpago });

			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return rs;
	}

	public ResultSet getConsultaHuespedes() {
		// DefaultTableModel modelo = (DefaultTableModel) datos.getModel();
		while (modeloH.getRowCount() > 0)
			modeloH.removeRow(0);
		newSQLConection cc = new newSQLConection();
		Connection conn1 = cc.getConnection();
		int id, idreserva;
		String nombre, apellido, nacionalidad, telefono;
		Date fechanac;

		ResultSet rs = null;
		try {
			Statement stm1 = (Statement) conn1.createStatement();
			rs = stm1.executeQuery("SELECT * FROM Hotel_alura.Huespedes");
			modeloH.addRow(new Object[] { "ID", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Teléfono",
					"IdReserva" });

			while (rs.next()) {
				id = rs.getInt("id");
				nombre = rs.getString("Nombre");
				apellido = rs.getString("Apellido");
				fechanac = rs.getDate("FechaNacimiento");
				nacionalidad = rs.getString("Nacionalidad");
				telefono = rs.getString("Telefono");
				idreserva = rs.getInt("IdReserva");

				modeloH.addRow(new Object[] { id, nombre, apellido, fechanac, nacionalidad, telefono, idreserva });

			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return rs;
	}

	public void EliminaConsultaHuespedes() {

		newSQLConection cc = new newSQLConection();
		Connection conn = cc.getConnection();
		int n = 0;
		int row = tbHuespedes.getSelectedRow();// Creo el entero con la seleccion de la fila
		String celda = tbHuespedes.getModel().getValueAt(row, 0).toString();// Convierto a string el valor
		String SQL = "DELETE FROM hotel_alura.Huespedes WHERE id = " + celda;
		int opc = JOptionPane.showConfirmDialog(this, "Deseas eliminar al Huesped?", "Pregunta",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);// Dialogo de confirmacion.
		if (opc == JOptionPane.YES_OPTION) {// Si la opcion es YES(SI) entonces...
			System.out.println(SQL);

			try {
				Statement stm = (Statement) conn.createStatement();
				n = stm.executeUpdate(SQL);
				JOptionPane.showMessageDialog(null, "Huesped eliminado con exito");// Mensaje exitoso
				getConsultaHuespedes();

			} catch (SQLException e) {
				System.out.println(conn);
				JOptionPane.showMessageDialog(null, "Hubo un error, intente nuevamente");// Mensaje si hay error.
			} finally {
				// return n;
			}
		}
	}

	public void EliminaConsultaReserva() {

		newSQLConection cc = new newSQLConection();
		Connection conn = cc.getConnection();
		int n = 0;
		int row = tbReservas.getSelectedRow();// Creo el entero con la seleccion de la fila
		String celda1 = tbReservas.getModel().getValueAt(row, 0).toString();// Convierto a string el valor
		String SQL = "DELETE FROM hotel_alura.Reservas WHERE id = " + celda1;
		int opc = JOptionPane.showConfirmDialog(this, "Deseas eliminar la Reserva?", "Pregunta",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);// Dialogo de confirmacion.
		if (opc == JOptionPane.YES_OPTION) {// Si la opcion es YES(SI) entonces...
			System.out.println(SQL);

			try {
				Statement stm = (Statement) conn.createStatement();
				n = stm.executeUpdate(SQL);
				JOptionPane.showMessageDialog(null, "Reserva eliminada con exito");// Mensaje exitoso
				getConsultaReservas();

			} catch (SQLException e) {
				System.out.println(conn);
				JOptionPane.showMessageDialog(null, "Hubo un error, intente nuevamente");// Mensaje si hay error.
			} finally {
				// return n;
			}
		}
	}

	
	public void ActulizarDatosReservas() {
		int fila = tbReservas.getSelectedRow();
		
		int id = Integer.parseInt((String) this.tbReservas.getValueAt(fila, 0).toString());
		String FechaE =  tbReservas.getValueAt(fila, 1).toString();
		String FechaS =  tbReservas.getValueAt(fila, 2).toString();
		String Valor =  tbReservas.getValueAt(fila, 3).toString();
		String fPago = tbReservas.getValueAt(fila, 4).toString();
		newSQLConection cc = new newSQLConection();
		Connection conn = cc.getConnection();
		int n = 0;
		String SQL = "UPDATE hotel_alura.Reservas SET FechaEntrada='"+FechaE+"', FechaSalida='"+FechaS+"', Valor='"+Valor+"', FormaPago='"+fPago+"'WHERE id='"+id+"'";
		try {
			Statement stm = (Statement) conn.createStatement();
			n = stm.executeUpdate(SQL);
			JOptionPane.showMessageDialog(null, "Registro Actualizado con Exito");// Mensaje ok
		}
		catch (SQLException e) {
			System.out.println(conn);
			JOptionPane.showMessageDialog(null, "Hubo un error, intente nuevamente");// Mensaje si hay error.
	}
	}
	public void ActulizarDatosHuespedes() {
		int fila = tbHuespedes.getSelectedRow();
		
		int id = Integer.parseInt((String) this.tbHuespedes.getValueAt(fila, 0).toString());
		String nombre =  tbHuespedes.getValueAt(fila, 1).toString();
		String apellido =  tbHuespedes.getValueAt(fila, 2).toString();
		String FechN =  tbHuespedes.getValueAt(fila, 3).toString();
		String nacion = tbHuespedes.getValueAt(fila, 4).toString();
		String telefono = tbHuespedes.getValueAt(fila, 5).toString();
		String IDres = tbHuespedes.getValueAt(fila, 6).toString();
		newSQLConection cc = new newSQLConection();
		Connection conn = cc.getConnection();
		int n = 0;
		String SQL = "UPDATE hotel_alura.Huespedes SET Nombre='"+nombre+"', Apellido='"+apellido+"', FechaNacimiento='"+FechN+"', Nacionalidad='"+nacion+"', Telefono='"+telefono+"', IdReserva='"+IDres+"'WHERE id='"+id+"'";
		try {
			Statement stm = (Statement) conn.createStatement();
			n = stm.executeUpdate(SQL);
			JOptionPane.showMessageDialog(null, "Registro Actualizado con Exito");// Mensaje ok
		}
		catch (SQLException e) {
			System.out.println(conn);
			JOptionPane.showMessageDialog(null, "Hubo un error, intente nuevamente");// Mensaje si hay error.
	}
	}
	
	public void buscarID() {
		trsfiltro.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 0));

		System.out.println(trsfiltro);
	}
	public void buscarNOM() {
		trsfiltro1.setRowFilter(RowFilter.regexFilter(txtBuscar.getText(), 1));

		System.out.println(trsfiltro);
	}
	
	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		
		txtBuscar.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(final KeyEvent e) {
				trsfiltro = new TableRowSorter(tbReservas.getModel());
				tbReservas.setRowSorter(trsfiltro);
				trsfiltro1 = new TableRowSorter(tbHuespedes.getModel());
				tbHuespedes.setRowSorter(trsfiltro1);
				
				String cadena = (txtBuscar.getText()).toUpperCase();
				txtBuscar.setText(cadena);
				repaint();
				buscarID();
				buscarNOM();
				System.out.println(cadena);
			}
		});

		
		
		//@Override
		//	}
			//public void keyTyped(KeyEvent e) {
			//}
		//});
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setColumnSelectionAllowed(true);
		tbReservas.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas,
				null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		
		getConsultaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setColumnSelectionAllowed(true);
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes,
				null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		getConsultaHuespedes();

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JButton btnNewButton = new JButton("ELIMINAR HUESPED");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminaConsultaHuespedes();

				// JTable id=tbHuespedes;
				// int row =id.getSelectedRow();
				// DefaultTableModel modelo = (DefaultTableModel) id.getModel();
				// String clave = modelo.getValueAt(row, 1).toString();
				// int r=EliminaConsultaHuespedes();

				// if(r!=0) {
				// JOptionPane.showMessageDialog(null, "el registro se elimino");
				// dispose();
				// }else
				// JOptionPane.showMessageDialog(null, "el registro no se elimino");
			}
		});
		btnNewButton.setBounds(247, 508, 226, 35);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("ELIMINAR RESERVA");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnNewButton_1.setForeground(SystemColor.text);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EliminaConsultaReserva();
				// int row = tbReservas.getSelectedRow();//Creo el entero con la seleccion de la
				// fila
				// String celda = tbReservas.getModel().getValueAt(row,
				// 0).toString();//Convierto a string el valor
				// String sql = "DELETE FROM Hotel_alura.Reservas WHERE id = " + celda;//Creo un
				// String SQL que lo utilizare para borrar en la db.
				// int opc = JOptionPane.showConfirmDialog(this, "Deseas eliminar el cliente?",
				// "Pregunta", JOptionPane.YES_NO_OPTION,
				// JOptionPane.QUESTION_MESSAGE);//Dialogo de confirmacion.
				// if (opc == JOptionPane.YES_OPTION) {//Si la opcion es YES(SI) entonces...
				// System.out.println(sql);

				// newSQLConection cc = new newSQLConection();
				// Connection conn = cc.getConnection();
				// int n=0;
				// System.out.println(SQL);
				// try {

				// Connection con =
				// DriverManager.getConnection("jdbc:mysql://localhost/hotel_alura?TimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				// "root", "aaaa1234");//Conexion MySQL
				// Statement stmt = con.createStatement();//Creo la sentencia SQL
				// stmt.executeUpdate(sql);//Ejecuto
				// Statement stm = (Statement) conn.createStatement();
				// n = stm.executeUpdate(sql);

				// System.out.println(stmt);
				// JOptionPane.showMessageDialog(null, "Cliente eliminado con exito");//Mensaje
				// exitoso
				// } catch (SQLException ex) {
				// JOptionPane.showMessageDialog(null,"Hubo un error, intente
				// nuevamente");//Mensaje si hay error.
				// }
			}

		});
		btnNewButton_1.setBounds(20, 508, 217, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EDITAR RESERVA");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setForeground(SystemColor.text);
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnNewButton_2.setRolloverEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActulizarDatosReservas();

			}
		});
		btnNewButton_2.setBounds(483, 508, 193, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("EDITAR HUESPED");
		btnNewButton_3.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnNewButton_3.setForeground(SystemColor.text);
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActulizarDatosHuespedes();
			}
		});
		btnNewButton_3.setBounds(686, 508, 193, 35);
		contentPane.add(btnNewButton_3);
		setResizable(false);

	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
