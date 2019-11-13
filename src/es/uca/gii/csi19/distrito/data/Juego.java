package es.uca.gii.csi19.distrito.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.Integer;

import es.uca.gii.csi19.distrito.data.Data;

public class Juego {
	private String _sCodigo;
	private int _iNParticipantes, _iId;
	private boolean _bIsDeleted = false;
	
	public int getId() {
		return _iId;
	}
	
	public String getCodigo() {
		return _sCodigo;
	}

	public void setCodigo(String sCodigo) throws Exception {
		if (sCodigo.equals(""))
			throw new Exception("El código es obligatorio");
		_sCodigo = sCodigo;
	}

	public int getNParticipantes() {
		return _iNParticipantes;
	}

	public void setNParticipantes(int iNParticipantes) {
		_iNParticipantes = iNParticipantes;
	}
	
	public boolean getIsDeleted() {
		return _bIsDeleted;
	}

	/**
	 * Constructor de la clase Juego.
	 * @param sCodigo es el código del juego.
	 * @throws Exception cuando hay un error en la conexión con la base de datos.
	 */
	//TODO: Precondition: debe existir un Juego con código sCodigo en la base de datos
	public Juego (int iId) throws Exception{
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT codigo, nParticipantes FROM Juego WHERE id = " + iId);
			
			rs.next();
			_iId = iId;
			_sCodigo = rs.getString("codigo");
			_iNParticipantes = rs.getInt("nParticipantes");
		}
		catch (SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
		
	}

	/**
	 * Procedimiento para mostrar información en tipo String de un objeto Juego.
	 * @return String con la información del juego.
	 */
	public String toString () {
		return super.toString() + ":" + _iId + ":" + _sCodigo + ":" + _iNParticipantes;
	}
	
	/**
	 * Crea una instancia de Juego.
	 * @param sCodigo
	 * @param iNParticipantes
	 * @return una instancia de Juego.
	 * @throws Exception
	 */
	//TODO: Precondition: no debe existir un Juego con código igual a sCodigo ni nParticipantes igual a iNParticipantes.
	public static Juego Create (String sCodigo, int iNParticipantes) throws Exception {
		Connection con = null;
		
		try {
			if (sCodigo.equals("")) 
				throw new Exception("El código es obligatorio");
			
			con = Data.Connection();
			con.createStatement().executeUpdate(String.format("INSERT INTO Juego (codigo, nParticipantes) VALUES (%s, %d)", Data.String2Sql(sCodigo, true, false), iNParticipantes));
			
			return new Juego(Data.LastId(con));
		}
		catch (SQLException ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * @return número de juegos existentes actualmente en la base de datos.
	 * @throws Exception
	 */
	public static int Size() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT COUNT(id) as nJuegos FROM Juego");
			
			rs.next();
			return rs.getInt("nJuegos");
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * Elimina un registro de la base de datos.
	 * @throws Exception
	 */
	public void Delete() throws Exception {
		Connection con = null;
		
		if(_bIsDeleted)
			throw new Exception("El juego " + _sCodigo + " ya ha sido eliminado");
		try {
			con = Data.Connection();
			con.createStatement().executeUpdate("DELETE FROM Juego WHERE id = " + _iId);
			_bIsDeleted = true;
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * Modifica un registro de la base de datos.
	 * @throws Exception
	 */
	public void Update() throws Exception {
		Connection con = null;
		
		if(_bIsDeleted)
			throw new Exception("El juego " + _sCodigo + " está eliminado");
		try {
			con = Data.Connection();
			con.createStatement().executeUpdate(String.format("UPDATE Juego SET codigo = %s,nParticipantes = %d WHERE id = %d", Data.String2Sql(_sCodigo, true, false), _iNParticipantes, _iId));
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
	/**
	 * Selecciona los registros que coinciden con un cierto criterio de búsqueda
	 * @param sCodigo
	 * @param iNParticipantes
	 * @return ArrayList de Juego 
	 * @throws Exception
	 */
	public static ArrayList<Juego> Select(String sCodigo, Integer iNParticipantes) throws Exception {
		ArrayList<Juego> aResultado = new ArrayList<Juego>();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT id, codigo, nParticipantes FROM Juego " + Where(sCodigo, iNParticipantes));
			
			while(rs.next())
				aResultado.add(new Juego(rs.getInt("id")));
			
			return aResultado;
		}
		catch(SQLException ee) { throw ee; }
		finally {
			if (rs != null) rs.close();
			if (con != null) con.close();
		}
	}
	
	/**
	 * Genera la condición o condiciones de búsqueda de la cláusula WHERE
	 * @param sCodigo
	 * @param iNParticipantes
	 * @return String
	 */
	private static String Where(String sCodigo, Integer iNParticipantes) {
		/*
		if(sCodigo != null && iNParticipantes != null)
			return "WHERE codigo LIKE " + Data.String2Sql(sCodigo, true, true) + " && nParticipantes = " +iNParticipantes.intValue();
		if(sCodigo == null && iNParticipantes == null)
			return "";
		if(sCodigo != null)
			return "WHERE codigo LIKE " + Data.String2Sql(sCodigo, true, true);
		return "WHERE nParticipantes = " + iNParticipantes.intValue();
		*/
		String sResultado = "WHERE";
		
		if(sCodigo != null)
			sResultado += " codigo LIKE " + Data.String2Sql(sCodigo, true, true) + " AND ";
		if(iNParticipantes != null)
			sResultado += " nParticipantes = " + iNParticipantes.intValue() + " AND ";
		return sResultado.substring(0, sResultado.length() - 5);
	}
	
}
