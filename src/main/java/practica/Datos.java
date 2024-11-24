package practica;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Datos 
{
	public boolean altaArticulo(String descripcion, BigDecimal precio, int cantidad)
	{
		boolean finalizado = true;
		try 
		{
			if(descripcion.contains("*") | descripcion.contains("-"))
			{
				finalizado = false;
			}
			else
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String sourceURL = "jdbc:mysql://localhost/tiendecitaAMI";
				Connection dbcon = DriverManager.getConnection(sourceURL, "root", "Mr9+agar");
				Statement sentencia = dbcon.createStatement();
				String comando = "INSERT INTO articulos (descripcionArticulo, precioArticulo, cantidadArticulo) VALUES ('" + descripcion + "', " + precio + ", " + cantidad + ")";
				sentencia.executeUpdate(comando);
				sentencia.close();
			}
		} 
		catch (ClassNotFoundException cnfe) 
		{
			System.out.println("Hubo un error al cargar el driver:" + cnfe.toString());
		}
		catch (SQLException sqle) 
		{
			System.out.println("Hubo un error en la sentencia SQL:" + sqle.toString());
		}
		return finalizado;
	}

	public String[] rellenarArticulos()
	{
		String elementosCadena = "Elije un artículo...*";
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sourceURL = "jdbc:mysql://localhost/tiendecitaAMI";
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "Mr9+agar");
			Statement sentencia = dbcon.createStatement();
			String comando = "SELECT * FROM articulos";
			ResultSet resultado = sentencia.executeQuery(comando);
			while(resultado.next())
			{
				elementosCadena = elementosCadena + resultado.getString("idArticulo") + "-" + resultado.getString("descripcionArticulo") + "-" + resultado.getString("precioArticulo") + "-" + resultado.getString("cantidadArticulo") + "*";
			}
			sentencia.close();
		} 
		catch (ClassNotFoundException cnfe) 
		{
			System.out.println("Hubo un error al cargar el driver:" + cnfe.toString());
		}
		catch (SQLException sqle) 
		{
			System.out.println("Hubo un error en la sentencia SQL:" + sqle.toString());
		}
		return elementosCadena.split("\\*");
	}
	
	public String consultaArticulos()
	{
		String contenido = "";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sourceURL = "jdbc:mysql://localhost/tiendecitaAMI";
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "Mr9+agar");
			Statement sentencia = dbcon.createStatement();
			String comando = "SELECT * FROM articulos";
			ResultSet resultado = sentencia.executeQuery(comando);
			while(resultado.next())
			{
				contenido = contenido + "ID: " + resultado.getInt("idArticulo") + " | Descripción: " + resultado.getString("descripcionArticulo") + " | Precio: " + resultado.getString("precioArticulo") + " € | Cantidad: " + resultado.getInt("cantidadArticulo") + " uds." + "\n";
			}
		}
		catch (ClassNotFoundException cnfe) 
		{
			System.out.println("Hubo un error al cargar el driver:" + cnfe.toString());
		}
		catch (SQLException sqle) 
		{
			System.out.println("Hubo un error en la sentencia SQL:" + sqle.toString());
		}
		return contenido;
	}
	
	public boolean modificacionArticulo(String id, String descripcion, BigDecimal precio, int cantidad)
	{
		boolean finalizado = true;
		try 
		{
			if(descripcion.contains("*") | descripcion.contains("-"))
			{
				finalizado = false;
			}
			else
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String sourceURL = "jdbc:mysql://localhost/tiendecitaAMI";
				Connection dbcon = DriverManager.getConnection(sourceURL, "root", "Mr9+agar");
				Statement sentencia = dbcon.createStatement();
				String comando = "UPDATE articulos SET descripcionArticulo = '" + descripcion + "', precioArticulo = " + precio + ", cantidadArticulo = " + cantidad + " WHERE idArticulo = " + id;
				sentencia.executeUpdate(comando);
				sentencia.close();
			}
		} 
		catch (ClassNotFoundException cnfe) 
		{
			System.out.println("Hubo un error al cargar el driver:" + cnfe.toString());
		}
		catch (SQLException sqle) 
		{
			System.out.println("Hubo un error en la sentencia SQL:" + sqle.toString());
		}
		return finalizado;
	}
	
	public boolean bajaArticulo(String id)
	{
		boolean errorSQL = false;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sourceURL = "jdbc:mysql://localhost/tiendecitaAMI";
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "Mr9+agar");
			Statement sentencia = dbcon.createStatement();
			String comando = "DELETE FROM articulos WHERE idArticulo = " + id;
			sentencia.executeUpdate(comando);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Hubo un error al cargar el driver:" + cnfe.toString());
		}
		catch(SQLException sqle)
		{
			errorSQL = true;
		}
		return errorSQL;
	}

	public boolean altaTicket(String fecha, BigDecimal total, String articulo)
	{
		boolean finalizado = true;
		int idTicket = 0;
		String[] contenedor = null;
		for(int i = 0; i < 3; i++)
		{
			contenedor = fecha.split("/");
		}
		int dia = Integer.parseInt(contenedor[0]);
		int mes = Integer.parseInt(contenedor[1]);
		int anio = Integer.parseInt(contenedor[2]);
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sourceURL = "jdbc:mysql://localhost/tiendecitaAMI";
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "Mr9+agar");
			Statement sentencia = dbcon.createStatement();
			String comando = "INSERT INTO tickets (fechaTicket, totalTicket) VALUES ('" + anio + "-" + mes + "-" + dia + "', " + total + ")";
			sentencia.executeUpdate(comando);
			ResultSet resultado = sentencia.executeQuery("SELECT MAX(idTicket) AS max_id FROM tickets");
			if(resultado.next())
			{
				idTicket = resultado.getInt("max_id");
				System.out.println(idTicket);
			}
			String comando_2 = "INSERT INTO compras (idArticuloFK, idTicketFK) VALUES (" + articulo.split("-")[0] + ", " + idTicket + ")";
			sentencia.executeUpdate(comando_2);
			resultado.close();
			sentencia.close();
		} 
		catch (ClassNotFoundException cnfe) 
		{
			System.out.println("Hubo un error al cargar el driver:" + cnfe.toString());
		}
		catch (SQLException sqle) 
		{
			System.out.println("Hubo un error en la sentencia SQL:" + sqle.toString());
		}
		return finalizado;
	}
	
	public String consultaTickets()
	{
		String contenido = "";
		String pattern = "dd/MM/YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sourceURL = "jdbc:mysql://localhost/tiendecitaAMI";
			Connection dbcon = DriverManager.getConnection(sourceURL, "root", "Mr9+agar");
			Statement sentencia = dbcon.createStatement();
			String comando = "SELECT * FROM tickets INNER JOIN compras ON tickets.idTicket = compras.idTicketFK INNER JOIN articulos ON compras.idArticuloFK = articulos.idArticulo";
			ResultSet resultado = sentencia.executeQuery(comando);
			while(resultado.next())
			{
				contenido = contenido + "ID: " + resultado.getInt("idTicket") + " | Fecha: " + simpleDateFormat.format(resultado.getDate("fechaTicket")) + " | Total: " + resultado.getString("totalTicket") + " | Articulo: " + resultado.getString("descripcionArticulo") + "\n";
			}
		}
		catch (ClassNotFoundException cnfe) 
		{
			System.out.println("Hubo un error al cargar el driver:" + cnfe.toString());
		}
		catch (SQLException sqle) 
		{
			System.out.println("Hubo un error en la sentencia SQL:" + sqle.toString());
		}
		return contenido;
	}

	public static boolean fechaCorrecta(int dia, int mes, int anio)
	{
		if(dia < 1 | dia > 31 | mes < 1 | mes > 12)
		{
			return false;
		}
		else if(dia > 30 & (mes == 4 | mes == 6 | mes == 9 | mes == 11))
		{
			return false;
		}
		else if(dia > 28 & mes == 2 & anio % 4 != 0 | dia > 29 & mes == 2 & anio % 4 == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}