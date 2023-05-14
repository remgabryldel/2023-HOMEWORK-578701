package it.uniroma3.diadia.ambienti;

public class StanzaBuiaProtected extends StanzaProtected{

	final static private String NOME_ATTREZZO = "lanterna";
	private String nomeAttrezzo;

	public StanzaBuiaProtected(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzo=nomeAttrezzo;
	}
	
	public StanzaBuiaProtected(String nome) {
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
