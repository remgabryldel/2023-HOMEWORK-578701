package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia stanzaBuia;
	private Stanza stanza;
	
	@BeforeEach	
	void setUp() {
	    stanzaBuia = new StanzaBuia("Stanza1");
		stanza = new Stanza("Stanza1");
	}
	
	@Test
	void testGetDescrizioneStatoBuio() {
		assertNotEquals(stanzaBuia.getDescrizione(),stanza.getDescrizione());
	}
	
	@Test
	void testGetDescrizioneStatoNoBuio() {
		stanzaBuia.addAttrezzo(new Attrezzo("lanterna",1));
		stanza.addAttrezzo(new Attrezzo("lanterna",1));
		assertEquals(stanzaBuia.getDescrizione(),stanza.getDescrizione());
	}

}
