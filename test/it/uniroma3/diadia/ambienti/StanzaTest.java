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
		    X[1].impostaStanzaAdiacente(Direzione.nord,new Stanza("stanza_1"));
		    X[4] = new Stanza("test_con_SA");
		    X[4].impostaStanzaAdiacente(Direzione.nord,new Stanza("stanza_1"));
		    
		    X[2] = new Stanza("test_SA_piena");
		    X[2].impostaStanzaAdiacente(Direzione.nord,new Stanza("stanza_1"));
		    X[2].impostaStanzaAdiacente(Direzione.sud,new Stanza("stanza_2"));
		    X[2].impostaStanzaAdiacente(Direzione.est,new Stanza("stanza_3"));
		    X[2].impostaStanzaAdiacente(Direzione.ovest,new Stanza("stanza_4"));
		    X[5] = new Stanza("test_SA_piena");
		    X[5].impostaStanzaAdiacente(Direzione.nord,new Stanza("stanza_1"));
		    X[5].impostaStanzaAdiacente(Direzione.sud,new Stanza("stanza_2"));
		    X[5].impostaStanzaAdiacente(Direzione.est,new Stanza("stanza_3"));
		    X[5].impostaStanzaAdiacente(Direzione.ovest,new Stanza("stanza_4"));
		    for(int i=0;i<10;i++) {
			      String s= String.valueOf(i);
			      X[2].addAttrezzo(new Attrezzo(s,i/3));
			      X[5].addAttrezzo(new Attrezzo(s,i/3));
		    }
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNullStanzaNull() {  
	   X[0].impostaStanzaAdiacente(null, null);
	   assertEquals(0,X[0].getDirezioni().size());
	   assertEquals(0,X[0].getMapStanzeAdiacenti().size());
	   assertEquals(0,X[0].getNumeroStanzeAdiacenti());
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNullStanzaNoNull() {
	   X[0].impostaStanzaAdiacente(null, new Stanza("stanza_1"));
	   assertEquals(0,X[0].getDirezioni().size());
	   assertEquals(0,X[0].getNumeroStanzeAdiacenti());
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNoNullStanzaNull() {
       X[0].impostaStanzaAdiacente(Direzione.nord, null);
	   assertTrue(X[0].getDirezioni().contains("nord"));
	   assertNull(X[0].getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	void testImpostaStanzaAdiacenteNomeNoNullStanzaNoNull() {
       X[0].impostaStanzaAdiacente(Direzione.nord, new Stanza("stanza_1"));
       assertTrue(X[0].getDirezioni().contains("nord"));
	   assertEquals("stanza_1",X[0].getStanzaAdiacente(Direzione.nord).getNome());
	}
	
	/*@Test
	void testImpostaStanzaAdiacenteNuovaInStanzaConStanzeAdiacenticompleto() {
	   X[2].impostaStanzaAdiacente(Direzione.est, new Stanza("stanza_ie"));
	   for(int i=0; i<X[2].getNumeroStanzeAdiacenti(); i++) {
		   assertEquals(X[5].getDirezioni().get(i),X[2].getDirezioni().get(i));
		   assertEquals(X[5].getStanzaAdiacente(String.valueOf(i)).getNome(),X[2].getStanzaAdiacente(String.valueOf(i)).getNome());
	   }
	}	*/
	
	@Test
	void testImpostaStanzaAdiacenteGiaEsistenteInStanzaConStanzeAdiacenticompleto() {
       X[2].impostaStanzaAdiacente(Direzione.est, new Stanza("forse"));
       assertTrue(X[2].getDirezioni().contains("est"));
	   assertEquals("forse",X[2].getStanzaAdiacente(Direzione.est).getNome());	   
	}

	@Test
	void testGetStanzaAdiacenteInputStanzaNull() {
		assertEquals(X[1],X[1].getStanzaAdiacente(null));
	}
	
	@Test
	void testGetStanzaAdiacenteInputStanzaEmpty() {
		assertEquals(X[1],X[1].getStanzaAdiacente(Direzione.est));
	}
	
	@Test
	void testGetStanzaAdiacenteInputStanzaEsistente() {
		assertEquals("stanza_1",X[1].getStanzaAdiacente(Direzione.nord).getNome());
	}

	@Test
	void testAddAttrezzo() {
		assertFalse(X[0].addAttrezzo(null));
		assertEquals(0,X[0].getNumeroAttrezzi());
		for(int i=0;i<10;i++) {
			assertTrue(X[0].addAttrezzo(new Attrezzo(String.valueOf(i),1)));
			assertEquals(i+1,X[0].getNumeroAttrezzi());
			assertEquals(String.valueOf(i),X[0].getAttrezzo(String.valueOf(i)).getNome());
			assertEquals(1,X[0].getAttrezzo(String.valueOf(i)).getPeso());
		}
		assertEquals("9",X[0].getAttrezzo("9").getNome());
		assertEquals(1,X[0].getAttrezzo("9").getPeso());
		assertFalse(X[0].addAttrezzo(new Attrezzo("10",1)));

	}

	@Test
	void testHasAttrezzo() {
		assertFalse(X[0].hasAttrezzo(null));
		assertFalse(X[0].hasAttrezzo("abc"));
		
		for(int i=0;i<5;i++) {
		    assertTrue(X[2].hasAttrezzo(String.valueOf(i)));
		    }
		assertFalse(X[2].hasAttrezzo("10"));

 	}

	@Test
	void testGetAttrezzo() {
		assertNull(X[0].getAttrezzo(null));
		assertNull(X[0].getAttrezzo("abc"));
	    assertEquals(10,X[2].getNumeroAttrezzi());
        for(int i=0;i<X[2].getNumeroAttrezzi();i++) {
		    assertNotNull(X[2].getAttrezzo(String.valueOf(i)));
		    }
		assertNull(X[2].getAttrezzo("10"));
	}

	@Test
	void testRemoveAttrezzo() {
		assertFalse(X[0].removeAttrezzo(null));
		assertFalse(X[0].removeAttrezzo("abc"));
		assertTrue(X[2].removeAttrezzo("8"));
	}
	
	@Test
	void testEqualsStanzeSoloNome() {
		assertEquals(X[0],X[3]);
		assertNotEquals(X[0],new Stanza("nun"));
	}
	
	@Test
	void testEqualsStanzeNomeAttrezzi() {
		X[0].addAttrezzo(new Attrezzo("att",1));
		X[3].addAttrezzo(new Attrezzo("att",1));
		assertEquals(X[0],X[3]);
		X[3].addAttrezzo(new Attrezzo("att2",1));
		assertNotEquals(X[0],X[3]);
		X[0].addAttrezzo(new Attrezzo("att2",2));
		assertNotEquals(X[0],X[3]);
	}
	
	@Test
	void testEqualsStanzeNomeAdiacenti() {
		assertEquals(X[1],X[4]);
		X[4].impostaStanzaAdiacente(Direzione.est, null);
		assertNotEquals(X[1],X[4]);
		X[1].impostaStanzaAdiacente(Direzione.est, new Stanza("stanza_x"));
		assertNotEquals(X[1],X[4]);
	}
	
	@Test
	void testEqualsStanzeNomeAttrezziAdiacenti() {
		assertEquals(X[2],X[5]);
		X[5].impostaStanzaAdiacente(Direzione.nord, null);
		assertNotEquals(X[2],X[5]);
		X[2].impostaStanzaAdiacente(Direzione.nord, new Stanza("stanza_x"));
		assertNotEquals(X[2],X[5]);
		X[5].impostaStanzaAdiacente(Direzione.nord, new Stanza("stanza_x"));
		assertEquals(X[2],X[5]);
		X[5].removeAttrezzo("1");
		assertNotEquals(X[2],X[5]);
		X[2].removeAttrezzo("1");
		assertEquals(X[2],X[5]);
        X[5].getAttrezzo("4").setPeso(10);
		assertNotEquals(X[2],X[5]);
	}
}