package it.uniroma3.diaida.IOConsole;
import java.util.Scanner;

/**Classe che centralizza l’accesso a System.out/System.in
*
*
* @author  docente di POO 
* 
*          
*@version base
*/
public class IOConsole {
	/**
	 * questo metodo implementa l'output
	 * @param una stringa da mostrare in output
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * questo metodo implementa la lettura in input dei comandi e implementa la lettura
	 * di più dati richiamando piu volte si ottengono piu dati in input
	 * @return una stringa letta da input
	 */
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}
