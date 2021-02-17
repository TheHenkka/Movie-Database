package luokat;

import fi.jyu.mit.ohj2.Mjonot;

import java.io.PrintStream;

/**
 * Luokka joka osaa lis�t� ja poistaa henkil�it�
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class Henkilo {

	private int 		henkilonId;
	private String 		henkilonNimi	="";
	private static int  seuraavaNro		= 1;
	
	
	/**
	 * Luodaan uusi henkil�, jolle annetaan annettu nimi
	 * @param nimi henkil�n nimi
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
	 * Tulostaa henkil�n tiedot
	 * @param out mihin tulostetaan
	 */
	public void tulosta(PrintStream out){
		out.println("Henkil�n ID: "+ String.format("%03d", henkilonId,3) +", Henkil�n nimi: "+henkilonNimi);
	}
	
	
	@Override
	public String toString() {
		return "" + henkilonId	+ "|" + henkilonNimi;		
	}
	
	
	/**
     * Asettaa id:n ja samalla varmistaa ett�
     * seuraava numero on aina suurempi kuin t�h�n menness� suurin. (k�ytet��n silloin kun luetaan tiedosto)
     * @param nr asetettava tunnusnumero
	 * @return henkil�n ID
     */
    public int setId(int nr) {
    	henkilonId = nr;
        if (henkilonId >= seuraavaNro) seuraavaNro = henkilonId + 1;
        return henkilonId;
    }
	
	
	/**
	* Palauttaa henkil�n numeron.
	* @return henkil�n numero
	*/
	public int getHenkilonId() {
		return henkilonId;
	}
	
	
	/**
	 * @return Palauttaa henkil�n nimen
	 */
	public String getHenkilonNimi() {
		return henkilonNimi;
	}
	
	
	/**
	 * Annetaan henkil�lle numero
	 * @return henkil�n numero
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
	 * annetaan henkil�lle jotkut arvot, tulevaisuudessa turha
	 */
	public void lokeroi(){
		henkilonNimi= "Uusi N�yttelij�";
	}

	
	/**
	 * Selvitt�� tiedot ja antaa henkil�lle arvot
	 * @param string Rivi jolla on henkil�n tiedot muodossa 1|Hentter Eloranta
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
	 * @param args ei k�yt�ss�
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