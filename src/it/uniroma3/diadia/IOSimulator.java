package it.uniroma3.diadia;

public class IOSimulator implements IO {

	private String[] istructions={
			  "aiuto","guarda","vai nord", "vai sud", "vai est","vai ovest","vai","prendi osso",
			  "prendi lanterna","prendi chiave","prendi eivahc","posa lanterna","posa osso",
			  "posa eivahc","posa chiave"
		};
	private int[] combinazione = {2,7,3,12,7,7,12,1,8,2,5,3,11,1,9,2,4,14,10,2,2,13,2,10,3,13,9,2,14,1,2};
	private int iteratore=0;
	private String messages="";
	
	@Override
	public void mostraMessaggio(String messaggio) {
           StringBuilder build = new StringBuilder(this.messages);
           build.append("\n"+messaggio+"\n");
           this.messages = build.toString();
           System.out.println("\n"+messaggio);
	}

	@Override
	public String leggiRiga() {
		String in = istructions[combinazione[iteratore]];
		this.messages=this.messages+in+"\n";
        System.out.println("\n"+in+"\n");
		iteratore++;
		return in;
	}

}
