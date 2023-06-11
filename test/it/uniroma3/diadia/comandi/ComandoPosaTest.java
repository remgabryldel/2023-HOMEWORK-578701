package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
  private Partita partita[] = new Partita[3];
  private ComandoPosa comando;
  
  @BeforeEach
  public void setup() {
	  Labirinto monolocale_vuoto = new LabirintoBuilder()
				 .addStanzaIniziale("Atrio")
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
	  partita[1] = new Partita(monolocale_vuoto);
	  partita[1].getGiocatore().addAttrezzo(new Attrezzo("boh",1));
	  partita[2] = new Partita(monolocale_pieno);
	  partita[2].getGiocatore().addAttrezzo(new Attrezzo("boh",1));

  }
  
	@Test
	void testEseguiBorsaEmpty() {
		comando = new ComandoPosa();
		comando.esegui(partita[0],new IOConsole());//esiste un assert che verifica l'output?
		assertEquals(0,partita[0].getGiocatore().getNumeroAttrezzi());

	}

	@Test
	void testEseguiBorsaNoEmptyParametersNull() {
		comando = new ComandoPosa();
		comando.esegui(partita[1],new IOConsole());//esiste un assert che verifica l'output?
		assertEquals(1,partita[1].getGiocatore().getNumeroAttrezzi());

	}
	
	@Test
	void testEseguiBorsaNoEmptyParametersInvalid() {
		comando = new ComandoPosa();
		comando.setParametro("invalid");
		comando.esegui(partita[1],new IOConsole());//esiste un assert che verifica l'output?
		assertEquals(1,partita[1].getGiocatore().getNumeroAttrezzi());
	}
	
	@Test
	void testEseguiBorsaNoEmptyParametersValid() {
		comando = new ComandoPosa();
		comando.setParametro("boh");
		comando.esegui(partita[1],new IOConsole());//esiste un assert che verifica l'output?
		assertEquals(0,partita[1].getGiocatore().getNumeroAttrezzi());
	}
	
	@Test
	void testEseguiBorsaNoEmptyParametersValidButRoomIsFull() {
		comando = new ComandoPosa();
		comando.setParametro("boh");
		comando.esegui(partita[2],new IOConsole());//esiste un assert che verifica l'output?
		assertEquals(1,partita[2].getGiocatore().getNumeroAttrezzi());
		assertEquals(10,partita[2].getStanzaCorrente().getNumeroAttrezzi());
	}
}
