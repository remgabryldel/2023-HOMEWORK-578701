package it.uniroma3.diaida.IOConsole;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IOConsoleTest {

	@Test
	void testMostraMessaggioNullo() {
		IOConsole InOut = new IOConsole(); 
		InOut.mostraMessaggio(null);
	}

	@Test
	void testMostraMessaggioVuoto() {
		IOConsole InOut = new IOConsole(); 
		InOut.mostraMessaggio("");
	}
	
	@Test
	void testMostraMessaggioNormale() {
		IOConsole InOut = new IOConsole(); 
		InOut.mostraMessaggio("abc abc");
	}
	
	@Test
	void testLeggiRigaNulla() {
		assertNotEquals(null,new IOConsole().leggiRiga());
 	}
	
	@Test
	void testLeggiRigaVuota() {
		assertEquals("",new IOConsole().leggiRiga());
 	}
	
	@Test
	void testLeggiRigaNormale() {
		assertEquals("abc abc",new IOConsole().leggiRiga());
 	}

}
