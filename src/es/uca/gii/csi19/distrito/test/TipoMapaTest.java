package es.uca.gii.csi19.distrito.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.uca.gii.csi19.distrito.data.Data;
import es.uca.gii.csi19.distrito.data.TipoMapa;

class TipoMapaTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Data.LoadDriver();
	}

	/**
	 * Test del método Constructor de la clase TipoMapa.
	 * @throws Exception si la instancia de TipoMapa no corresponde con la esperada.
	 */
	
	@Test
	void testConstructor() throws Exception {
		TipoMapa tipoMapa = null;
		
		try {
			tipoMapa = new TipoMapa(1);
			
			assertEquals("Jungla", tipoMapa.getNombre());
			
		}
		catch(SQLException ee) { throw ee; }	
	}
	
	/**
	 * Test de la función Select de la clase TipoMapa.
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {
		try {
			assertEquals(3, TipoMapa.Select().size());
		}
		catch(Exception ee) { throw ee; }
	}

}
