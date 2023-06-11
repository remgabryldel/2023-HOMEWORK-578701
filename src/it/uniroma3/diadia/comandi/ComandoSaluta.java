package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {

	private static final String MESSAGGIO_NO_PERSONAGGI = "Non c'e' alcun personaggio in questa stanza!";
	
	@Override
	public void esegui(Partita partita,IO io) {
		if(partita.getStanzaCorrente().getPersonaggio()!=null)
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else 
			io.mostraMessaggio(MESSAGGIO_NO_PERSONAGGI);
	}

	@Override
	public String getNome() {
		return "ComandoSaluta";
	}
}
