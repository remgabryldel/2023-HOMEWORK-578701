package it.uniroma3.diadia;
import java.util.*;

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
		this.indiceIstruzione++;
		return this.getIstruzione(indiceIstruzione);
	}
	
	public String getIstruzione(int indice) {
		this.indiceIstruzione = indice;
		return listaInput.get(indice);
	}
	
	public void setIndiceIstruzione(int indice) {
		this.indiceIstruzione = indice;
	}

	public boolean hasNextIstruzione() {
		return listaInput.containsKey(this.indiceIstruzione++);
	}

	public String nextIstruzione() {
		return listaInput.get(this.indiceIstruzione++);
	}
	
	public List<String> getListaOutput(){
		Collections.reverse(this.listaOutput);
		return this.listaOutput;
	}
	
	public static IOSimulator IOSimulatorMonolocale(Map<Integer,String> listaIstruzioni, IOSimulator io) {
		IOSimulator iosMono = io;
		Labirinto labirinto = new LabirintoBuilder()
				 .addStanzaIniziale("Atrio")
				 .addAttrezzo("osso", 5)
				 .addStanzaVincente("Biblioteca")
				 .addAdiacenza("Atrio", "Biblioteca", "nord")
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
				 .addAdiacenza("Atrio", "Biblioteca", "nord")
				 .addAdiacenza("Atrio", "Aula N10", "est")
				 .getLabirinto();
		DiaDia gioco = new DiaDia(iosBil, labirinto);
		gioco.gioca();
		return iosBil;
	}
	
	public static IOSimulator IOSimulatorCompleto(Map<Integer,String> listaIstruzioni, IOSimulator io) {
		IOSimulator ioSCom = io;
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaBloccata("Atrio","nord","chiave")
				 .addAttrezzo("osso", 4)
				 .addStanzaIniziale("Atrio")
				 .addStanza("Aula N11")
				 .addStanzaMagica("Aula N10",2)
				 .addAttrezzo("lanterna",3)
				 .addStanza("Laboratorio Campus")
				 .addStanzaVincente("biblioteca")
				 .addStanzaBuia("Ripostiglio","lanterna")
				 .addAttrezzo("Chiave", 1)
				 .addAdiacenza("Atrio", "Biblioteca", "nord")
				 .addAdiacenza("Atrio", "Aula N10", "sud")
				 .addAdiacenza("Atrio", "Aula N11", "est")
				 .addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				 .addAdiacenza("Aula N11", "Laboratorio Campus", "est")
				 .addAdiacenza("Aula N10", "Aula N11", "est")
				 .addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
				 .addAdiacenza("Laboratorio Campus", "Ripostiglio", "sud")
				 .getLabirinto();
		DiaDia gioco = new DiaDia(ioSCom, labirinto);
		gioco.gioca();
		return ioSCom;
	}

}
