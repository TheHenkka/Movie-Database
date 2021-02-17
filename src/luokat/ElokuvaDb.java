package luokat;

import java.util.Collection;

/**
 * P��luokka joka k�skee muita 
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class ElokuvaDb {

	private final Henkilot henkilot = new Henkilot();
	private final Elokuvat elokuvat = new Elokuvat(); 
	private final Roolit roolit = new Roolit(); 
	private final ElokuvaRoolit elokuvaRoolit = new ElokuvaRoolit();


	/**
	 * Oletusmuodostaja
	 */
	public ElokuvaDb() {
		// 
	}


	/**
	* @return henkil�iden lukum��r�
	*/
	public int getHenkilot() {
		return henkilot.getLkm();
	}
	
	
	/**
	 * Etsit��n halutun elokuvan n�yttelij�t ja annetaan niiden id:t
	 * @param nro Elokuvan id
	 * @param id N�yttelij� vai ohjaaja
	 * @return kaikkien elokuvan n�yttelij�iden idt
	 */
	public Collection<Integer> etsiNayttelijat(int nro, int id) {
		return elokuvaRoolit.etsiNayttelijat(nro, id);
	}
	
	
	/**
	 * Etsit��n elokuva nimen perusteella ja palautetaan viite siihen jos l�ytyi
	 * @param nimi nimi leffa nimi joka halutaan
	 * @return palauttaa halutun elokuvan
	 * @throws IndexOutOfBoundsException jos i on liian iso
	 */
	public Elokuva annaElokuva(String nimi) throws IndexOutOfBoundsException {
		return elokuvat.annaElokuva(nimi);
	}
	
	
	/**
	 * Etsit��n henkil� id:n perusteella ja palautetaan henkil�n nimi
	 * @param id Henkil�n id
	 * @return palauttaa henkil�n nimen
	 */
	public String etsiHenkilo(int id) {
		return henkilot.etsiHenkilo(id);
	}
	
	
	/**
	 * Poistetaan elokuva
	 * @param id Poistettavan leffan id
	 */
	public void poistaElokuva(int id){
		elokuvat.poista(id);
	}
	
	
	/**
	 * Poistetaan henkil�
	 * @param henkilo Poistettavan henkil�n id
	 */
	public void poistaHenkilo(Henkilo henkilo){
		henkilot.poista(henkilo);
	}
	
	
	/**
	 * Poistetaan annetun elokuvan kaikki roolit
	 * @param id Elokuvan id jonka roolit poistetaan
	 */
	public void poistaNayttelijaElokuvasta(int id){
		elokuvaRoolit.poista(id);
	}
	
	
	/**
	 * M��r�t��n tietty henkil� tiettyyn rooliin tiettyyn elokuvaan 
	 * @param mita sis�lt�� elokuvan, henkil�n ja roolin numeron t�ss� j�rjestyksess�
	 */
	public void lisaa(ElokuvaRooli mita) {
		elokuvaRoolit.lisaa(mita);
	}


	/**
	 * Tehd��n ensin elokuvarooli annetuilla arvoilla ja sitten lis�t��n se
	 * @param elokuvanNro Elokuvan ID
	 * @param henkilonId Henkil�n ID
	 * @param i Rooli ID
	 */
	public void lisaa(int elokuvanNro, int henkilonId, int i) {
		elokuvaRoolit.lisaa(elokuvanNro, henkilonId, i);
	}
	

	/**
	 * Etsit��n elokuvia
	 * @param hakuehto hakuehto
	 * @return Palauttaa l�ydetyt
	 */
	public Collection<Elokuva> etsiElokuvia(String hakuehto) {
		return elokuvat.etsi(hakuehto);
	}
	
	
	/**
	 * Etsit��n henki�it�
	 * @param hakuehto hakuehto
	 * @return Palauttaa l�ydetyt
	 */
	public Collection<Henkilo> etsiHenkiloita(String hakuehto) {
		return henkilot.etsi(hakuehto);
	}
	
	
	/**
	 * @return elokuvien m��r�
	 */
	public int getElokuvat() {
		return elokuvat.getLkm();
	}
	
	
	/**
	 * Lis�� uuden henkil�n 
	 * @param henkilo Henkil� joka lis�t��n 
	 */
	public void lisaa(Henkilo henkilo){
		henkilot.lisaa(henkilo);
	}
	
	
	/**
	 * Lis�� uuden elokuvan
	 * @param leffa Elokuva joka lis�t��n
	 * @throws TilaException Jos ei mahdu
	 */
	public void lisaa(Elokuva leffa) throws TilaException{
		elokuvat.lisaa(leffa);
	}
	
	
	/**
	 * Tehd��n ensin elokuva annetuilla arvoilla ja sitten lis�t��n se 
	 * @param elokuvanNimi nimi
	 * @param vuosi vuosi
	 * @param tahdet tahdet
	 * @param juoni juoni
	 * @throws TilaException Jos ei mahdu
	 */
	public void lisaa(String elokuvanNimi, int vuosi, int tahdet, String juoni) throws TilaException {
		elokuvat.lisaa(elokuvanNimi, vuosi, tahdet, juoni);
		
	}


	/**
	 * Luodaan ensin henkil� annetulla nimell� ja sitten lis�t��n se
	 * @param nimi Henkil�n nimi
	 */
	public void lisaaHenkilo(String nimi) {
		henkilot.lisaa(nimi);
	}
	

	/**
	 * Lis�� uuden roolin. esim n�yttelij�
	 * @param rooli Roolivaihtoehto joka lis�t��n
	 */
	public void lisaa(Rooli rooli) {
		roolit.lisaa(rooli);		
	}


	/**
	 * Lukee kaikki tiedostot
	 */
	public void lueTiedostot() {
		try {
			elokuvaRoolit.lueTiedostosta();
			elokuvat.lueTiedostosta();
			roolit.lueTiedostosta();
			henkilot.lueTiedostosta();
		} catch (TilaException e) {
			System.err.println("Ei voitu lukea. "+e);
		}
	}
	
	
	/**
	 * Tallennetaan kaikki tiedostot
	 */
	public void talleta() {
		try {
			elokuvaRoolit.talleta();
			elokuvat.talleta();
			henkilot.talleta();
			roolit.talleta();
		} catch (TilaException e) {
			System.err.println("Jotain meni pieleen. H�h�h�h�� "+e);
		}
	}
	
	
	/**
	 * @param args ei k�yt
	 */
	public static void main(String[] args) {
		ElokuvaDb elokuvaDb= new ElokuvaDb();
		
		Henkilo jonne= new Henkilo("Jonne");
		Henkilo janne= new Henkilo("Janna");
		Henkilo onni= new Henkilo("Onni");
		Henkilo jone= new Henkilo("Jone");
		
		jonne.numeroi();
		janne.numeroi();
		onni.numeroi();
		jone.numeroi();
		
		elokuvaDb.lisaa(jonne);
		elokuvaDb.lisaa(janne);
		elokuvaDb.lisaa(onni);
		elokuvaDb.lisaa(jone);
		
		Elokuva leffa= new Elokuva();
		Elokuva filmi= new Elokuva();
		
		leffa.esimTulosta();
		leffa.numeroi();
		filmi.esimTulosta();
		filmi.numeroi();
		
		try {
			elokuvaDb.lisaa(leffa);
			elokuvaDb.lisaa(filmi);
		} catch (TilaException e) {
			System.err.println("Jotain meni pieleen. H�h�h�h�� "+e);
		}
		
		Rooli nayttelija= new Rooli();
		Rooli ohjaaja= new Rooli();
		
		nayttelija.roolita();
		ohjaaja.roolita();
		
		elokuvaDb.lisaa(nayttelija);
		elokuvaDb.lisaa(ohjaaja);
		
		ElokuvaRooli XXX = new ElokuvaRooli(leffa.getElokuvanNro(),jonne.getHenkilonId(), ohjaaja.getRooliId());
		ElokuvaRooli YYY = new ElokuvaRooli(leffa.getElokuvanNro(),jone.getHenkilonId(), nayttelija.getRooliId());
		
		elokuvaDb.lisaa(XXX);
		elokuvaDb.lisaa(YYY);
		
		elokuvaDb.talleta();
		
		System.out.println("Elokuvia on "+ elokuvaDb.getElokuvat());
		System.out.println("Henkil�it� on "+ elokuvaDb.getHenkilot());
		System.out.println(XXX);
		System.out.println(YYY);
	}
}