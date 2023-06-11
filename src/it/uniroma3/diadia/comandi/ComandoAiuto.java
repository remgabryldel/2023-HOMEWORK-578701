package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	static final private String NOME = "ComandoAiuto";
	static final private String[] elencoComandi = {"vai <direzione>", "aiuto", "fine", "prendi <nome_oggetto>", "posa <nome_oggetto>","guarda"};
	
	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita,IO inOut) {
		super.setIo(inOut);
		StringBuilder build = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) 
			build.append(elencoComandi[i]+" ; ");
        super.getIo().mostraMessaggio(build.toString());
	}


	@Override
	public String getNome() {
		return NOME;
	}

}
