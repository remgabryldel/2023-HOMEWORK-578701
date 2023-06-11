package it.uniroma3.diadia.personaggi;

import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_PARTICOLARE = ":'non ci sono stanze adiacenti, rimani qui'.";
	private static final String MESSAGGIO_POSITIVO=":'Mi hai salutato, spero che ti sia di utilità'";
	private static final String MESSAGGIO_NEGATIVO = ":'Non mi hai salutato, complimenti maleducato eccoti la sopresa che ti riserva'";
	private static final String MESSAGGIO_REGALO = "Pensavi di fare il ruffiano con me! ahahahah";
	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		Map<Direzione,Stanza> str2stz = partita.getStanzaCorrente().getMapStanzeAdiacenti();
		if(str2stz.size() == 0)
			return this.getNome() + MESSAGGIO_PARTICOLARE;
		if(this.haSalutato()) {
			Stanza maxAtt = str2stz.values().iterator().next();
			for(Stanza s : str2stz.values()) 
				if(s.getNumeroAttrezzi()>maxAtt.getNumeroAttrezzi()) 
					maxAtt = s;
			return this.getNome()+MESSAGGIO_POSITIVO;
		}else {
			Stanza minAtt = str2stz.values().iterator().next();
			for(Stanza s : str2stz.values())
				if(s.getNumeroAttrezzi()<minAtt.getNumeroAttrezzi())
					minAtt = s;
			return this.getNome()+MESSAGGIO_NEGATIVO;
		}
		
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo==null)
			return "Cosa volevi regalarmi?";
		partita.getGiocatore().removeAttrezzo(attrezzo.getNome());
		return MESSAGGIO_REGALO;
	}

}
