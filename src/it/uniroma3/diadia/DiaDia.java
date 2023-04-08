package it.uniroma3.diadia;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diaida.IOConsole.IOConsole;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi <nome_oggetto>", "posa <nome_oggetto>"};

	private Partita partita;
	private final static IOConsole InOut = new IOConsole();

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
       
		InOut.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {		
			istruzione = InOut.leggiRiga();
		}while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		String nome_comando = comandoDaEseguire.getNome();

		if (comandoDaEseguire.sconosciuto()) {
			InOut.mostraMessaggio("Comando sconosciuto");
		} else {
			if (nome_comando.equals("fine")) {
				this.fine(); 
				return true;
			} else if (nome_comando.equals("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (nome_comando.equals("aiuto"))
				this.aiuto();
			else if (nome_comando.equals("prendi"))
				this.prendi(comandoDaEseguire.getParametro());
			else if (nome_comando.equals("posa"))
				this.posa(comandoDaEseguire.getParametro());
			else
				InOut.mostraMessaggio("Comando sconosciuto");
		}
		if (this.partita.vinta()) {
			InOut.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			InOut.mostraMessaggio(elencoComandi[i]+" ");
        InOut.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			InOut.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			InOut.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu--);
		}
		InOut.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
	}
	
	/**
	 * Cerca di prendere un oggetto. Se c'e' un oggetto lo inserisce in borsa
	 * e ne stampa l'evento altrimenti mostra una lista dei suddetti presenti nella stanza,
	 *  altrimenti se non riesce a prendere l'oggetto citato ne stampa l'errore
	 *  @param stringa del nome di un attrezzo
	 */
	private void prendi(String nomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		if(nomeAttrezzo==null) {
			int numeroAttrezzi = stanzaCorrente.getNumeroAttrezzi();
			if(numeroAttrezzi == 0  ) {
    			InOut.mostraMessaggio("non vi sono attrezzi");
                return;
            }
			InOut.mostraMessaggio("quale attrezzo vuoi prendere?");
			StringBuilder risultato = new StringBuilder();
			for (int i = 0; i<numeroAttrezzi ;i++) {
				Attrezzo attrezzo = stanzaCorrente.getAttrezzi()[i];
				if(attrezzo!=null)
					risultato.append(attrezzo.toString()+" ");
			}
			InOut.mostraMessaggio(risultato.toString());
			return;
		}else {
			if(stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo prendi =stanzaCorrente.getAttrezzo(nomeAttrezzo);
					Giocatore giocatore = this.partita.getGiocatore();
					if(giocatore.addAttrezzo(prendi)) {
						if(stanzaCorrente.removeAttrezzo(nomeAttrezzo)) {
							InOut.mostraMessaggio("hai preso l'oggetto "+prendi.toString());
							return;
						}else {
							stanzaCorrente.addAttrezzo(giocatore.removeAttrezzo(nomeAttrezzo));
							InOut.mostraMessaggio("errore l'oggetto non è stato preso");
							}
					}else
						InOut.mostraMessaggio("errore impossibile prendere attrezzo");
			}else
				InOut.mostraMessaggio("non esiste questo attrezzo "+nomeAttrezzo);

		}

	}

	/**
	 * Cerca di posare un oggetto. Se c'e l'oggetto in  borsa viene rilasciato nella stanza corrente,
	 * ne stampa l'evento altrimenti mostra una lista dei suddetti presenti in borsa,
	 *  altrimenti se non riesce a gettare l'oggetto citato ne stampa l'errore
	 *  @param stringa del nome di un attrezzo
	 */
	private void posa(String nomeAttrezzo) {
		Giocatore giocatore = this.partita.getGiocatore();
		if(nomeAttrezzo==null) {
            if(giocatore.getNumeroAttrezzi() == 0  ) {
            	InOut.mostraMessaggio("non vi sono attrezzi nella borsa");
                return;
            }
            InOut.mostraMessaggio("quale attrezzo vuoi posare?");
            InOut.mostraMessaggio(giocatore.mostraInventario());
			return;
		}else {
			if(giocatore.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo getta = giocatore.removeAttrezzo(nomeAttrezzo);
					if(!giocatore.hasAttrezzo(nomeAttrezzo)) {
						Stanza stanzaCorrente = this.partita.getStanzaCorrente();
						if(stanzaCorrente.addAttrezzo(getta)) {
							InOut.mostraMessaggio("hai posato l'oggetto "+getta.toString());
							return;
						}else {
							giocatore.addAttrezzo(getta);
							InOut.mostraMessaggio("errore l'attrezzo non è stato posato");
							}
					}else
						InOut.mostraMessaggio("errore impossibile posare attrezzo");
			}else
				InOut.mostraMessaggio("non hai questo attrezzo "+nomeAttrezzo);
		}
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		InOut.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}