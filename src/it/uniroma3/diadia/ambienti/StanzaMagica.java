package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 *Classe StanzaMagica - una stanza che effettua una magia in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * Se posato un attrezzo particolare permette di avere accesso alla stanza adiaciente. 
 * 
 * @author docente di POO 
 * @see Stanza
 * @version base
 */
public class StanzaMagica extends Stanza {
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati>this.sogliaMagica)
		attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);	}

	/*
	 * Implementabili?
	 * public void setAttrezziDefault(Attrezzo... attrezzo) {
		for(int i = 0; i < attrezzo.length ; i++) {
		super.addAttrezzo(attrezzo[i]);	
		}
	}
	
	public int getSogliaMagica() {
		if(this.sogliaMagica==0)
			return SOGLIA_MAGICA_DEFAULT;
		return this.sogliaMagica;
	}*/
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}
}
