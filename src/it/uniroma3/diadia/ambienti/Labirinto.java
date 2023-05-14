package it.uniroma3.diadia.ambienti;


/**
 * Questa classe modella il labirinto del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
    
    /**
     * ritorna un riferimento alla stanza corrente
     * @return un riferimento alla stanza corrente
     */
	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}
	/**
     * imposta la stanza corrente 
     * @param riferimento alla stanza da impostare corrente
     */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	/**
     * ritorna un riferimento alla stanza vincente
     * @return riferimento alla stanza vincente
     */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	/**
     * imposta la stanza vincente 
     * @param riferimento alla stanza da impostare vincente
     */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	//!!trovo inutile testare questi metodi dato che sono solo getter e setter!!
	
	/**
     * ritorna un riferimento alla stanza iniziale
     * @return riferimento alla stanza iniziale
     */
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	/**
     * imposta la stanza iniziale
     * @param riferimento alla stanza da impostare iniziale
     */
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}
	
}
