package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
    private String Messaggio; 
    static final private String NOME = "ComandoNonValido";
    
    public ComandoNonValido() {
    	this("errore, comando inserito non valido");
    }
    
    public ComandoNonValido(String Messaggio) {
    	this.Messaggio = Messaggio;
    }
    
	@Override
	public void esegui(Partita partita,IO io) {
		super.setIo(io);
		super.getIo().mostraMessaggio(Messaggio);
	}

	@Override
	public void setParametro(String parametro) {
		this.Messaggio = parametro;
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.Messaggio;
	}

}
