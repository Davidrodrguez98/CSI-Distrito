package es.uca.gii.csi19.distrito.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi19.distrito.data.Data;

class DataTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}

	@Test
	void testTableAccess() throws Exception {
		Connection con = null;
		ResultSet rs = null;
		ResultSet rsGames = null;
		int iGames = 0;
		int iColumns = 0;
		
		try {			
			con = Data.Connection();
			rs = con.createStatement().executeQuery("SELECT codigo, nParticipantes, fechaInicio, fechaFin FROM juego;");
			
			rsGames = con.createStatement().executeQuery("SELECT COUNT(codigo) FROM juego;");
			
			if(rsGames.next()) {
				iGames = rsGames.getInt(1);
			}
			
			int i = 0;
			while(rs.next()) {
				System.out.println(rs.getString("codigo") + " " + rs.getInt("nParticipantes")
				+ " " + rs.getDate("fechaInicio") + " " + rs.getDate("fechaFin"));
				i++;
			}
			
			assertEquals(iGames, i);
			
			iColumns = rs.getMetaData().getColumnCount();
			
			assertEquals(4, iColumns);
		}catch(SQLException ee) {
			throw ee;
		}finally {
			if(rsGames != null) rsGames.close();
			if(rs != null) rs.close();
			if(con != null) con.close();
		}
	}
	
	@Test
	void testString2Sql() throws Exception{
		String sResult = null;
		
		try {
			sResult = Data.String2Sql("hola", false, false);
			assertEquals("hola", sResult);
			
			sResult = Data.String2Sql("hola", true, false);
			assertEquals("'hola'", sResult);
			
			sResult = Data.String2Sql("hola", false, true);
			assertEquals("%hola%", sResult);
			
			sResult = Data.String2Sql("hola", true, true);
			assertEquals("'%hola%'", sResult);
			
			sResult = Data.String2Sql("O'Connell", false, false);
			assertEquals("O''Connell", sResult);
			
			sResult = Data.String2Sql("O'Connell", true, false);
			assertEquals("'O''Connell'", sResult);
			
			sResult = Data.String2Sql("'Smith '", false, true);
			assertEquals("%''Smith ''%", sResult);
			
			sResult = Data.String2Sql("'Smith '", true, false);
			assertEquals("'''Smith '''", sResult);
			
			sResult = Data.String2Sql("'Smith '", true, true);
			assertEquals("'%''Smith ''%'", sResult);
		}catch(Exception ee) {
			throw ee;
		}
	}
	
	@Test
	void testBoolean2Sql() throws Exception{
		int iResultBoolean = -1;
		try {
			iResultBoolean = Data.Boolean2Sql(true);
			
			assertEquals(1, iResultBoolean);
			
			iResultBoolean = Data.Boolean2Sql(false);
			
			assertEquals(0, iResultBoolean);
		}catch(Exception ee) {
			throw ee;
		}
	}

}
