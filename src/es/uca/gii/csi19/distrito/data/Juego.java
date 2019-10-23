package es.uca.gii.csi19.distrito.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uca.gii.csi19.distrito.data.Data;

public class Juego {
	private String _sCodigo;
	private int _iNParticipantes;
	
	public String getsCodigo() {
		return _sCodigo;
	}

	public void setsCodigo(String sCodigo) {
		_sCodigo = sCodigo;
	}

	public int getiNParticipantes() {
		return _iNParticipantes;
	}

	public void setiNParticipantes(int iNParticipantes) {
		_iNParticipantes = iNParticipantes;
	}

	/**
	 * Constructor de la clase Juego.
	 * @param sCodigo es el código del juego.
	 * @throws Exception cuando hay un error en la conexión con la base de datos.
	 */
	//TODO: Precondition: debe existir un Juego con código sCodigo en la base de datos
	public Juego (String sCodigo) throws Exception{
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT codigo, nParticipantes FROM Juego WHERE codigo = " + Data.String2Sql(sCodigo, true, false));
			
			rs.next();
			_sCodigo = rs.getString("codigo");
			_iNParticipantes = rs.getInt("nParticipantes");
		}
		catch (SQLException ee) { throw ee; }
		finally {
			if  (rs != null) rs.close();
			if (con != null) con.close();
		}
		
	}

	/**
	 * Procedimiento para mostrar información en tipo String de un objeto Juego.
	 * @return String con la información del juego.
	 */
	public String toString () {
		return super.toString() + ":" + _sCodigo + ":" + _iNParticipantes;
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
			con = Data.Connection();
			con.createStatement().executeUpdate(String.format("INSERT INTO Juego (codigo, nParticipantes) VALUES (%s, %d)", Data.String2Sql(sCodigo, true, false), iNParticipantes));
			
			return new Juego(sCodigo);
		}
		catch (SQLException ee) { throw ee; }
		finally {
			if (con != null) con.close();
		}
	}
	
}
