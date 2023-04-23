package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendi implements Comando {
 private String nomeAttrezzo;
 private IO InOut = new IOConsole();
 
     /**
	 * Cerca di prendere un oggetto. Se c'e' un oggetto lo inserisce in borsa
	 * e ne stampa l'evento altrimenti mostra una lista dei suddetti presenti nella stanza,
	 *  altrimenti se non riesce a prendere l'oggetto citato ne stampa l'errore
	 */
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(nomeAttrezzo==null) {
			int numeroAttrezzi = stanzaCorrente.getNumeroAttrezzi();
			if(numeroAttrezzi == 0  ) {
    			InOut.mostraMessaggio("non vi sono attrezzi");
                return;
            }
			InOut.mostraMessaggio("quale attrezzo vuoi prendere?");
			StringBuilder risultato = new StringBuilder();
			for (int i = 0; i<numeroAttrezzi ;i++) {
				Attrezzo attrezzo = stanzaCorrente.getAttrezzi()[i];
				if(attrezzo!=null)
					risultato.append(attrezzo.toString()+" ");
			}
			InOut.mostraMessaggio(risultato.toString());
			return;
		}else {
			if(stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo prendi =stanzaCorrente.getAttrezzo(nomeAttrezzo);
					Giocatore giocatore = partita.getGiocatore();
					if(giocatore.addAttrezzo(prendi)) {
						if(stanzaCorrente.removeAttrezzo(nomeAttrezzo)) {
							InOut.mostraMessaggio("hai preso l'oggetto "+prendi.toString());
							return;
						}else {
							stanzaCorrente.addAttrezzo(giocatore.removeAttrezzo(nomeAttrezzo));
							InOut.mostraMessaggio("errore l'oggetto non è stato preso");
					    }
					}else
						InOut.mostraMessaggio("errore impossibile prendere attrezzo");
			}else
				InOut.mostraMessaggio("non esiste questo attrezzo "+nomeAttrezzo);
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
