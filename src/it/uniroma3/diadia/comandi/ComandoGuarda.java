package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
//TODO ricordati di inserire dei comandi per mostrare in output 
// le strutture ordinate dalle varie collezioni di attrezzi
	static final private String NOME = "ComandoGuarda";
	
	@Override
	public void esegui(Partita partita, IO inOut) {
		super.setIo(inOut);
		super.getIo().mostraMessaggio("ti trovi nella stanza:\n"+partita.getStanzaCorrente().getDescrizione());
		super.getIo().mostraMessaggio("\nattualmente sei in gioco, con "+partita.getCfu()+" cfu rimanenti.");
		super.getIo().mostraMessaggio("\nil tuo inventario è composto da:"+partita.getGiocatore().mostraInventario());
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
