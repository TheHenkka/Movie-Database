package luokat;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Määrittää henkilön roolin elokuvassa
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class ElokuvaRooli {

	private int leffaId;
	private int henkiloId;
	private int rooliId;
	
	/**
	 * Muodostoja joka käyttää annettuja arvoja
	 * @param leffaId Leffan numero
	 * @param henkiloId Henkilön numero
	 * @param rooliId Roolin numero
	 * 
	 */
	public ElokuvaRooli(int leffaId, int henkiloId, int rooliId){
		this.leffaId= leffaId;
		this.henkiloId= henkiloId;
		this.rooliId= rooliId;
	}


	/**
	 * Oletusmuodostaja
	 */
	public ElokuvaRooli() {
		// 
	}

	/**
	 * Tarkistaa onko annetut samat
	* @example
    * <pre name="test">
    * ElokuvaRooli leffa1 = new ElokuvaRooli(1,2,2);
	* ElokuvaRooli filmi1 = new ElokuvaRooli(1,1,1);
	* ElokuvaRooli elokuva1 = new ElokuvaRooli(1,1,1);
    *   
    *   leffa1.equals(filmi1) === false;
    *   filmi1.equals(elokuva1) === true;
    *   elokuva1.equals(filmi1) === true;
    *   filmi1.equals(leffa1) === false;
    * </pre>
    */
	@Override
    public boolean equals(Object elokuvarooli) {
        return this.toString().equals(elokuvarooli.toString());
    }
	
	
	@Override
	public int hashCode() {
		return leffaId;
	}
	

	@Override
	public String toString() {
		return "" + leffaId   +"|"+
				    henkiloId +"|"+
				    rooliId;  
	}

	
	/**
	 * Selvittää tiedot ja antaa elokuvaroolille arvot
	 * @param string Rivi jolla on elokuvaroolien tiedot muodossa 1|2|1
	 */
	public void parse(String string) {
		StringBuilder a=new StringBuilder(string);
		 this.leffaId= 		Integer.valueOf(Mjonot.erota(a, '|'));
		 this.henkiloId=	Integer.valueOf(Mjonot.erota(a, '|'));
		 this.rooliId=		Integer.valueOf(Mjonot.erota(a, '|'));
	}
	
	
	/**
	 * Tulostetaan tiedot
	 * @param out mihin tulostetaan
	 */
	public void tulosta(PrintStream out) {
		out.println("Leffan nimi: "+ String.format("%03d", leffaId,3) +", Henkilön nimi: "+ henkiloId + " Rooli; " + rooliId);		
	}

	
	/**
	 * @param args eip
	 */
	public static void main(String[] args) {
		ElokuvaRooli joku= new ElokuvaRooli(1, 2,3);
		joku.tulosta(System.out);
	}
}