package luokat;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka joka osaa lisätä ja poistaa roolivaihtoehtoja
 * @author Opseli
 * @version 14.5.2015
 */
public class Rooli {

	private int 		rooliId		= 0;
	private static int 	seuraavaNro	= 1;
	private String 		rooli		= "";
	
	
	/**
	 * Annetaan roolivaihtoehdolle numero
	 * @return roolivaihtoehdon numero
	 * @example
	 * <pre name="test">
	 * Rooli nayttelija = new Rooli();
	 * nayttelija.roolita();
	 * int a = nayttelija.getRooliId();
	 * nayttelija.getRooliId()===1;
	 * 
	 * Rooli nayttelija1 = new Rooli();
	 * nayttelija1.roolita();
	 * int b = nayttelija1.getRooliId();
	 * nayttelija1.getRooliId()===2;
	 * a === b-1;
	 * </pre>
	 */
	public int numeroi(){
		return setId(seuraavaNro);
	}
	
	
	/**
	 * @return palauttaa roolivaihtoehdon id:n
	 */
	public int getRooliId(){
		return rooliId;
	}
	
	
	@Override
    public boolean equals(Object roolinNimi) {
        return this.toString().equals(roolinNimi.toString());
    }
	
	
	@Override
	public int hashCode() {
		return rooliId;
	}
	
	
	/**
	 * @return palauttaa roolivaihtoehdon
	 */
	public String getRooliVaihtoehto() {
		return rooli;
	}
	
	
	/**
	 * 
	 */
	public void roolita(){
		rooliId= numeroi();
		if (getRooliId()==1){rooli= "Näyttelijä"; return;}
		if (getRooliId()==2){rooli= "Ohjaaja"; return;}
		rooli = "...";
	}
	
	
	/**
	 * @param out mihin tulostetaan
	 */
	public void tulosta(PrintStream out){
		out.println("Rooli ID: "+ String.format("%03d", rooliId,3) + ", Henkilön rooli: " + rooli);
	}

	
	@Override
	public String toString() {
		return "" + rooliId + "|" + rooli;
	}
	
	
	/**
     * Asettaa id:n ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin. (käytetään silloin kun luetaan tiedosto)
     * @param nr asetettava tunnusnumero
	 * @return roolin id
     */
    public int setId(int nr) {
    	rooliId = nr;
        if (rooliId >= seuraavaNro) seuraavaNro = rooliId + 1;
		return rooliId;
    }
	

	/**
	 * 
	 * @param string Rivi jolla on  roolitiedot muodossa 1|Näyttelijä
	 */
	public void parse(String string) {
		StringBuilder a=new StringBuilder(string);
		setId(Integer.valueOf(Mjonot.erota(a, '|')));
		 this.rooli= Mjonot.erota(a, '|');
	}
	
	
	/**
	 * @param args ei käyt
	 */
	public static void main(String[] args) {
		Rooli nayttelija = new Rooli();
		nayttelija.roolita();
		nayttelija.tulosta(System.out);
		
		Rooli ohjaaja= new Rooli();
		ohjaaja.roolita();
		ohjaaja.tulosta(System.out);
		System.out.println(ohjaaja.getRooliVaihtoehto());
		System.out.println(ohjaaja.getRooliId());
	}
}