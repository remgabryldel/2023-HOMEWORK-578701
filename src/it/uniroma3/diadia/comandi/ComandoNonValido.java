package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;

public class ComandoNonValido implements Comando {
    private IO inout = new IOConsole();
    private String Messaggio; 
    
    public ComandoNonValido() {
    	this("errore, comando inserito non valido");
    }
    
    public ComandoNonValido(String Messaggio) {
    	this.Messaggio = Messaggio;
    }
    
	@Override
	public void esegui(Partita partita) {
		inout.mostraMessaggio(Messaggio);
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
