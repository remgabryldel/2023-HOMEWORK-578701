package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class ComandoPrendi extends AbstractComando {
    private String nomeAttrezzo;
	static final private String NOME = "ComandoPrendi";
 
     /**
	 * Cerca di prendere un oggetto. Se c'e' un oggetto lo inserisce in borsa
	 * e ne stampa l'evento altrimenti mostra una lista dei suddetti presenti nella stanza,
	 *  altrimenti se non riesce a prendere l'oggetto citato ne stampa l'errore
	 */
	@Override
	public void esegui(Partita partita, IO io) {
		super.setIo(io);
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if(nomeAttrezzo==null) {
			int numeroAttrezzi = stanzaCorrente.getNumeroAttrezzi();
			if(numeroAttrezzi == 0  ) {
				super.getIo().mostraMessaggio("non vi sono attrezzi");
                return;
            }
			super.getIo().mostraMessaggio("quale attrezzo vuoi prendere?");
			StringBuilder risultato = new StringBuilder();
			for (Attrezzo a: stanzaCorrente.getAttrezzi()) {
				if(a!=null)
					risultato.append(a.toString()+" ");
			}
			super.getIo().mostraMessaggio(risultato.toString());
			return;
		}else {
			if(stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
				Attrezzo prendi =stanzaCorrente.getAttrezzo(nomeAttrezzo);
					Giocatore giocatore = partita.getGiocatore();
					if(giocatore.addAttrezzo(prendi)) {
						if(stanzaCorrente.removeAttrezzo(nomeAttrezzo)) {
							super.getIo().mostraMessaggio("hai preso l'oggetto "+prendi.toString());
							return;
						}else {
							stanzaCorrente.addAttrezzo(giocatore.removeAttrezzo(nomeAttrezzo));
							super.getIo().mostraMessaggio("errore l'oggetto non è stato preso");
					    }
					}else
						super.getIo().mostraMessaggio("errore impossibile prendere attrezzo");
			}else
				super.getIo().mostraMessaggio("non esiste questo attrezzo "+nomeAttrezzo);
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
