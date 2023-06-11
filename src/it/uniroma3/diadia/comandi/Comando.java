package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {

	/**
	 * esecuzione del comando
	 */
	public void esegui(Partita partita, IO io);
	
	/**
	* set parametro del comando
	*/
	public void setParametro(String parametro);
	
	/**
	 * returna il nome del comando
	 * @return stringa nome del comando
	 */
	public String getNome();
	
	/**
	 * returna il parametro
	 * @return stringa parametro
	 */
	public String getParametro();
}
