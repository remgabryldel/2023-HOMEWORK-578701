package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo implements Comparable<Attrezzo>{

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Imposta il nome identificatore dell'attrezzo
	 * @param il nuovo nome identificatore dell'attrezzo
	 */
	public void setNome(String nome) {
		this.nome=nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}
	/**
	 * Imposta il peso dell'attrezzo
	 * @param il nuovo peso dell'attrezzo
	 */
	public void setPeso(int peso) {
		this.peso=peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override
	public int compareTo(Attrezzo that) {
		return this.getNome().compareTo(that.getNome());
	}
	
	@Override
	public boolean equals(Object o) {
		Attrezzo that = (Attrezzo)o;
		return this.getNome().equals(that.getNome()) && this.getPeso() == that.getPeso();
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode()+this.getPeso();
	}
}