package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Borsa - un inventario in un gioco di ruolo.
 * Una borsa e un invenrio di attrezzi del gioco.
 * Può collezionare attrezzi che risiedono nelle varie stanze fino a un massimo di peso.
 * 
 * @author docente di POO 
 * @see Giocatore
 * @version base
 */
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	/**
	 * Crea una borsa con un peso massimo costante.
	 * 
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Crea una borsa con un peso massimo pari a pesoMax, può contenere al massimo 10 elementi
	 * inizialmente è vuota.
	 * @param peso massimo supportato dalla borsa
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Inserisce un attrezzo nella borsa, se essa non ha 10 elementi o la somma dei pesi degli attrezzi,
	 * incluso l'attrezzo da aggiungiere, non raggiunge o oltrepassa il limite prefissato.
	 * @param attrezzo l'attrezzo da mettere nella borsa.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	/**
	 * ritorna il valore di peso massimo che la borsa puo avere
	 * @return il valore del peso massimo
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * ricerca un attrezzo nella borsa e se lo trova ritorna un riferimento ad esso
	 * @param il nome dell'attrezzo da cercare
	 * @return un riferimento all'attrezzo cercato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	
	/**
	 * ritorna la lista di attrezzi presenti attualmente in borsa
	 * @return una lista di attrezzi
	 */
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}
	
	/**
	 * ritorna il numero effettivo di attrezzi presenti in borsa
	 * @return
	 */
	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}
	/**
	 * ritorna il valore del peso attuale di tutti gli oggetti contenuti nella borsa
	 * @return il peso degli attrezzi contenuti
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	 /**
	  * ritorna un booleano che indica se la borsa è vuota
	  * @return true se la borsa è vuota, altrimenti false
	  */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * verifica se un attrezzo è presente nella borsa 
	 * @param nome attrezzo da ricercare
	 * @return true se l'attrezzo è presente, altrimenti false
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * rimove un attrezzo dalla borsa 
	 * @param nome attrezzo da rimuovere
	 * @return il rifermento all'attrezzo rimosso dalla borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
			 return null;
		Attrezzo a = null;
		int i= 0;
		while( i<this.numeroAttrezzi) {
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
			    a = attrezzi[i];
				while(i<(this.numeroAttrezzi-1)) {
				    attrezzi[i] = attrezzi[i+1];
				    i++;
				}
				attrezzi[i]=null;
				this.numeroAttrezzi--;
			}
            i++;
		}
		//System.out.println(a==null);
		//System.out.println(a);
		return a;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}