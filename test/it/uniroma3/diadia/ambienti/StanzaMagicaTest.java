package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
    private Stanza stanza;
    private Stanza magica;
    private int numeroAttrezzi;
    private final static int  SOGLIA_MAGICA = 3;
    
	@BeforeEach
	public void setUp() {
	    stanza = new Stanza("Normale");
	    magica = new StanzaMagica("Magica",SOGLIA_MAGICA);
	    numeroAttrezzi = 6;
	    for(int i=0;i<numeroAttrezzi;i++) {
	    	String s= String.valueOf(i)+"abc";
		      stanza.addAttrezzo(new Attrezzo(s,i/3));
		      magica.addAttrezzo(new Attrezzo(s,i/3));
	    }
	}

	@Test
	void testAddAttrezzoNoMagic() {
		for(int i=0;i<SOGLIA_MAGICA;i++) {
			String s= String.valueOf(i)+"abc";
		    assertEquals(stanza.getAttrezzo(s).toString(),magica.getAttrezzo(s).toString());
		}
	}
	
	@Test
	void testAddAttrezzoMagic() {
		for(int i=SOGLIA_MAGICA;i<6;i++) {
				StringBuilder s = new StringBuilder(String.valueOf(i)+"abc");
				s = s.reverse();
				assertEquals(s.toString()+" ("+String.valueOf((i/3)*2)+"kg)",magica.getAttrezzo(s.toString()).toString());
		}
	}

}
