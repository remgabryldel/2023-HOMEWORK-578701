package it.uniroma3.diadia;
import java.util.*;

import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class IOSimulator implements IO {

	private Map<Integer, String> listaInput;
	private List<String> listaOutput;
	int indiceIstruzione;
	
	public IOSimulator(Map<Integer, String> listaIstruzioni) {
		this.indiceIstruzione = 0;
		this.listaInput = new TreeMap<>(listaIstruzioni);
		this.listaOutput = new ArrayList<>();
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		listaOutput.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		if(!this.hasIstruzione()) {
			new IOConsole().mostraMessaggio("hai dimenticato di far terminare la partita");
			return"fine";
		}
		String app = this.getIstruzione(indiceIstruzione);
		this.indiceIstruzione++;
        return app;
	}
	
	public String getIstruzione(int indice) {
		this.indiceIstruzione = indice;
		return listaInput.get(indice);
	}
	
	public void setIndiceIstruzione(int indice) {
		this.indiceIstruzione = indice;
	}

	public boolean hasIstruzione() {
		return listaInput.containsKey(this.indiceIstruzione);
	}
	
	public boolean hasNextIstruzione() {
		int i = this.indiceIstruzione+1;
		return listaInput.containsKey(i);
	}

	public String nextIstruzione() {
		int i = this.indiceIstruzione+1;
		return listaInput.get(i);
	}
	
	public List<String> getListaOutput(){
		//Collections.reverse(this.listaOutput);
		return this.listaOutput;
	}
	
	public Map<Integer,String>  getMapInput(){
		//Collections.reverse(this.listaOutput);
		return this.listaInput;
	}
	
	public static IOSimulator IOSimulatorMonolocale(Map<Integer,String> listaIstruzioni, IOSimulator io) {
		IOSimulator iosMono = io;
		Labirinto labirinto = new LabirintoBuilder()
				 .addStanzaIniziale("Atrio")
				 .addAttrezzo("osso", 5)
				 .addStanzaVincente("Biblioteca")
				 .addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				 .getLabirinto();
		DiaDia gioco = new DiaDia(iosMono, labirinto);
		gioco.gioca();
		return iosMono;
	}
	
	public static IOSimulator IOSimulatorBilocale(Map<Integer,String> listaIstruzioni, IOSimulator io) {
		IOSimulator iosBil = io;
		Labirinto labirinto = new LabirintoBuilder()
				 .addStanzaIniziale("Atrio")
				 .addAttrezzo("osso", 5)
				 .addStanza("Aula N10")
				 .addStanzaVincente("Biblioteca")
				 .addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
				 .addAdiacenza("Atrio", "Aula N10", Direzione.est)
				 .addAdiacenza( "Aula N10", "Atrio", Direzione.ovest)
				 .getLabirinto();
		DiaDia gioco = new DiaDia(iosBil, labirinto);
		gioco.gioca();
		return iosBil;
	}
	
	public static IOSimulator IOSimulatorCompleto(Map<Integer,String> listaIstruzioni, IOSimulator io) {
		IOSimulator ioSCom = io;
		Labirinto labirinto = new LabirintoBuilder()
				 .addStanzaBloccata("Atrio",Direzione.nord,"chiave")
				 .addAttrezzo("osso", 4)
				 .addStanzaIniziale("Atrio")
				 .addStanza("Aula N11")
				 .addStanzaMagica("Aula N10",2)
				 .addAttrezzo("lanterna",3)
				 .addStanza("Laboratorio Campus")
				 .addStanzaVincente("Biblioteca")
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
				 .addAdiacenza( "Laboratorio Campus","Atrio", Direzione.est)
				 /* collegamento N11-Campus */
				 .addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.est)
				 .addAdiacenza( "Laboratorio Campus","Aula N11", Direzione.ovest)
				 /* collegamento Campus-Ripostiglio */
				 .addAdiacenza("Laboratorio Campus", "Ripostiglio", Direzione.sud)
				 .addAdiacenza( "Ripostiglio","Laboratorio Campus", Direzione.nord)

				 .getLabirinto();
		DiaDia gioco = new DiaDia(ioSCom, labirinto);
		gioco.gioca();
		return ioSCom;
	}

}
