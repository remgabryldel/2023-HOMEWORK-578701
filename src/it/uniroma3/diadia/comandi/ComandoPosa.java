package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPosa extends AbstractComando {
	private String nomeAttrezzo;
	static final private String NOME = "ComandoPosa";
	 
	     /**
		 * Cerca di posare un oggetto. Se c'e l'oggetto in  borsa viene rilasciato nella stanza corrente,
		 * ne stampa l'evento altrimenti mostra una lista dei suddetti presenti in borsa,
		 *  altrimenti se non riesce a gettare l'oggetto citato ne stampa l'errore
		 */
	@Override
	public void esegui(Partita partita,IO io) {
		super.setIo(io);
		Giocatore giocatore = partita.getGiocatore();
		if(nomeAttrezzo==null) {
            if(giocatore.getNumeroAttrezzi() == 0  ) {
            	super.getIo().mostraMessaggio("non vi sono attrezzi nella borsa");
                return;
            }
            super.getIo().mostraMessaggio("quale attrezzo vuoi posare?");
            super.getIo().mostraMessaggio(giocatore.mostraInventario());
			return;
		}else {
			if(giocatore.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo getta = giocatore.removeAttrezzo(nomeAttrezzo);
					if(!giocatore.hasAttrezzo(nomeAttrezzo)) {
						Stanza stanzaCorrente = partita.getStanzaCorrente();
						if(stanzaCorrente.addAttrezzo(getta)) {
							super.getIo().mostraMessaggio("hai posato l'oggetto "+getta.toString());
							return;
						}else {
							giocatore.addAttrezzo(getta);
							super.getIo().mostraMessaggio("errore l'attrezzo non è stato posato");
							}
					}else
						super.getIo().mostraMessaggio("errore impossibile posare attrezzo");
			}else
				super.getIo().mostraMessaggio("non hai questo attrezzo "+nomeAttrezzo);
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
