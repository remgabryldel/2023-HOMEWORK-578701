package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;

public class ComandoGuarda implements Comando {
//TODO ricordati di inserire dei comandi per mostrare in output 
// le strutture ordinate dalle varie collezioni di attrezzi
	private IO InOut = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
        StringBuilder guarda = new StringBuilder();
        guarda.append("ti trovi nella stanza:\n "+partita.getStanzaCorrente().getDescrizione()+"\n");
        guarda.append("attualmente sei in gioco, con "+partita.getCfu()+" cfu rimanenti.\n");
        guarda.append("il tuo inventario è composto da :\n"+partita.getGiocatore().mostraInventario());
        InOut.mostraMessaggio(guarda.toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
