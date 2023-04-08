package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Classe Giocatore - alias del giocatore all'interno del gioco.
 * Un giocatore ha un punteggio alias cfu, e una borsa.
 * Può collezionare attrezzi e alterare i cfu.
 * 
 * @author docente di POO 
 * @see Giocatore
 * @version base
 */
public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;

	/**
	 * Crea un giocatore con i cfu iniziali e una borsa.
	 * 
	 */
	public Giocatore() {
		this.setCfu(CFU_INIZIALI);
		this.borsa = new Borsa();
	}
    
	/**
	 * Inserisce un attrezzo nella borsa.
	 * @param attrezzo l'attrezzo da mettere nella borsa.
	 * @return true se riesce ad aggiungere l'attrezzo, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	
	/**
	 * rimove un attrezzo dalla borsa 
	 * @param nome attrezzo da rimuovere
	 * @return il rifermento all'attrezzo rimosso dalla borsa
	 */
	public Attrezzo removeAttrezzo(String Attrezzo) {
		Attrezzo att = this.borsa.removeAttrezzo(Attrezzo);
		System.out.println(att==null);
		System.out.println(att);
		return att;
	}
	
	/**
	 * ricerca un attrezzo nella borsa e se lo trova ritorna un riferimento ad esso
	 * @param il nome dell'attrezzo da cercare
	 * @return un riferimento all'attrezzo cercato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.borsa.getAttrezzo(nomeAttrezzo);
	}
	
	public int getNumeroAttrezzi() {
		return this.borsa.getNumeroAttrezzi();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.borsa.hasAttrezzo(nomeAttrezzo);
	}
	
	/**
	 * ritorna il numero di cfu del giocatore
	 * @return numero di cfu
	 */
	public int getCfu() {
		return cfu;
	}

	/**
	 * imposta il nuovo numero di cfu del giocatore
	 * @param numero di cfu
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	/**
	 * ritorna una stringa di tutti gli attrezzi collezionati
	 * @return stringa di attrezzi
	 */
	public String mostraInventario() {
		return this.borsa.toString();
	}
	
	//non trovo utile fare test su questa classe
}
