package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

    private Labirinto labirinto;
    private Giocatore giocatore;
	private boolean finita;
	
	public Partita(){
		this.finita = false;
		this.labirinto = new Labirinto();
		this.giocatore = new Giocatore();
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {//fai test
		return this.labirinto.getStanzaCorrente()== this.labirinto.getStanzaVincente();
	}
     
	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {//fai test
		return finita || vinta() || (this.giocatore.getCfu()) == 0;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
    
	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}
	
	public Stanza getStanzaCorrente(){
		return this.labirinto.getStanzaCorrente();
	}
	
	public void setStanzaCorrente(Stanza stanzaAttuale) {
		this.labirinto.setStanzaCorrente(stanzaAttuale);
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public boolean giocatoreIsVivo() {
		return this.getCfu()>0;
	}	
}
