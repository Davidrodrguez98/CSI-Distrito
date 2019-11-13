package es.uca.gii.csi19.distrito.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi19.distrito.data.Data;
import es.uca.gii.csi19.distrito.data.Juego;

class JuegoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}

	/**
	 * Test del método Constructor de la clase Juego.
	 * @throws Exception si la instancia de Juego no corresponde con la esperada.
	 */
	
	@Test
	void testConstructor() throws Exception {
		Juego juego = null;
		
		try {
			juego = new Juego(1);
			
			assertEquals("Juego1", juego.getCodigo());
			assertEquals(14, juego.getNParticipantes());
			
		}catch(SQLException ee) {
			throw ee;
		}
		
	}
	
	/**
	 * Test del método Create de la clase Juego.
	 * @throws Exception si el Juego creado no es el esperado.
	 */

	@Test
	public void testCreate() throws Exception {
		Juego juego = null;
		
		try {
			juego = Juego.Create("Juego3", 12);
			
			assertEquals("Juego3", juego.getCodigo());
			assertEquals(12, juego.getNParticipantes());
			
		}catch(SQLException ee) {
			throw ee;
		}
	}
	
	/**
	 * Test de la función Select de la clase Juego.
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		try {
			assertEquals(Juego.Size(), Juego.Select(null, null).size());
			assertEquals(1, Juego.Select("Juego", 20).size());
			assertEquals(Juego.Size(), Juego.Select("Juego", null).size());
			assertEquals(1, Juego.Select(null, 14).size());
		}
		catch(Exception ee) { throw ee; }
	}
	
	/**
	 * Test de la función Update de la clase Juego.
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		try {
			Juego juego = new Juego(2);
			juego.setCodigo("Juego2Editado");
			juego.setNParticipantes(2);
			juego.Update();
			Juego juegoEditado = new Juego(2);
			assertEquals("Juego2Editado", juegoEditado.getCodigo());
			assertEquals(2, juegoEditado.getNParticipantes());
		}
		catch(Exception ee) { throw ee; }
	}

	/**
	 * Test de la función Delete de la clase Juego.
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		try {
			Juego juego = new Juego(14);
			juego.Delete();
			assertEquals(3, Juego.Size());
			assertEquals(true, juego.getIsDeleted());
		}
		catch(Exception ee) { throw ee; }
	}
}
