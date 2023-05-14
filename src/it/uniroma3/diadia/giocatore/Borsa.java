package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;

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
	private Map<String,Attrezzo> attrezzi;
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
		this.attrezzi = new HashMap<>();
	}
	
	/**
	 * Inserisce un attrezzo nella borsa, se essa non ha 10 elementi o la somma dei pesi degli attrezzi,
	 * incluso l'attrezzo da aggiungiere, non raggiunge o oltrepassa il limite prefissato.
	 * @param attrezzo l'attrezzo da mettere nella borsa.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo != null)
			if (!(this.getPeso() + attrezzo.getPeso() > this.getPesoMax())) {
		         this.attrezzi.put(attrezzo.getNome(),attrezzo);
		 		return true;
			}
		return false;

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
		Attrezzo out = null;
		if(nomeAttrezzo!=null && this.attrezzi.containsKey(nomeAttrezzo))
			out = this.attrezzi.get(nomeAttrezzo);
		return out;
	}
	
	/**
	 * ritorna la lista di attrezzi presenti attualmente in borsa
	 * @return una lista di attrezzi
	 */
	public Attrezzo[] getAttrezzi() {
		List<Attrezzo> values = new ArrayList<>(this.attrezzi.values());
		Collections.reverse(values);
		return values.toArray(new Attrezzo[this.attrezzi.size()]);
	}
	
	/**
	 * ritorna il numero effettivo di attrezzi presenti in borsa
	 * @return
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	/**
	 * ritorna il valore del peso attuale di tutti gli oggetti contenuti nella borsa
	 * @return il peso degli attrezzi contenuti
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a: this.getAttrezzi())
			peso += a.getPeso();
		return peso;
	}
	 /**
	  * ritorna un booleano che indica se la borsa è vuota
	  * @return true se la borsa è vuota, altrimenti false
	  */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
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
		Attrezzo removed=null;
		if(nomeAttrezzo != null) 
			removed = this.attrezzi.remove(nomeAttrezzo);
		return removed;
	}
	
	/**
	 * Metodo che ordina gli attrezzi basandosi sul loro peso,
	 * e a parità di peso il nome.
	 * @return lista ordinata di attrezzi
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){// fai test
		List<Attrezzo> listaOrdinataPeso = new ArrayList<>(this.attrezzi.values());
		
		listaOrdinataPeso.sort(Comparator.comparing(Attrezzo::getNome));
		listaOrdinataPeso.sort(Comparator.comparingInt(Attrezzo::getPeso));
		
		return listaOrdinataPeso;
	}
	
	/**
	 * Metodo che crea un Set ordinato per nome.
	 * @return Set ordinato per nome di tutti gli attrezzi presenti nella borsa.
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){// fai test
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}
	/**
	 * Metodo che ordina il contenuto della borsa per peso, e a parità di peso per nome attraverso un set.
	 * @return contenuto della borsa ordinato per peso.
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {//fai test
		SortedSet<Attrezzo> ordinatoPeso = new TreeSet<>(new ComparatoreAttrezziPerPeso());
		ordinatoPeso.addAll(this.attrezzi.values());
		return ordinatoPeso;
	}
	
	/**
	 * Metodo che restituisce una mappa che ha come chiave un intero, e come valore un Set con tutti gli attrezzi che hanno quel peso.
	 * @return Map(integer),Set(Attrezzo))
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){// fai test
		Map<Integer,Set<Attrezzo>> mappaIntSet = new TreeMap<>();
		
		for(Attrezzo a: this.attrezzi.values()) {
			if(!mappaIntSet.containsKey(a.getPeso()))
				mappaIntSet.put(a.getPeso(),new HashSet<>());
			mappaIntSet.get(a.getPeso()).add(a);
		}
		return mappaIntSet;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.attrezzi.isEmpty()) { //se la borsa non e' vuota, stampa:
			//			s.append("\nContenuto borsa: " + this.getPeso() + " kg / " + this.getPesoMax() + " kg\n");
			//			s.append(this.getContenutoOrdinatoPerNome().toString());
			//			s.append(this.getContenutoOrdinatoPerPeso().toString());
			s.append("\nContenuto borsa raggruppato per peso: \n");
			//	        s.append(this.getContenutoRaggruppatoPerPeso().toString());
			s.append(this.getSortedSetOrdinatoPerPeso().toString());
			return s.toString();
		}
		else
			s.append("Borsa vuota.");
		return s.toString();
	}
}