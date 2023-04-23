package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanza;
	
	@BeforeEach	
	void setUp() {
	    stanzaBloccata = new StanzaBloccata("Stanza1");
		stanza = new Stanza("Stanza2");
	    stanzaBloccata.impostaStanzaAdiacente("nord",stanza);
	    stanzaBloccata.impostaStanzaAdiacente("sud",stanza);
	}
	
	@Test
	void testGetDescrizioneStatoBloccataDirezioneBloccata() {
		assertEquals(stanzaBloccata,stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetDescrizioneStatoBloccataDirezioneLibera() {
		assertEquals(stanzaBloccata.getStanzaAdiacente("sud"),stanza);
	}
	
	@Test
	void testGetDescrizioneStatoNoBloccata() {
		stanzaBloccata.addAttrezzo(new Attrezzo("chiave",1));
		assertEquals(stanzaBloccata.getStanzaAdiacente("nord"),stanza);
	}

}
