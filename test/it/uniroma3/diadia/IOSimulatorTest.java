package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	private Map<Integer, String> listaInput1;
	private Map<Integer, String> listaInput2;
	private Map<Integer, String> listaInput3;
	private List<String> listaOutput;

	
	@BeforeEach
	void setUp() {
		this.listaInput1 = new TreeMap<>();
		this.listaInput2 = new TreeMap<>();
		this.listaInput3 = new TreeMap<>();
		this.listaOutput = new ArrayList<>();
		
		/*tutti i setup devono terminare con l'istruzione fine!!ALTRIMENTI SOLLEVANO
		 * UN NULL POINTER EXEPTION IN costruisciComando dato che la stringa passata e null */
		/**
		 * Setup Lista input 1
		 */
		this.listaInput1.put(0, "prendi osso");
		this.listaInput1.put(1, "guarda");
		this.listaInput1.put(2, "vai nord");
		this.listaInput1.put(3, "fine");




		/**
		 * Setup Lista input 2
		 */
		
		this.listaInput2.put(0, "vai est");
		this.listaInput2.put(1, "vai ovest");
		this.listaInput2.put(2, "prendi osso");
		this.listaInput2.put(3, "vai nord");
		this.listaInput1.put(4, "fine");

		
		/**
		 * Setup Lista input 3
		 */

		this.listaInput3.put(0, "vai sud");
		this.listaInput3.put(1, "prendi lanterna");
		this.listaInput3.put(2, "vai nord");
		this.listaInput3.put(3, "vai ovest");
		this.listaInput3.put(4, "vai sud");
		this.listaInput3.put(5, "posa lanterna");
		this.listaInput3.put(6, "prendi chiave");
		this.listaInput3.put(7, "vai nord");
		this.listaInput3.put(8, "vai est");
		this.listaInput3.put(9, "posa chiave");
		this.listaInput3.put(10, "vai nord");
		this.listaInput3.put(11, "fine");

	}

	@Test
	public void testMostraMessaggio() {
		
		IOSimulator io = new IOSimulator(new TreeMap<Integer,String>());
		io.mostraMessaggio("test1");
		io.mostraMessaggio("test2");
		List<String> lis = new ArrayList<>();
		lis.add("test1");
		lis.add("test2");
        assertEquals(io.getListaOutput(),lis);
        assertEquals(io.getListaOutput().get(0),lis.get(0));
        assertEquals(io.getListaOutput().get(1),lis.get(1));
	}
	
	@Test
	public void testLeggiRiga() {
		Map<Integer,String> map = new TreeMap<Integer,String>();
		map.put(0, "test0");
		map.put(1, "test1");
		map.put(2, "test2");
		IOSimulator io = new IOSimulator(map);
		assertTrue(io.hasNextIstruzione());
		assertEquals("test0",io.leggiRiga());
		assertTrue(io.hasNextIstruzione());
		assertEquals("test1",io.leggiRiga());
		assertEquals("test2",io.leggiRiga());
	}
	
	@Test
	public void testNextIstruzione() {
		Map<Integer,String> map = new TreeMap<Integer,String>();
		map.put(0, "test0");
		map.put(1, "test1");
		map.put(2, "test2");
		IOSimulator io = new IOSimulator(map);
		/* stessi test ma con map*/
		assertEquals(io.getMapInput().get(0),io.leggiRiga());
		assertEquals(io.getMapInput().get(2),io.nextIstruzione());
		assertEquals(io.getMapInput().get(1),io.leggiRiga());
		assertFalse(io.hasNextIstruzione());
	}
	
	@Test
	void testIOSimulatorMonolocale() {
		IOSimulator io = new IOSimulator(listaInput1);
		IOSimulator.IOSimulatorMonolocale(listaInput1, io);
		this.listaOutput = io.getListaOutput();
		assertEquals(7,listaOutput.size());
		assertEquals(listaOutput.get(1),"hai preso l'oggetto osso (5kg)");
		assertEquals(listaOutput.get(2),"ti trovi nella stanza:\n"
				+ "Atrio\n"
				+ "Uscite:\n"
				+ "nord ; \n"
				+ "Attrezzi nella stanza:\n");
	    assertEquals(listaOutput.get(3), "\nattualmente sei in gioco, con 20 cfu rimanenti.");
	    assertEquals(listaOutput.get(4), "\nil tuo inventario è composto da:\n"
				+ "Contenuto borsa raggruppato per peso:\n"
				+ "[osso (5kg)]");
		assertEquals(listaOutput.get(5),"ti trovi in Biblioteca");
		assertEquals(listaOutput.get(6),"Hai vinto!");

	}

	@Test
	void testIOSimulatorBilocale() {
		IOSimulator io = new IOSimulator(listaInput2);
		IOSimulator.IOSimulatorBilocale(listaInput2, io);
		this.listaOutput = io.getListaOutput();
		assertEquals(6,listaOutput.size());
		assertEquals(listaOutput.get(1),"ti trovi in Aula N10");
		assertEquals(listaOutput.get(2),"ti trovi in Atrio");
		assertEquals(listaOutput.get(3),"hai preso l'oggetto osso (5kg)");
		assertEquals(listaOutput.get(4),"ti trovi in Biblioteca");
		assertEquals(listaOutput.get(5),"Hai vinto!");

	}

	@Test
	void testIOSimulatorCompleto() {
		IOSimulator io = new IOSimulator(listaInput3);
		IOSimulator.IOSimulatorCompleto(listaInput3, io);
		this.listaOutput = io.getListaOutput();
		assertEquals(listaOutput.get(1),"ti trovi in Aula N10");
		assertEquals(listaOutput.get(2),"hai preso l'oggetto lanterna (3kg)");
		assertEquals(listaOutput.get(3),"ti trovi in Atrio");
		assertEquals(listaOutput.get(4),"ti trovi in Laboratorio Campus");
		assertEquals(listaOutput.get(5),"ti trovi in Ripostiglio");
		assertEquals(listaOutput.get(6),"hai posato l'oggetto lanterna (3kg)");
		assertEquals(listaOutput.get(7),"hai preso l'oggetto chiave (1kg)");
		assertEquals(listaOutput.get(8),"ti trovi in Laboratorio Campus");
		assertEquals(listaOutput.get(9),"ti trovi in Atrio");
		assertEquals(listaOutput.get(10),"hai posato l'oggetto chiave (1kg)");
		assertEquals(listaOutput.get(11),"ti trovi in Biblioteca");
		assertEquals(listaOutput.get(12),"Hai vinto!");
	
	}

}
