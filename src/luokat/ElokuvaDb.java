package luokat;

import java.util.Collection;

/**
 * Pääluokka joka käskee muita 
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
	* @return henkilöiden lukumäärä
	*/
	public int getHenkilot() {
		return henkilot.getLkm();
	}
	
	
	/**
	 * Etsitään halutun elokuvan näyttelijät ja annetaan niiden id:t
	 * @param nro Elokuvan id
	 * @param id Näyttelijä vai ohjaaja
	 * @return kaikkien elokuvan näyttelijöiden idt
	 */
	public Collection<Integer> etsiNayttelijat(int nro, int id) {
		return elokuvaRoolit.etsiNayttelijat(nro, id);
	}
	
	
	/**
	 * Etsitään elokuva nimen perusteella ja palautetaan viite siihen jos löytyi
	 * @param nimi nimi leffa nimi joka halutaan
	 * @return palauttaa halutun elokuvan
	 * @throws IndexOutOfBoundsException jos i on liian iso
	 */
	public Elokuva annaElokuva(String nimi) throws IndexOutOfBoundsException {
		return elokuvat.annaElokuva(nimi);
	}
	
	
	/**
	 * Etsitään henkilö id:n perusteella ja palautetaan henkilön nimi
	 * @param id Henkilön id
	 * @return palauttaa henkilön nimen
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
	 * Poistetaan henkilö
	 * @param henkilo Poistettavan henkilön id
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
	 * Määrätään tietty henkilö tiettyyn rooliin tiettyyn elokuvaan 
	 * @param mita sisältää elokuvan, henkilön ja roolin numeron tässä järjestyksessä
	 */
	public void lisaa(ElokuvaRooli mita) {
		elokuvaRoolit.lisaa(mita);
	}


	/**
	 * Tehdään ensin elokuvarooli annetuilla arvoilla ja sitten lisätään se
	 * @param elokuvanNro Elokuvan ID
	 * @param henkilonId Henkilön ID
	 * @param i Rooli ID
	 */
	public void lisaa(int elokuvanNro, int henkilonId, int i) {
		elokuvaRoolit.lisaa(elokuvanNro, henkilonId, i);
	}
	

	/**
	 * Etsitään elokuvia
	 * @param hakuehto hakuehto
	 * @return Palauttaa löydetyt
	 */
	public Collection<Elokuva> etsiElokuvia(String hakuehto) {
		return elokuvat.etsi(hakuehto);
	}
	
	
	/**
	 * Etsitään henkiöitä
	 * @param hakuehto hakuehto
	 * @return Palauttaa löydetyt
	 */
	public Collection<Henkilo> etsiHenkiloita(String hakuehto) {
		return henkilot.etsi(hakuehto);
	}
	
	
	/**
	 * @return elokuvien määrä
	 */
	public int getElokuvat() {
		return elokuvat.getLkm();
	}
	
	
	/**
	 * Lisää uuden henkilön 
	 * @param henkilo Henkilö joka lisätään 
	 */
	public void lisaa(Henkilo henkilo){
		henkilot.lisaa(henkilo);
	}
	
	
	/**
	 * Lisää uuden elokuvan
	 * @param leffa Elokuva joka lisätään
	 * @throws TilaException Jos ei mahdu
	 */
	public void lisaa(Elokuva leffa) throws TilaException{
		elokuvat.lisaa(leffa);
	}
	
	
	/**
	 * Tehdään ensin elokuva annetuilla arvoilla ja sitten lisätään se 
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
	 * Luodaan ensin henkilö annetulla nimellä ja sitten lisätään se
	 * @param nimi Henkilön nimi
	 */
	public void lisaaHenkilo(String nimi) {
		henkilot.lisaa(nimi);
	}
	

	/**
	 * Lisää uuden roolin. esim näyttelijä
	 * @param rooli Roolivaihtoehto joka lisätään
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
			System.err.println("Jotain meni pieleen. Hähähähää "+e);
		}
	}
	
	
	/**
	 * @param args ei käyt
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
			System.err.println("Jotain meni pieleen. Hähähähää "+e);
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
		System.out.println("Henkilöitä on "+ elokuvaDb.getHenkilot());
		System.out.println(XXX);
		System.out.println(YYY);
	}
}