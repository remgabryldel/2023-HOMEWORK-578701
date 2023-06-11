package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_INTERAZIONE = ":'bau bau'(il cane ti morde, hai perso 1 cfu)";
	private static String CIBO_PREFERITO= "osso";

	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		partita.setCfu(partita.getCfu()-1);
		return this.getNome()+MESSAGGIO_INTERAZIONE;	
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo==null)
			return "Cosa volevi regalarmi?";
		StringBuilder risposta = new StringBuilder("Bau grazie per avermi regalato ");
		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			risposta.append("il mio cibo preferito.");
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("collare", 2));
		} else {
			risposta.append(attrezzo.getNome()+",ma non e' il mio cibo preferito, bau!");
			risposta.append(this.agisci(partita));
		}
		return risposta.toString();
	}

}
