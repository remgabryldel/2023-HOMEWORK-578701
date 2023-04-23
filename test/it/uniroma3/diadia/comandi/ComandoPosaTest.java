package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
  private Partita partita[] = new Partita[3];
  private ComandoPosa comando;
  
  @BeforeEach
  public void setup() {
	  partita[0] = new Partita();
	  partita[1] = new Partita();
	  partita[1].getGiocatore().addAttrezzo(new Attrezzo("boh",1));
	  partita[2] = new Partita();
	  partita[2].getGiocatore().addAttrezzo(new Attrezzo("boh",1));
	  for(int i=0;i<9;i++) {
		  partita[2].getStanzaCorrente().addAttrezzo(new Attrezzo(String.valueOf(i),1));
	  }
  }
  
	@Test
	void testEseguiBorsaEmpty() {
		comando = new ComandoPosa();
		comando.esegui(partita[0]);//esiste un assert che verifica l'output?
		assertEquals(0,partita[0].getGiocatore().getNumeroAttrezzi());

	}

	@Test
	void testEseguiBorsaNoEmptyParametersNull() {
		comando = new ComandoPosa();
		comando.esegui(partita[1]);//esiste un assert che verifica l'output?
		assertEquals(1,partita[1].getGiocatore().getNumeroAttrezzi());

	}
	
	@Test
	void testEseguiBorsaNoEmptyParametersInvalid() {
		comando = new ComandoPosa();
		comando.setParametro("invalid");
		comando.esegui(partita[1]);//esiste un assert che verifica l'output?
		assertEquals(1,partita[1].getGiocatore().getNumeroAttrezzi());
	}
	
	@Test
	void testEseguiBorsaNoEmptyParametersValid() {
		comando = new ComandoPosa();
		comando.setParametro("boh");
		comando.esegui(partita[1]);//esiste un assert che verifica l'output?
		assertEquals(0,partita[1].getGiocatore().getNumeroAttrezzi());
	}
	
	@Test
	void testEseguiBorsaNoEmptyParametersValidButRoomIsFull() {
		comando = new ComandoPosa();
		comando.setParametro("boh");
		comando.esegui(partita[2]);//esiste un assert che verifica l'output?
		assertEquals(1,partita[2].getGiocatore().getNumeroAttrezzi());
		assertEquals(10,partita[2].getStanzaCorrente().getNumeroAttrezzi());
	}
}
