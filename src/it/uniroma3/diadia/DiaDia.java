package it.uniroma3.diadia;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do {		
			istruzione = scannerDiLinee.nextLine();
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
			System.out.println("Comando sconosciuto");
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
			else
				System.out.println("Comando sconosciuto");
		}
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
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
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu--);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	/**
	 * Cerca di prendere un oggetto. Se c'e' un oggetto lo inserisce in borsa
	 * e ne stampa l'evento altrimenti mostra una lista dei suddetti,
	 *  altrimenti se non riesce a prendere l'oggetto citato ne stampa l'errore
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			System.out.println("quale oggetto vuoi prendere?");
			Attrezzo[] attrezzi = this.partita.getStanzaCorrente().getAttrezzi();
            if(attrezzi.length == 0  ) {
    			System.out.println("non vi sono oggetti");
                return;
            }
			int numeroAttrezzi = this.partita.getStanzaCorrente().getNumeroAttrezzi();
			for(int i = 0; i<numeroAttrezzi; i++) {
				System.out.println(attrezzi[i].toString());
			}
			return;
		}else {
			if(this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
				Attrezzo prendi =this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
					if(this.partita.addAttrezzo(prendi)) {
						if(this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo)) {
							System.out.println("hai preso l'oggetto "+prendi.toString());
							return;
						}else {
							this.partita.getStanzaCorrente().addAttrezzo(this.partita.removeAttrezzo(nomeAttrezzo));
							System.out.println("errore l'oggetto non è stato preso");
							}
					}else
						System.out.println("errore impossibile prendere attrezzo");
			}else
    			System.out.println("non esiste questo attrezzo "+nomeAttrezzo);

		}

	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}