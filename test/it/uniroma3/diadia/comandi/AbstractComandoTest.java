package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class AbstractComandoTest {

	private ConcreteComando cc;
	private Partita p;
	@BeforeEach
	void setUp() {
		cc = new ConcreteComando();
		p = new Partita(new LabirintoBuilder()
				       .addStanzaIniziale("stanza")
				       .getLabirinto());
	}

	@Test
	void testParametro() {
		cc.setParametro("pippo");
		 assertNotNull(cc.getParametro());
	}

	@Test
	void testGetNome() {
		 assertNotEquals("AbstractComando", cc.getNome());
		 assertEquals("ConcreteComando", cc.getNome());
    }
	
	@Test
	void testEsegui() {
		cc.esegui(p,new IOConsole());
		 assertTrue(p.isFinita());
    }

	public class ConcreteComando extends AbstractComando{

		@Override
		public void esegui(Partita partita, IO io) {	
			partita.setFinita();
		}
	}

}
