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

	final static private String NOME_ATTREZZO = "chiave";
	final static private String NOME_DIREZIONE = "nord";
	private String direzione;
	private String nomeAttrezzo;
	
	public StanzaBloccata(String nome,String direzione, String nomeAttrezzo) {
		super(nome);
		this.direzione = direzione;
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	public StanzaBloccata(String nome) {
		this(nome, NOME_DIREZIONE, NOME_ATTREZZO);
	}
	
	/**
	 * Metodo che verifica se nella stanza è presente un attrezzo,
	 * dall'esito ritorna la stanza adiacente o la stanza stessa.
	 * @param direzione che si vuole intraprendere
	 * @return una stanza
	 */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(this.hasAttrezzo(nomeAttrezzo)||!this.direzione.equals(direzione))
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
	
}
