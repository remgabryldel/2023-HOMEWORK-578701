package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
private Stanza[] X= new Stanza[6];

	@BeforeEach
	public void setUp() {
		    X[0] = new Stanza("test_no_agg");
		    X[3] = new Stanza("test_no_agg");
		    
		    X[1] = new Stanza("test_con_SA");
		    X[1].impostaStanzaAdiacente("dir1",new Stanza("stanza_1"));
		    X[4] = new Stanza("test_con_SA");
		    X[4].impostaStanzaAdiacente("dir1",new Stanza("stanza_1"));
		    
		    X[2] = new Stanza("test_SA_piena");
		    X[2].impostaStanzaAdiacente("dir1",new Stanza("stanza_1"));
		    X[2].impostaStanzaAdiacente("dir2",new Stanza("stanza_2"));
		    X[2].impostaStanzaAdiacente("dir3",new Stanza("stanza_3"));
		    X[2].impostaStanzaAdiacente("dir4",new Stanza("stanza_4"));
		    X[5] = new Stanza("test_SA_piena");
		    X[5].impostaStanzaAdiacente("dir1",new Stanza("stanza_1"));
		    X[5].impostaStanzaAdiacente("dir2",new Stanza("stanza_2"));
		    X[5].impostaStanzaAdiacente("dir3",new Stanza("stanza_3"));
		    X[5].impostaStanzaAdiacente("dir4",new Stanza("stanza_4"));
		    for(int i=0;i<10;i++) {
			      String s= String.valueOf(i);
			      X[2].addAttrezzo(new Attrezzo(s,i/3));
			      X[5].addAttrezzo(new Attrezzo(s,i/3));
		    }
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNullStanzaNull() {  
	   X[0].impostaStanzaAdiacente(null, null);
	   assertEquals(0,X[0].getDirezioni().length);
	   assertEquals(0,X[0].getStanzeAdiacenti().length);
	   assertEquals(0,X[0].getNumeroStanzeAdiacenti());
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNullStanzaNoNull() {
	   X[0].impostaStanzaAdiacente(null, new Stanza("stanza_1"));
	   assertEquals(0,X[0].getDirezioni().length);
	   assertEquals(0,X[0].getStanzeAdiacenti().length);
	   assertEquals(0,X[0].getNumeroStanzeAdiacenti());
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNoNullStanzaNull() {
       X[0].impostaStanzaAdiacente("dir1", null);
	   assertEquals("dir1",X[0].getDirezioni()[0]);
	   assertNull(X[0].getStanzeAdiacenti()[0]);
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNoNullStanzaNoNull() {
       X[0].impostaStanzaAdiacente("dir1", new Stanza("stanza_1"));
	   assertEquals("dir1",X[0].getDirezioni()[0]);
	   assertEquals("stanza_1",X[0].getStanzeAdiacenti()[0].getNome());
	}
	
	@Test
	void testImpostaStanzaAdiacenteNuovaInStanzaConStanzeAdiacenticompleto() {
	   X[2].impostaStanzaAdiacente("dirx", new Stanza("stanza_ie"));
	   for(int i=0; i<X[2].getNumeroStanzeAdiacenti(); i++) {
		   assertEquals(X[5].getDirezioni()[i],X[2].getDirezioni()[i]);
		   assertEquals(X[5].getStanzeAdiacenti()[i].getNome(),X[2].getStanzeAdiacenti()[i].getNome());
	   }
	}	
	
	@Test
	void testImpostaStanzaAdiacenteGiaEsistenteInStanzaConStanzeAdiacenticompleto() {
       X[2].impostaStanzaAdiacente("dir3", new Stanza("forse"));
       assertEquals("dir3",X[2].getDirezioni()[2]);
	   assertEquals("forse",X[2].getStanzeAdiacenti()[2].getNome());	   
	}

	@Test
	void testGetStanzaAdiacenteInputStanzaNull() {
		assertNull(X[1].getStanzaAdiacente(null));
	}
	
	@Test
	void testGetStanzaAdiacenteInputStanzaEmpty() {
		assertNull(X[1].getStanzaAdiacente(""));
	}
	
	@Test
	void testGetStanzaAdiacenteInputStanzaEsistente() {
		assertEquals("stanza_1",X[1].getStanzaAdiacente("dir1").getNome());
	}

	@Test
	void testAddAttrezzo() {
		assertFalse(X[0].addAttrezzo(null));
		
		assertTrue(X[0].addAttrezzo(new Attrezzo("abc",67)));
		assertEquals(1,X[0].getNumeroAttrezzi());
		assertEquals(1,X[0].getNumeroAttrezzi());
		assertEquals("abc",X[0].getAttrezzi()[0].getNome());
		assertEquals(67,X[0].getAttrezzi()[0].getPeso());

		assertFalse(X[2].addAttrezzo(new Attrezzo("abc",67)));
	}

	@Test
	void testHasAttrezzo() {
		assertFalse(X[0].hasAttrezzo(null));
		assertFalse(X[0].hasAttrezzo("abc"));
		assertTrue(X[2].hasAttrezzo("8"));
		assertFalse(X[2].hasAttrezzo("10"));
 	}

	@Test
	void testGetAttrezzo() {
		assertNull(X[0].getAttrezzo(null));
		assertNull(X[0].getAttrezzo("abc"));
        assertNotNull(X[2].getAttrezzo("5"));
		assertNull(X[2].getAttrezzo("abc"));
	}

	@Test
	void testRemoveAttrezzo() {
		assertFalse(X[0].removeAttrezzo(null));
		assertFalse(X[0].removeAttrezzo("abc"));
		assertTrue(X[2].removeAttrezzo("8"));
	}
}