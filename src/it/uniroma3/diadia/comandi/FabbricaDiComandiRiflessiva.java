package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@SuppressWarnings("deprecation")
	@Override
	public Comando costruisciComando(String istruzione) {
		if(istruzione==null)
        	return new ComandoNonValido("errore l'istruzione ricevuta era null.");
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null; // es. ‘vai’
			String parametro = null; // es. ‘sud’
			Comando comando = null;
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();//prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();//seconda parola: eventuale parametro
			if (nomeComando == null)
				return new ComandoNonValido("errore, non hai inserito un comando.");//controlla che sia corretta
			try {
				String nomeClasse = "it.uniroma3.diadia.comandi.Comando";
				nomeClasse += Character.toUpperCase(nomeComando.charAt(0));
				nomeClasse += nomeComando.substring(1);
				comando = (Comando)Class.forName(nomeClasse).newInstance();
				comando.setParametro(parametro);
			} catch (Exception e) {
				comando = new ComandoNonValido();
				comando.setParametro("errore, il comando inserito non è valido");
			}
			return comando;
		}
	}

}
