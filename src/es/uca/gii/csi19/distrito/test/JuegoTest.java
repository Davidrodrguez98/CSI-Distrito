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
		Juego game = null;
		
		try {
			game = new Juego("Juego1");
			
			assertEquals("Juego1", game.getsCodigo());
			assertEquals(14, game.getiNParticipantes());
			
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
		Juego game = null;
		
		try {
			game = Juego.Create("Juego3", 20);
			
			assertEquals("Juego3", game.getsCodigo());
			assertEquals(20, game.getiNParticipantes());
			
		}catch(SQLException ee) {
			throw ee;
		}
	}

}
