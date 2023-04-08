package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {

	@Test
	void testVintaStanzaNonVincente() {
		Partita p = new Partita();
		p.setStanzaCorrente(new Stanza("stanza_1"));
		assertFalse(p.vinta());
	}
	@Test
	void testVintaStanzaVincente() {
		Partita p = new Partita();
		p.setStanzaCorrente(p.getStanzaVincente());
		assertTrue(p.vinta());
	}

	@Test
	void testIsFinitaOggettoSoloInizializzato() {
		Partita p = new Partita();
        assertFalse(p.isFinita());
	}
	
	@Test
	void testIsFinitaSetCfu0() {
		Partita p = new Partita();
        p.setCfu(0);
        assertTrue(p.isFinita());
	}
	
	@Test
	void testIsFinitaSetFinita() {
		Partita p = new Partita();
        p.setFinita();
        assertTrue(p.isFinita());
	}
	
	@Test
	void testIsFinitaSetStanzaVincenteCorrente() {
		Partita p = new Partita();
		p.setStanzaCorrente(p.getStanzaVincente());
        assertTrue(p.isFinita());
	}

}
