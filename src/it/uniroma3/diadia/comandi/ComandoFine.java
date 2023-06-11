package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	static final private String NOME = "ComandoFine";

	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita,IO io) {
		 super.setIo(io);
		super.getIo().mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
