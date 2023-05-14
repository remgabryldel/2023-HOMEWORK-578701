package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String,Attrezzo> attrezzi;
	private Map<String,Stanza> stanzeAdiacenti;
	

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {//fai test
		if ((this.stanzeAdiacenti.containsKey(direzione)||this.getNumeroStanzeAdiacenti() < NUMERO_MASSIMO_DIREZIONI )&& direzione!=null) {
			this.stanzeAdiacenti.put(direzione, stanza);		
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {//fai test
		if(this.stanzeAdiacenti.containsKey(direzione))
		    return this.stanzeAdiacenti.get(direzione);
		return this;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return new ArrayList<>(this.attrezzi.values());
	}
	
	/**
	 * Restituisce il numero di attrezzi presenti nella stanza.
	 * @return il numero di attrezzi nella stanza.
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {//fai test
		if ((this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) && (attrezzo!= null)) {
			this.attrezzi.put(attrezzo.getNome(),attrezzo);
			return true;
		}
		return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: \n");
		for (String direzione : this.stanzeAdiacenti.keySet())
			//if (direzione!=null)
				risultato.append(direzione + " ; ");
		risultato.append("\nAttrezzi nella stanza:\n");
		for (Attrezzo a: this.attrezzi.values()) {
			if(a!=null)
				risultato.append(a.toString()+" ; ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {//fai test
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {//fai test
		Attrezzo attrezzoTrovato = null;
		if (nomeAttrezzo != null && this.attrezzi.containsKey(nomeAttrezzo))
			attrezzoTrovato = this.attrezzi.get(nomeAttrezzo);
		return attrezzoTrovato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {//fai test
		if((nomeAttrezzo != null) && (!this.attrezzi.isEmpty()))
			return this.attrezzi.remove(nomeAttrezzo) != null;
		return false;

	}

	/**
	 * Restituisce la collezione di direzioni delle stanze adiacenti.
	 * @return la collezione di direzioni delle stanze adiacenti dalla stanza attuale.
	 */
	public List<String> getDirezioni() {
        return new ArrayList<>(this.stanzeAdiacenti.keySet());
	}

	/**
	 * Restituisce la collezione di stanze adiacenti.
	 * @return la collezione di stanze adiacenti dalla stanza attuale.
	 */
	public Map<String,Stanza> getMapStanzeAdiacenti() {
        return this.stanzeAdiacenti;
	}
	/**
	 * Restituisce il numero di stanze adiacenti.
	 * @return il numero di stanze adiacenti dalla stanza attuale.
	 */
	public int getNumeroStanzeAdiacenti() {
		return this.stanzeAdiacenti.size();
	}
	/**
	 * Ritorna true se la stanza è normale altrimenti viene sovrascritto il metodo da StanzaMagica
	 * @return booleano
	 * @see StanzaMagica
	 */
	public boolean isMagica() {
		return false;
	}
	
}