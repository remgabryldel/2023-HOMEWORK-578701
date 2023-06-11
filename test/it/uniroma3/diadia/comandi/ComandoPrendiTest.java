package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	private Partita partita[] = new Partita[4];
	  private ComandoPrendi comando;
	  
	  @BeforeEach
	  public void setup() {
		  Labirinto monolocale_vuoto = new LabirintoBuilder()
					 .addStanzaIniziale("Atrio")
					 .addStanzaVincente("Biblioteca")
					 .addAdiacenza("Atrio", "Biblioteca", "nord")
					 .getLabirinto();
		  Labirinto monolocale_boh = new LabirintoBuilder()
					 .addStanzaIniziale("Atrio")
					 .addAttrezzo("boh", 11)
					 .addStanzaVincente("Biblioteca")
					 .addAdiacenza("Atrio", "Biblioteca", "nord")
					 .getLabirinto();
		  Labirinto monolocale_pieno = new LabirintoBuilder()
					 .addStanzaIniziale("Atrio")
					 .addAttrezzo("o", 5)
					 .addAttrezzo("os", 5)
					 .addAttrezzo("oss", 5)
					 .addAttrezzo("osso", 5)
					 .addAttrezzo("p", 5)
					 .addAttrezzo("pa", 5)
					 .addAttrezzo("pal", 5)
					 .addAttrezzo("pala", 5)
					 .addAttrezzo("k", 5)
					 .addAttrezzo("ka", 5)
					 .addStanzaVincente("Biblioteca")
					 .addAdiacenza("Atrio", "Biblioteca", "nord")
					 .getLabirinto();
		  
		  partita[0] = new Partita(monolocale_vuoto);
		  partita[1] = new Partita(monolocale_pieno);
		  partita[2] = new Partita(monolocale_boh);
		  partita[3] = new Partita(monolocale_pieno);
		  for(int i = 0; i<10; i++) {
			  partita[3].getGiocatore().addAttrezzo(new Attrezzo(String.valueOf(i),i/4));
		  }
	  }
	  
		@Test
		void testEseguiStanzaEmpty() {
			comando = new ComandoPrendi();
			comando.esegui(partita[0],new IOConsole());//esiste un assert che verifica l'output?
			assertEquals(0,partita[0].getGiocatore().getNumeroAttrezzi());
			assertEquals(0,partita[0].getStanzaCorrente().getNumeroAttrezzi());
		}

		@Test
		void testEseguiStanzaEmptyParametersNoNull() {
			comando = new ComandoPrendi();
			comando.esegui(partita[0],new IOConsole());//esiste un assert che verifica l'output?
			comando.setParametro("boh");
			assertEquals(0,partita[0].getGiocatore().getNumeroAttrezzi());
			partita[0].getStanzaCorrente().toString();
			assertEquals(0,partita[0].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiStanzaNoEmptyParametersNull() {
			comando = new ComandoPrendi();
			comando.esegui(partita[1],new IOConsole());//esiste un assert che verifica l'output?
			assertEquals(0,partita[1].getGiocatore().getNumeroAttrezzi());
			assertEquals(10,partita[1].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiStanzaNoEmptyParametersInvalid() {
			comando = new ComandoPrendi();
			comando.setParametro("invalid");
			comando.esegui(partita[1],new IOConsole());//esiste un assert che verifica l'output?
			assertEquals(0,partita[1].getGiocatore().getNumeroAttrezzi());
			assertEquals(10,partita[1].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiBorsaNoEmptyParametersValid() {
			comando = new ComandoPrendi();
			comando.setParametro("osso");
			comando.esegui(partita[1],new IOConsole());//esiste un assert che verifica l'output?
			assertEquals(1,partita[1].getGiocatore().getNumeroAttrezzi());
			assertEquals(9,partita[1].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiBorsaNoEmptyParametersValidButWeightItsToo() {
			comando = new ComandoPrendi();
			comando.setParametro("boh");
			comando.esegui(partita[2],new IOConsole());//esiste un assert che verifica l'output?
			assertEquals(0,partita[2].getGiocatore().getNumeroAttrezzi());
			assertEquals(1,partita[2].getStanzaCorrente().getNumeroAttrezzi());
		}
		
		@Test
		void testEseguiBorsaNoEmptyParametersValidButBagAll() {
			comando = new ComandoPrendi();
			comando.setParametro("osso");
			comando.esegui(partita[3],new IOConsole());//esiste un assert che verifica l'output?
			assertEquals(10,partita[3].getGiocatore().getNumeroAttrezzi());
			assertEquals(10,partita[3].getStanzaCorrente().getNumeroAttrezzi());
		}
}
