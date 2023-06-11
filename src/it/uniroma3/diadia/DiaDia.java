package it.uniroma3.diadia;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 * @author  docente di POO (da un'idea di Michael Kolling and David J. Barnes) 
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

	private Partita partita;
	private IO InOut;

	public DiaDia(IO io, Labirinto labirinto) {
		this.partita = new Partita(labirinto);
		this.InOut = io;
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
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
				comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita,this.InOut); 
		if (this.partita.vinta())
			InOut.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			InOut.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}   

	public static void main(String[] argc) throws FileNotFoundException, FormatoFileNonValidoException {
		IO io = new IOConsole();
		Labirinto labirinto =  Labirinto.newBuilder("labirinto5.txt").getLabirinto();
				new LabirintoBuilder()
				 .addStanzaBloccata("Atrio",Direzione.nord,"chiave")
				 .addAttrezzo("osso", 4)
				 .addStanzaIniziale("Atrio")
				 .addStanza("Aula N11")
				 .addStanzaMagica("Aula N10",2)
				 .addAttrezzo("lanterna",3)
				 .addStanza("Laboratorio Campus")
				 .addStanzaVincente("biblioteca")
				 .addStanzaBuia("Ripostiglio","lanterna")
				 .addAttrezzo("chiave", 1)
				 
				 /* collegamento Atrio-Biblioteca */
				 .addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				 .addAdiacenza( "Biblioteca","Atrio", Direzione.sud)
				 /* collegamento Atrio-N10 */
				 .addAdiacenza("Atrio", "Aula N10", Direzione.sud)
				 .addAdiacenza( "Aula N10","Atrio", Direzione.nord)
				 /* collegamento Atrio-N11 */
				 .addAdiacenza("Atrio", "Aula N11", Direzione.est)
				 .addAdiacenza( "Aula N11", "Atrio", Direzione.ovest)
				 /* collegamento Atrio-Campus */
				 .addAdiacenza("Atrio", "Laboratorio Campus", Direzione.ovest)
				 .addAdiacenza( "Laboratorio Campus","Atrio",Direzione.est)
				 /* collegamento N11-Campus */
				 .addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.est)
				 .addAdiacenza( "Laboratorio Campus","Aula N11", Direzione.ovest)
				 /* collegamento Campus-Ripostiglio */
				 .addAdiacenza("Laboratorio Campus", "Ripostiglio", Direzione.sud)
				 .addAdiacenza( "Ripostiglio","Laboratorio Campus", Direzione.nord)

				 .getLabirinto();
	    DiaDia gioco = new DiaDia(io,labirinto);

		gioco.gioca();
	}
}