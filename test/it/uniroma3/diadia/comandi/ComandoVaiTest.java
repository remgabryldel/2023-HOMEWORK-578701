package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	private Partita partitaVuota = null;
	private Partita partitaInizializzata = new Partita();
	
	
	@Test
	void testEseguiPartitaNull() {
		ComandoVai comand = new ComandoVai();
		comand.esegui(partitaVuota);
		assertNull(partitaVuota);
	}
	
	@Test
	void testEseguiPartitaDirezioneNull() {
		ComandoVai comand = new ComandoVai();
		Stanza Corrente = partitaInizializzata.getStanzaCorrente();
		comand.esegui(partitaInizializzata);
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Corrente);
	}

	@Test
	void testEseguiPartitaDirezioneEmpty() {
		ComandoVai comand = new ComandoVai();
		Stanza Corrente = partitaInizializzata.getStanzaCorrente();
		comand.setParametro("");
		comand.esegui(partitaInizializzata);
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Corrente);
	}
	
	@Test
	void testEseguiPartitaDirezioneInvalid() {
		ComandoVai comand = new ComandoVai();
		Stanza Corrente = partitaInizializzata.getStanzaCorrente();
		comand.setParametro("boh");
		comand.esegui(partitaInizializzata);
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Corrente);
	}
	
	@Test
	void testEseguiPartitaDirezionevalid() {
		ComandoVai comand = new ComandoVai();
		Stanza Adiacente = partitaInizializzata.getStanzaCorrente().getStanzaAdiacente("nord");
		comand.setParametro("nord");
		comand.esegui(partitaInizializzata);
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Adiacente);
	}
}
