package it.uniroma3.diadia.ambienti;
import java.util.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe LabirintoBuilder che si occupa della generazione di labirinti.
 * @author Remigiusz Gabryl Della Rosa - 578701
 * @version base
 * @see Labirinto
 */

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Map<String,Stanza> string2stanza;
	private Stanza ultimaInserita;
	
	public LabirintoBuilder() {
		this.string2stanza = new HashMap<>();
	}
	
	/**
	 * Ritorna il labirinto cosi come specificato.
	 * @return ritorna il labirinto con le stanze generate.
	 */
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	/**
	 * Imposta la stanza iniziale del labirinto l'ultima inserita, altrimenti ne crea una.
	 * @param nomeStanza
	 *
	 */
	public LabirintoBuilder addStanzaIniziale(String nomeStanza){
		if(nomeStanza==null)
			return this;
		if(!this.string2stanza.containsKey(nomeStanza)) {
		      this.ultimaInserita = new Stanza(nomeStanza);
			  this.string2stanza.put(nomeStanza,this.ultimaInserita);
		}
		//this.labirinto.setStanzaIniziale(this.ultimaInserita);
		this.labirinto.setStanzaCorrente(this.ultimaInserita);
		return this ;
	}
	
	/**
	 * Imposta la stanza vincente del labirinto.
	 * @param nomeStanza
	 *
	 */
	
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		if(nomeStanza==null)
			return this;
		if(!this.string2stanza.containsKey(nomeStanza))
		     this.ultimaInserita = new Stanza(nomeStanza);
		this.labirinto.setStanzaVincente(this.ultimaInserita);
		this.string2stanza.put(nomeStanza,this.ultimaInserita);
		return this;
	}
	
	/**
	 * Aggiunge una stanza al labirinto.
	 * @param nomeStanza
	 *
	 */
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		if(nomeStanza==null)
			return this;
		this.ultimaInserita = new Stanza(nomeStanza);
		this.string2stanza.put(nomeStanza,this.ultimaInserita);
		return this;
	}
	
	/**
	 * Aggiunge una stanza bloccata al labirinto.
	 * @param nomeStanza
	 * @param direzioneBloccata
	 * @param nomeAttrezzoSbloccante
	 *
	 */
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza,Direzione direzioneBloccata, String nomeAttrezzoSbloccante) {
		if(nomeStanza==null)
			return this;
		this.ultimaInserita = new StanzaBloccata(nomeStanza,direzioneBloccata,nomeAttrezzoSbloccante);
		this.string2stanza.put(nomeStanza,this.ultimaInserita);
		return this;
	}
	
	/**
	 * Aggiunge una stanza buia al labirinto.
	 * @param nomeStanza
	 * @param nomeAttrezzoIlluminante
	 *
	 */
	
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String nomeAttrezzoIlluminante) {
		if(nomeStanza==null)
			return this;
		this.ultimaInserita = new StanzaBuia(nomeStanza,nomeAttrezzoIlluminante);
		this.string2stanza.put(nomeStanza,this.ultimaInserita);
		return this;
	}
	
	/**
	 * Aggiunge una stanza magica al labirinto.
	 * @param nomeStanza
	 * @param sogliaMagica
	 *
	 */
	
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		if(nomeStanza==null)
			return this;
		this.ultimaInserita = new StanzaMagica(nomeStanza,sogliaMagica);
		this.string2stanza.put(nomeStanza,this.ultimaInserita);
		return this;
	}
	
	/**
	 * Aggiunge un attrezzo all'ultima stanza aggiunta nel labirinto.
	 * @param nomeAttrezzo
	 * @param peso
	 *
	 */
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		this.ultimaInserita.addAttrezzo(new Attrezzo(nomeAttrezzo,peso));
		return this;
	}
	
	/**
	 * Imposta l'adiacenza tra due stanze gia esistenti se .
	 * @param nome1
	 * @param nome2
	 * @param direzione
	 * 
	 */

	public LabirintoBuilder addAdiacenza(String nome1, String nome2, Direzione direzione) {
		if(this.string2stanza.containsKey(nome1)&&this.string2stanza.containsKey(nome2))
			if(direzione!=null) 
			   this.string2stanza.get(nome1).impostaStanzaAdiacente(direzione, this.string2stanza.get(nome2));
		return this;
	}
	

	/**
	 * Lista delle Stanza con chiave il loro nome.
	 * @return ritorna una mappa con chiave String e valori Stanza.
	 */
	
	public Map<String,Stanza> getListaStanze() {
		return this.string2stanza;
	}
}
