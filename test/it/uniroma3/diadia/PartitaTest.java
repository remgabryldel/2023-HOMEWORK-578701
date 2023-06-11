package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	Labirinto monolocale;
	Partita p;
	Stanza s;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		monolocale  = Labirinto.newBuilder("labirinto2.txt").getLabirinto();
 
				new LabirintoBuilder()
				 .addStanzaIniziale("Atrio")
				 .addAttrezzo("osso", 5)
				 .addStanzaVincente("Biblioteca")
				 .addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				 .getLabirinto();
				p = new Partita(monolocale);
				 s = new Stanza("Stanza");
	}

	@Test
	void testVintaStanzaNonVincente() {
		Partita p = new Partita(monolocale);
		assertFalse(p.vinta());
	}
	@Test
	void testVintaStanzaVincente() {
		Partita p = new Partita(monolocale);
		p.setStanzaCorrente(p.getStanzaVincente());
		assertTrue(p.vinta());
	}

	@Test
	void testIsFinitaOggettoSoloInizializzato() {
		Partita p = new Partita(monolocale);
        assertFalse(p.isFinita());
	}
	
	@Test
	void testIsFinitaSetCfu0() {
		Partita p = new Partita(monolocale);
        p.setCfu(0);
        assertTrue(p.isFinita());
	}
	
	@Test
	void testIsFinitaSetFinita() {
		Partita p = new Partita(monolocale);
        p.setFinita();
        assertTrue(p.isFinita());
	}
	
	@Test
	void testIsFinitaSetStanzaVincenteCorrente() {
		Partita p = new Partita(monolocale);
		p.setStanzaCorrente(p.getStanzaVincente());
        assertTrue(p.isFinita());
	}

}
