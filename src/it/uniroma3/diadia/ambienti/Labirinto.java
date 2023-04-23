package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella il labirinto del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Labirinto {
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	
	public Labirinto() {
		creaStanze();

	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",4);
		Attrezzo chiave =new Attrezzo("chiave",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBloccata("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new StanzaMagica("Aula N10",2);
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		Stanza ripostiglio = new StanzaBuia("Ripostiglio");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		laboratorio.impostaStanzaAdiacente("sud", ripostiglio);
	    ripostiglio.impostaStanzaAdiacente("nord", laboratorio);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		ripostiglio.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
        stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
    }
    
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
	
}
