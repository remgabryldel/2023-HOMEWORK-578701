package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	private Partita partitaVuota = null;
	private Labirinto labirinto = new LabirintoBuilder()
			 .addStanzaIniziale("Atrio")
			 .addAttrezzo("osso", 5)
			 .addStanza("Aula N10")
			 .addStanzaVincente("Biblioteca")
			 .addAdiacenza("Atrio", "Biblioteca", "nord")
			 .addAdiacenza("Atrio", "Aula N10", "est")
			 .addAdiacenza( "Aula N10", "Atrio", "ovest")
			 .getLabirinto();
	private Partita partitaInizializzata = new Partita(this.labirinto);
	
	
	@Test
	void testEseguiPartitaNull() {
		ComandoVai comand = new ComandoVai();
		comand.esegui(partitaVuota,new IOConsole());
		assertNull(partitaVuota);
	}
	
	@Test
	void testEseguiPartitaDirezioneNull() {
		ComandoVai comand = new ComandoVai();
		Stanza Corrente = partitaInizializzata.getStanzaCorrente();
		comand.esegui(partitaInizializzata,new IOConsole());
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Corrente);
	}

	@Test
	void testEseguiPartitaDirezioneEmpty() {
		ComandoVai comand = new ComandoVai();
		Stanza Corrente = partitaInizializzata.getStanzaCorrente();
		comand.setParametro("");
		comand.esegui(partitaInizializzata,new IOConsole());
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Corrente);
	}
	
	@Test
	void testEseguiPartitaDirezioneInvalid() {
		ComandoVai comand = new ComandoVai();
		Stanza Corrente = partitaInizializzata.getStanzaCorrente();
		comand.setParametro("boh");
		comand.esegui(partitaInizializzata,new IOConsole());
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Corrente);
	}
	
	@Test
	void testEseguiPartitaDirezionevalid() {
		ComandoVai comand = new ComandoVai();
		Stanza Adiacente = partitaInizializzata.getStanzaCorrente().getStanzaAdiacente("est");
		comand.setParametro("est");
		comand.esegui(partitaInizializzata,new IOConsole());
		assertNotNull(partitaInizializzata);
		assertEquals(partitaInizializzata.getStanzaCorrente(),Adiacente);
	}
}
