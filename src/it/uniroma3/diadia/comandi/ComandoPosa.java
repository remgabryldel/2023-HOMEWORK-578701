package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa implements Comando {
	private String nomeAttrezzo;
	 private IO InOut = new IOConsole();
	 
	     /**
		 * Cerca di posare un oggetto. Se c'e l'oggetto in  borsa viene rilasciato nella stanza corrente,
		 * ne stampa l'evento altrimenti mostra una lista dei suddetti presenti in borsa,
		 *  altrimenti se non riesce a gettare l'oggetto citato ne stampa l'errore
		 */
	@Override
	public void esegui(Partita partita) {
		Giocatore giocatore = partita.getGiocatore();
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
						Stanza stanzaCorrente = partita.getStanzaCorrente();
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

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
