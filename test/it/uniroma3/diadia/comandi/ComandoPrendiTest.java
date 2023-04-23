package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	private Partita partita[] = new Partita[4];
	  private ComandoPrendi comando;
	  
	  @BeforeEach
	  public void setup() {
		  partita[0] = new Partita();
		  partita[0].getStanzaCorrente().removeAttrezzo("osso");
		  partita[1] = new Partita();
		  partita[2] = new Partita();
		  partita[2].getStanzaCorrente().addAttrezzo(new Attrezzo("boh",11));
		  partita[3] = new Partita();
		  for(int i = 0; i<10; i++) {
			  partita[3].getGiocatore().addAttrezzo(new Attrezzo(String.valueOf(i),i/4));
		  }
	  }
	  
		@Test
		void testEseguiStanzaEmpty() {
			comando = new ComandoPrendi();
			comando.esegui(partita[0]);//esiste un assert che verifica l'output?
			assertEquals(0,partita[0].getGiocatore().getNumeroAttrezzi());
			assertEquals(0,partita[0].getStanzaCorrente().getNumeroAttrezzi());
		}

		@Test
		void testEseguiStanzaEmptyParametersNoNull() {
			comando = new ComandoPrendi();
			comando.esegui(partita[0]);//esiste un assert che verifica l'output?
			comando.setParametro("boh");
			assertEquals(0,partita[0].getGiocatore().getNumeroAttrezzi());
			assertEquals(0,partita[0].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiStanzaNoEmptyParametersNull() {
			comando = new ComandoPrendi();
			comando.esegui(partita[1]);//esiste un assert che verifica l'output?
			assertEquals(0,partita[1].getGiocatore().getNumeroAttrezzi());
			assertEquals(1,partita[1].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiStanzaNoEmptyParametersInvalid() {
			comando = new ComandoPrendi();
			comando.setParametro("invalid");
			comando.esegui(partita[1]);//esiste un assert che verifica l'output?
			assertEquals(0,partita[1].getGiocatore().getNumeroAttrezzi());
			assertEquals(1,partita[1].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiBorsaNoEmptyParametersValid() {
			comando = new ComandoPrendi();
			comando.setParametro("osso");
			comando.esegui(partita[1]);//esiste un assert che verifica l'output?
			assertEquals(1,partita[1].getGiocatore().getNumeroAttrezzi());
			assertEquals(0,partita[1].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiBorsaNoEmptyParametersValidButWeightItsToo() {
			comando = new ComandoPrendi();
			comando.setParametro("boh");
			comando.esegui(partita[2]);//esiste un assert che verifica l'output?
			assertEquals(0,partita[2].getGiocatore().getNumeroAttrezzi());
			assertEquals(2,partita[2].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiBorsaNoEmptyParametersValidButBagAll() {
			comando = new ComandoPrendi();
			comando.setParametro("osso");
			comando.esegui(partita[3]);//esiste un assert che verifica l'output?
			assertEquals(10,partita[3].getGiocatore().getNumeroAttrezzi());
			assertEquals(1,partita[3].getStanzaCorrente().getNumeroAttrezzi());
		}
}
