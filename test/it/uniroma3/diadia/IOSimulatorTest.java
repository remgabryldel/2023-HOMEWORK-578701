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
		
		/**
		 * Setup Lista input 1
		 */
		
		this.listaInput1.put(1, "prendi osso");
		this.listaInput1.put(2, "guarda");
		this.listaInput1.put(3, "vai nord");

		/**
		 * Setup Lista input 2
		 */
		
		this.listaInput2.put(1, "vai est");
		this.listaInput2.put(2, "prendi piccone");
		this.listaInput2.put(3, "vai ovest");
		this.listaInput2.put(4, "prendi osso");
		this.listaInput2.put(5, "vai nord");
		
		/**
		 * Setup Lista input 3
		 */

		this.listaInput3.put(1, "vai sud");
		this.listaInput3.put(2, "prendi Torcia");
		this.listaInput3.put(3, "vai sud");
		this.listaInput3.put(4, "posa Torcia");
		this.listaInput3.put(5, "prendi Chiave");
		this.listaInput3.put(6, "vai nord");
		this.listaInput3.put(7, "vai nord");
		this.listaInput3.put(8, "vai nord");
		this.listaInput3.put(9, "posa Chiave");
		this.listaInput3.put(10, "vai est");
	}
	
	@Test
	void testIOSimulatorMonolocale() {
		IOSimulator io = new IOSimulator(listaInput1);
		IOSimulator.IOSimulatorMonolocale(listaInput1, io);
		this.listaOutput = io.getListaOutput();
		assertEquals(listaOutput.get(1),"Hai raccolto osso (5kg).");
		assertEquals(listaOutput.get(3),"Atrio\n"
				+ "Uscite:  nord\n"
				+ "Attrezzi nella stanza: ");
		assertEquals(listaOutput.get(4),"CFU rimanenti: 20");
		assertEquals(listaOutput.get(5),"\n"
				+ "Contenuto borsa raggruppato per peso: \n"
				+ "[osso (5kg)]");
		assertEquals(listaOutput.get(7),"Biblioteca");
		assertEquals(listaOutput.get(8),"Hai vinto!");

	}

	@Test
	void testIOSimulatorBilocale() {
		IOSimulator io = new IOSimulator(listaInput2);
		IOSimulator.IOSimulatorBilocale(listaInput2, io);
		this.listaOutput = io.getListaOutput();
		assertEquals(listaOutput.get(1),"Aula N10");
		assertEquals(listaOutput.get(2),"Hai raccolto piccone (4kg).");
		assertEquals(listaOutput.get(3),"Atrio");
		assertEquals(listaOutput.get(4),"Hai raccolto osso (5kg).");
		assertEquals(listaOutput.get(5),"Biblioteca");

	}

	@Test
	void testIOSimulatorCompleto() {
		IOSimulator io = new IOSimulator(listaInput3);
		IOSimulator.IOSimulatorCompleto(listaInput3, io);
		this.listaOutput = io.getListaOutput();
		assertEquals(listaOutput.get(1),"Aula N10");
		assertEquals(listaOutput.get(2),"Hai raccolto Torcia (1kg).");
		assertEquals(listaOutput.get(3),"Scantinato");
		assertEquals(listaOutput.get(4),"Hai lasciato a terra Torcia.");
		assertEquals(listaOutput.get(5),"Hai raccolto Chiave (3kg).");
		assertEquals(listaOutput.get(6),"Aula N10");
		assertEquals(listaOutput.get(7),"Atrio");
		assertEquals(listaOutput.get(8),"Corridoio");
		assertEquals(listaOutput.get(9),"Hai lasciato a terra Chiave.");
		assertEquals(listaOutput.get(10),"Biblioteca");
		assertEquals(listaOutput.get(11),"Hai vinto!");
	}

}
