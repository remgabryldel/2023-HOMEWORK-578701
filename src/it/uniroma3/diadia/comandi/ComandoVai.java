package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	private Direzione direzione=null;
	static final private String NOME = "ComandoVai";

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui(Partita partita,IO io) {
		super.setIo(io);
		if(partita==null) {super.getIo().mostraMessaggio("Attenzione riavviare il gioco, errore grave.");return;}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione==null) {
			super.getIo().mostraMessaggio("Dove vuoi andare?");
			super.getIo().mostraMessaggio("Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if (prossimaStanza==stanzaCorrente) {
			super.getIo().mostraMessaggio("Direzione insesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		super.getIo().mostraMessaggio("ti trovi in "+partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	/**
	 * imposta la direzione da seguire
	 * @param Stringa della direzione 
	 */
	@Override
	public void setParametro(String parametro) {
		this.direzione = Direzione.valueOf(parametro);
	}
    /**
     * non è implementato questo metodo ritornerà null se chiamato
     */
	@Override
	public String getNome() {
		return NOME;
	}

	/**
	 * ritorna la direzione attuale da seguire
	 * @param Stringa della direzione attuale 
	 */
	@Override
	public String getParametro() {
		return this.direzione.name();
	}
}
