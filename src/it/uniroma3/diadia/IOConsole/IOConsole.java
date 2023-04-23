package it.uniroma3.diadia.IOConsole;
import java.util.Scanner;

import it.uniroma3.diadia.IO;

/**Classe che centralizza l’accesso a System.out/System.in
*
*
* @author  docente di POO 
* 
*          
*@version base
*/
public class IOConsole implements IO {
	/**
	 * questo metodo implementa l'output
	 * @param una stringa da mostrare in output
	 */
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * questo metodo implementa la lettura in input dei comandi e implementa la lettura
	 * di più dati richiamando piu volte si ottengono piu dati in input
	 * @return una stringa letta da input
	 */
	@Override
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}
