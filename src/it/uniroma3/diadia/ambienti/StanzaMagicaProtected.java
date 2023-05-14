package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Classe StanzaMagica - una stanza con effetti magici in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * Se posato un attrezzo un certo numero di volte si verificano degli effetti
 * magici su ogni attrezzo. 
 * 
 * @author docente di POO 
 * @see Stanza
 * @version base
 */
public class StanzaMagicaProtected extends StanzaProtected {
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica) {
			this.attrezzi.remove(attrezzo.getNome());
			attrezzo = this.modificaAttrezzo(attrezzo);
			this.attrezzi.put(attrezzo.getNome(),attrezzo);
			return true;
		}
	    return false;
	}

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
