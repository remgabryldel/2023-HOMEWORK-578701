package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;

public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = {"vai <direzione>", "aiuto", "fine", "prendi <nome_oggetto>", "posa <nome_oggetto>","guarda"};
	IO InOut = new IOConsole();
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		StringBuilder build = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) 
			build.append(elencoComandi[i]+" ; ");
        InOut.mostraMessaggio(build.toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
