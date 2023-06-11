package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	/*
	 * TODO Scrivere i test di unità su questa classe
     *concreta ma limitarsi alla sola verifica del
     *corretto riconoscimento dei comandi
	 * */
	@Override
	public Comando costruisciComando(String istruzione) {
        if(istruzione==null)
        	return new ComandoNonValido("errore l'istruzione ricevuta era null.");
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null;
			String parametro = null;
			Comando comando = null;
			
			
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();// prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next(); // seconda parola: eventuale param.
			
			
			if (nomeComando == null)
				comando = new ComandoNonValido("errore, non hai inserito un comando.");//controlla che sia corretta
			else if (nomeComando.equals("vai"))
				comando = new ComandoVai();//completo guarda e controlla se i test sono corretti
			else if (nomeComando.equals("prendi"))
				comando = new ComandoPrendi();//junit test e codice vedi correttezza 
			else if (nomeComando.equals("posa"))
				comando = new ComandoPosa();// junit test e anche codice vedi correttezza
			else if (nomeComando.equals("aiuto"))
				comando = new ComandoAiuto();// junit test non credo sia utile, vedi info su array di stringhe
			else if (nomeComando.equals("fine"))
				comando = new ComandoFine();//junit test non credo sia utile, verifica correttezza
			else if (nomeComando.equals("guarda"))
				comando = new ComandoGuarda();
			else comando = new ComandoNonValido("errore, il comando inserito non è valido");
			comando.setParametro(parametro);
			return comando;
		}
	}
}