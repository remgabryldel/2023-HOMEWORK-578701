package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		this.setCfu(CFU_INIZIALI);
		this.borsa = new Borsa();
	}
    
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	
	public Attrezzo removeAttrezzo(String Attrezzo) {
		return this.borsa.removeAttrezzo(Attrezzo);
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.borsa.getAttrezzo(nomeAttrezzo);
	}
	
	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
}
