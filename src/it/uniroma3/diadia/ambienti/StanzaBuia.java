package it.uniroma3.diadia.ambienti;

/**
 * Classe StanzaBuia - una stanza che in essa non è visibile in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * Se posato un attrezzo particolare permette di avere la descrizione della stanza. 
 * 
 * @author docente di POO 
 * @see Stanza
 * @version base
 */
public class StanzaBuia extends Stanza {

	final static private String NOME_ATTREZZO = "lanterna";
	private String nomeAttrezzo;

	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzo=nomeAttrezzo;
	}
	
	public StanzaBuia(String nome) {
		this(nome,NOME_ATTREZZO);
	}
	
	/**
	 * questo metodo sovrascrive il metodo presente nella classe Stanza,
	 * ritorna la descrizione della stanza se nella stanza è presente
	 * un particolare attrezzo, altrimenti ritorna una stringa predefinita
	 * @return Stringa
	 */
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(nomeAttrezzo)) 
			return super.getDescrizione();
		else
		    return "qui c'è buio pesto";
	}
}
