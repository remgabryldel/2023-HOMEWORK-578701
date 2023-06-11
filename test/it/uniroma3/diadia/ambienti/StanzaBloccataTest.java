package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanza;
	private StanzaBloccata[] X= new StanzaBloccata[2];

	@BeforeEach	
	void setUp() {
	    stanzaBloccata = new StanzaBloccata("Stanza1",Direzione.nord,"chiave");
		stanza = new Stanza("Stanza2");
	    stanzaBloccata.impostaStanzaAdiacente(Direzione.nord,stanza);
	    stanzaBloccata.impostaStanzaAdiacente(Direzione.sud,stanza);
	    
	    /*per testare l'equals*/
	    X[0] = new StanzaBloccata("test_eq_s1",Direzione.nord,"");
	    X[1] = new StanzaBloccata("test_eq_s1",Direzione.nord,"");
	    }
	
	@Test
	void testGetDescrizioneStatoBloccataDirezioneBloccata() {
		assertEquals(stanzaBloccata,stanzaBloccata.getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	void testGetDescrizioneStatoBloccataDirezioneLibera() {
		assertEquals(stanzaBloccata.getStanzaAdiacente(Direzione.sud),stanza);
	}
	
	@Test
	void testGetDescrizioneStatoNoBloccata() {
		stanzaBloccata.addAttrezzo(new Attrezzo("chiave",1));
		assertEquals(stanzaBloccata.getStanzaAdiacente(Direzione.nord),stanza);
	}
	@Test
	void testEqualsStanzeBloccate() {
		assertEquals(X[0],X[1]);
		X[1].setNomeAttrezzo("chiave");
		assertNotEquals(X[0],X[1]);
		X[0].setNomeAttrezzo("chiave");
		assertEquals(X[0],X[1]);
		X[1].setNomeDirezione("sud");
		assertNotEquals(X[0],X[1]);
		X[0].setNomeDirezione("sud");
		assertEquals(X[0],X[1]);
		X[0].impostaStanzaAdiacente(Direzione.nord, null);
		assertNotEquals(X[0],X[1]);
		X[1].impostaStanzaAdiacente(Direzione.nord, new Stanza("stanza_x"));
		assertNotEquals(X[0],X[1]);
		X[0].impostaStanzaAdiacente(Direzione.nord, new Stanza("stanza_x"));
		assertEquals(X[0],X[1]);
		X[1].addAttrezzo(new Attrezzo("chiave",1));
		assertNotEquals(X[0],X[1]);
		X[0].addAttrezzo(new Attrezzo("chiave",2));
		assertNotEquals(X[0],X[1]);
		X[0].removeAttrezzo("chiave");
		X[0].addAttrezzo(new Attrezzo("chiave",1));
		assertEquals(X[0],X[1]);
		X[1].setNomeDirezione("est");
		assertNotEquals(X[0],X[1]);
	}

}
