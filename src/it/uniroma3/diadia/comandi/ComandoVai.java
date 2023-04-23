package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione=null;
	private IO inout = new IOConsole();

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui(Partita partita) {
		if(partita==null) {inout.mostraMessaggio("Attenzione riavviare il gioco, errore grave.");return;}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione==null) {
			inout.mostraMessaggio("Dove vuoi andare?");
			inout.mostraMessaggio("Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza==null) {
			inout.mostraMessaggio("Direzione insesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		inout.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	/**
	 * imposta la direzione da seguire
	 * @param Stringa della direzione 
	 */
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
    /**
     * non è implementato questo metodo ritornerà null se chiamato
     */
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ritorna la direzione attuale da seguire
	 * @param Stringa della direzione attuale 
	 */
	@Override
	public String getParametro() {
		return this.direzione;
	}
}
