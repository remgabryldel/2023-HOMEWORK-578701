package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {

	private String parametro;
	private final static String NOME = "AbstractComando";
	private IO InOut;

	abstract public void esegui(Partita partita, IO io);

	@Override
	public void setParametro(String parametro) {
        this.parametro = parametro;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	public void setIo(IO io) {
		this.InOut = io;
	}

	public IO getIo() {
		return InOut;
	}


}
