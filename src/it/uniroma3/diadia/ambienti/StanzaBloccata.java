package it.uniroma3.diadia.ambienti;

/**
 * Classe StanzaBloccata - una stanza che in essa una direzione non accessibile in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * Se posato un attrezzo particolare permette di avere accesso alla stanza adiaciente. 
 * 
 * @author docente di POO 
 * @see Stanza
 * @version base
 */
public class StanzaBloccata extends Stanza {

	private Direzione direzione;
	private String nomeAttrezzo;
	
	public StanzaBloccata(String nome,Direzione direzione, String nomeAttrezzo) {
		super(nome);
		this.direzione = direzione;
		this.nomeAttrezzo = nomeAttrezzo;
	}

	/**
	 * Metodo che verifica se nella stanza è presente un attrezzo,
	 * dall'esito ritorna la stanza adiacente o la stanza stessa.
	 * @param direzione che si vuole intraprendere
	 * @return una stanza
	 */
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(this.hasAttrezzo(nomeAttrezzo)||!this.direzione.name().equals(direzione.name()))
			return super.getStanzaAdiacente(direzione);
		return this;
	}
	
	/**
	 * Metodo che veri se nella stanza è presente un attrezzo,
	 * dall'esito ritorna la stanza adiacente o la stanza stessa.
	 * @param direzione che si vuole intraprendere
	 * @return una stanza
	 */
	@Override
	public String getDescrizione() {
			StringBuilder S = new StringBuilder(super.getDescrizione());
			if(!this.hasAttrezzo(nomeAttrezzo)) {
				S.append("\nLa stanza adiaciente "+direzione+" è bloccata,");
				S.append("\nserve l'attrezzo "+nomeAttrezzo+ " nella stanza per sbloccarla");
				return S.toString();
			}
			return S.toString();
	}
	
	/**
	 * Ottiene il nome dell'attrezzo che blocca la stanza
	 * @return nome attrezzo che blocca la stanza
	 */
	public String getNomeAttrezzo() {
		return this.nomeAttrezzo;
	}
	
	/**
	 * Imposta il nome dell'attrezzo che blocca la stanza
	 * @param nome attrezzo che blocca la stanza
	 */
	public void setNomeAttrezzo(String nomeAttrezzo) {
	    this.nomeAttrezzo = nomeAttrezzo;
	}
	/**
	 * Ottiene il nome della direzione bloccata nella stanza
	 * @return nome direzione bloccata
	 */
	public String getNomeDirezione() {
		return this.direzione.name();
	}
	/**
	 * Ottiene il nome della direzione bloccata nella stanza
	 * @return nome direzione bloccata
	 */
	public void setNomeDirezione(String direzione) {
	     this.direzione = Direzione.valueOf(direzione);
	}
	
	@Override
	public boolean equals(Object o) {
		boolean eq = false;
		StanzaBloccata that = (StanzaBloccata)o;
		eq = this.nomeAttrezzo.equals(that.getNomeAttrezzo());
		eq = eq && this.direzione.name().equals(that.getNomeDirezione());
		if(eq)
			eq = super.equals(that);
		return eq;
	}
	
}
