package luokat;

import fi.jyu.mit.ohj2.Mjonot;

import java.io.PrintStream;

/**
 * Luokka joka osaa lisätä ja poistaa henkilöitä
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class Henkilo {

	private int 		henkilonId;
	private String 		henkilonNimi	="";
	private static int  seuraavaNro		= 1;
	
	
	/**
	 * Luodaan uusi henkilö, jolle annetaan annettu nimi
	 * @param nimi henkilön nimi
	 */
	public Henkilo(String nimi) {
		this.henkilonNimi = nimi;
	}


	/**
	 * oletusmuodostaja
	 */
	public Henkilo() {
		// 
	}


	/**
	 * Tulostaa henkilön tiedot
	 * @param out mihin tulostetaan
	 */
	public void tulosta(PrintStream out){
		out.println("Henkilön ID: "+ String.format("%03d", henkilonId,3) +", Henkilön nimi: "+henkilonNimi);
	}
	
	
	@Override
	public String toString() {
		return "" + henkilonId	+ "|" + henkilonNimi;		
	}
	
	
	/**
     * Asettaa id:n ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin. (käytetään silloin kun luetaan tiedosto)
     * @param nr asetettava tunnusnumero
	 * @return henkilön ID
     */
    public int setId(int nr) {
    	henkilonId = nr;
        if (henkilonId >= seuraavaNro) seuraavaNro = henkilonId + 1;
        return henkilonId;
    }
	
	
	/**
	* Palauttaa henkilön numeron.
	* @return henkilön numero
	*/
	public int getHenkilonId() {
		return henkilonId;
	}
	
	
	/**
	 * @return Palauttaa henkilön nimen
	 */
	public String getHenkilonNimi() {
		return henkilonNimi;
	}
	
	
	/**
	 * Annetaan henkilölle numero
	 * @return henkilön numero
	 * @example
	 * <pre name="test">
	 * Henkilo nicolas= new Henkilo();
	 * nicolas.numeroi();
	 * int a = nicolas.getHenkilonId();
	 * nicolas.getHenkilonId()===1;
	 * 
	 * Henkilo niko= new Henkilo();
	 * niko.numeroi();
	 * int b = niko.getHenkilonId();
	 * niko.getHenkilonId()===2;
	 * a === b-1;
	 * </pre>
	 */
	public int numeroi(){
		return setId(seuraavaNro);
	}
	
	/**
	* @example
    * <pre name="test">
    * Henkilo hnk1 = new Henkilo();  hnk1.parse("1|Kissa Kissanen");
	* Henkilo hnk2 = new Henkilo();  hnk2.parse("1|Kissa Kissanen");
	* Henkilo hnk3 = new Henkilo();  hnk3.parse("2|Kana Kananen");
    *   
    *   hnk1.equals(hnk2) === true;
    *   hnk2.equals(hnk1) === true;
    *   hnk2.equals(hnk3) === false;
    *   hnk3.equals(hnk2) === false;
    * </pre>
	 */
	@Override
     public boolean equals(Object henkilo) {
         return this.toString().equals(henkilo.toString());
     }
	
	
	/**
	 * annetaan henkilölle jotkut arvot, tulevaisuudessa turha
	 */
	public void lokeroi(){
		henkilonNimi= "Uusi Näyttelijä";
	}

	
	/**
	 * Selvittää tiedot ja antaa henkilölle arvot
	 * @param string Rivi jolla on henkilön tiedot muodossa 1|Hentter Eloranta
	 */
	public void parse(String string) {
		StringBuilder a=new StringBuilder(string);
		 setId(Integer.valueOf(Mjonot.erota(a, '|')));
		 this.henkilonNimi= Mjonot.erota(a, '|');
	}
	
	
	@Override
	public int hashCode() {
		return henkilonId;
	}


	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		
		Henkilo nicolas= new Henkilo();
		nicolas.numeroi();
		nicolas.lokeroi();
		nicolas.tulosta(System.out);
		System.out.println(nicolas.getHenkilonId());
		System.out.println(nicolas.toString());
		
		Henkilo nico= new Henkilo();
		nico.numeroi();
		nico.lokeroi();
		nico.tulosta(System.out);
	}
}