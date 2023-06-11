package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {
final private FabbricaDiComandiFisarmonica FDCF = new FabbricaDiComandiFisarmonica(); 
    

	@Test
	void testCostruisciComandoStrNull() {
		Comando cmd = FDCF.costruisciComando(null);
		assertEquals(cmd.getClass(),new ComandoNonValido().getClass());
	}
	
	@Test
	void testCostruisciComandoStrEmpty() {
		Comando cmd = FDCF.costruisciComando("");
		assertEquals(cmd.getClass(),new ComandoNonValido().getClass());
	}
	
	@Test
	void testCostruisciComandoStrNotValid() {
		Comando cmd = FDCF.costruisciComando("as ddf grg");
		assertEquals(cmd.getClass(),new ComandoNonValido().getClass());
	}
	
	@Test
	void testCostruisciComandoStrVaiParamNull() {
		Comando cmd = FDCF.costruisciComando("vai");
		assertEquals(cmd.getClass(),new ComandoVai().getClass());
		assertNull(cmd.getParametro());
	}
	
	@Test
	void testCostruisciComandoStrVaiParamNoNull() {
		Comando cmd = FDCF.costruisciComando("vai bla");
		assertEquals(cmd.getClass(),new ComandoVai().getClass());
		assertNotNull(cmd.getParametro());
	}
	
	@Test
	void testCostruisciComandoStrPrendiParamNull() {
		Comando cmd = FDCF.costruisciComando("prendi");
		assertEquals(cmd.getClass(),new ComandoPrendi().getClass());
		assertNull(cmd.getParametro());
	}
	
	@Test
	void testCostruisciComandoStrPrendiParamNoNull() {
		Comando cmd = FDCF.costruisciComando("prendi bla");
		assertEquals(cmd.getClass(),new ComandoPrendi().getClass());
		assertNotNull(cmd.getParametro());
	}
	
	@Test
	void testCostruisciComandoStrPosaParamNull() {
		Comando cmd = FDCF.costruisciComando("posa");
		assertEquals(cmd.getClass(),new ComandoPosa().getClass());
		assertNull(cmd.getParametro());
	}
	
	@Test
	void testCostruisciComandoStrPosaParamNoNull() {
		Comando cmd = FDCF.costruisciComando("posa bla");
		assertEquals(cmd.getClass(),new ComandoPosa().getClass());
		assertNotNull(cmd.getParametro());
	}
	
	@Test
	void testCostruisciComandoStrAiuto() {
		Comando cmd = FDCF.costruisciComando("aiuto");
		assertEquals(cmd.getClass(),new ComandoAiuto().getClass());
	}
	
	@Test
	void testCostruisciComandoStrFine() {
		Comando cmd = FDCF.costruisciComando("fine");
		assertEquals(cmd.getClass(),new ComandoFine().getClass());
	}
	
	@Test
	void testCostruisciComandoStrGuarda() {
		Comando cmd = FDCF.costruisciComando("guarda");
		assertEquals(cmd.getClass(),new ComandoGuarda().getClass());
	}
}
