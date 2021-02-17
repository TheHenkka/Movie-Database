package luokat;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka joka osaa lisätä ja poistaa leffoja
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class Elokuva {
	
	private int 	elokuvanId;
	private String 	elokuvanNimi 	= "";
	private int 	vuosi			= 0;
	private int 	tahdet 			= 0;
	private String 	juoni 			= "";
	private static  int  seuraavaNro = 1;

	
	/**
	 * @param nimi nimi
	 * @param v vuosi
	 * @param t tähdet
	 * @param j juoni
	 */
	public Elokuva(String nimi, int v, int t, String j) {
		this.elokuvanNimi =nimi;
		this.vuosi =v;
		this.tahdet = t;
		this.juoni = j;
	}


	/**
	 * Oletusmuodostaja
	 */
	public Elokuva() {
		//  
	}


	/**
	 * Tulostetaan elokuva järkevässä muodossa
	 * @param out mihin tulostetaan
	 */
	public void tulosta(PrintStream out){
		out.println("Numero: "+ String.format("%03d", elokuvanId,3) +", Elokuvan nimi: "+elokuvanNimi+","
					+ " Vuosi: "+vuosi+", Tähdet: "+tahdet+", Juoni: "+juoni );
	}
	
	
	/**
	 * Annetaan leffalle numero
	 * @return elokuvan numero
	 * @example
	 * <pre name="test">
	 * Elokuva leffa= new Elokuva();
	 * leffa.numeroi();
	 * int a = leffa.getElokuvanNro();
	 * leffa.getElokuvanNro()===1;
	 * 
	 * Elokuva leffa2= new Elokuva();
	 * leffa2.numeroi();
	 * int b = leffa2.getElokuvanNro();
	 * leffa2.getElokuvanNro()===2;
	 * a === b-1;
	 * </pre>
	 */
	public int numeroi(){
		 return setId(seuraavaNro);
	}
	
	
	/**
	* Palauttaa leffan numeron.
	* @return leffan numero
	*/
	public int getElokuvanNro() {
		return elokuvanId;
	}

	

     /**
      * Asettaa id:n ja samalla varmistaa että seuraava numero on suurempi kuin tähän mennessä suurin. (käytetään silloin kun luetaan tiedosto)
      * @param nr asetettava tunnusnumero
     * @return leffan numero
      */
     public int setId(int nr) {
    	 this.elokuvanId = nr;
         if (elokuvanId >= seuraavaNro) seuraavaNro = elokuvanId + 1;
         return elokuvanId;
     }
	
	
	/**
	 * Palauttaa leffan nimen
	 * @return leffan nimi
	 */
	public String getNimi() {
		return elokuvanNimi;
	}
	

	/**
	 * Oikeestaan vaan testejä varten
	 * @param nimi nimi joka halutaan antaa leffalle
	 */
	public void nimea(String nimi){
		elokuvanNimi= nimi;
	}
	
	
	/**
	 *  Tarkistaa onko annetut samat
	* @example
    * <pre name="test">
    *   Elokuva leffa1 = new Elokuva(); leffa1.parse("1|Die Hard|1982|5|Mahtava!"); 
    *   Elokuva leffa2 = new Elokuva(); leffa2.parse("2|Deep Throat|1972|5|Wink Wink ;)"); 
    *   Elokuva leffa3 = new Elokuva(); leffa3.parse("3|DieHasr|1911|3|öjsjhfgsdf dsf "); 
    *   Elokuva leffa4 = new Elokuva(); leffa4.parse("3|DieHasr|1911|3|öjsjhfgsdf dsf ");
    *   
    *   leffa1.equals(leffa2) === false;
    *   leffa2.equals(leffa2) === true;
    *   leffa3.equals(leffa4) === true;
    *   leffa4.equals(leffa1) === false;
    * </pre>
    */
	@Override
     public boolean equals(Object elokuva) {
         return this.toString().equals(elokuva.toString());
     }
	
	
	/**
	 * Otetaan elokuvan tiedot ja asetetaan ne elokuvalle
	 * @param string Rivi jolla on elokuvan tiedot muodossa 1|Rambo|1983|4|Blaa blaa blaah
	 */
	public void parse(String string) {
		StringBuilder a=new StringBuilder(string);
		 setId(Integer.valueOf(Mjonot.erota(a, '|')));
		 this.elokuvanNimi= Mjonot.erota(a, '|');
		 this.vuosi= 		Integer.valueOf(Mjonot.erota(a, '|'));
		 this.tahdet= 		Integer.valueOf(Mjonot.erota(a, '|'));	
		 this.juoni=		Mjonot.erota(a, '|');
	}
	
	
	@Override
	public String toString() {
		return "" + elokuvanId +"|"+ elokuvanNimi +"|"+ vuosi +"|"+ tahdet 	+"|"+ juoni;
	}
	
	
	/**
	 * esimerkkitulostus testejä varten
	 */
	public void esimTulosta(){
		elokuvanNimi="Deep Throat";
		vuosi= 1972;
		tahdet=5;
		juoni= "Wink Wink ;)";
	}
	
	
	@Override
	public int hashCode() {
		return elokuvanId;
	}


	/**
	 * @param args ei käyt
	 */
	public static void main(String[] args) {
		Elokuva leffa= new Elokuva();
		Elokuva filmi= new Elokuva();
		Elokuva elokuva= new Elokuva("Hobotti" ,2015,5 ,"dibodoa" );
		
		leffa.esimTulosta();
		leffa.numeroi();
		leffa.tulosta(System.out);
		System.out.println(leffa.getElokuvanNro());
		
		elokuva.numeroi();
		elokuva.tulosta(System.out);
		System.out.println(elokuva.getElokuvanNro());
		
		filmi.esimTulosta();
		filmi.numeroi();
		filmi.tulosta(System.out);
	}
}