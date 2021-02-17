package luokat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Luokka joka huolehtii elokuvien henkilöiden rooleista, osaa lisätä ja poistaa niitä
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 18.3.2015
 */
public class ElokuvaRoolit implements Iterable<ElokuvaRooli>{
	
	private final  Collection<ElokuvaRooli> elokuvaRooliTaulukko= new ArrayList<>();
	private boolean tallennetaanko = false;
	
	
	/**
	 * @param mita sisältää elokuvan, henkilön ja roolin numeron
	 */
	public void lisaa(ElokuvaRooli mita) {
		tallennetaanko=true;
		elokuvaRooliTaulukko.add(mita);
	}

	
	/**
	 * Iteraattori elokuvarooileille
	* @example
    * <pre name="test">
    * #import java.util.*;
    * 
    * ElokuvaRoolit elokuvaRoolit = new ElokuvaRoolit();
    * ElokuvaRooli leffa1 = new ElokuvaRooli(1,2,2);
    * ElokuvaRooli filmi1 = new ElokuvaRooli(1,1,1);
	* ElokuvaRooli elokuva1 = new ElokuvaRooli(2,2,1);
	* ElokuvaRooli elo = new ElokuvaRooli(5,1,1);
	* 
	*  elokuvaRoolit.lisaa(leffa1);
    *  elokuvaRoolit.lisaa(filmi1);
    *  elokuvaRoolit.lisaa(elokuva1);
    * 
    *  Iterator<ElokuvaRooli> i2=elokuvaRoolit.iterator();
    *  i2.next() === leffa1;
    *  i2.next() === filmi1;
    *  i2.next() === elokuva1;
    *  i2.next() === elo;  #THROWS NoSuchElementException  
    *  
    *  int n = 0;
    *  String jnrot[] = {"1|2|2", "1|1|1", "2|2|1"};
    *  
    *  for ( ElokuvaRooli rool :elokuvaRoolit ) { 
    *    rool.toString() === jnrot[n]; n++;  
    *  }
    *  
    *  n === 3;
    *  
    * </pre>
    */
	@Override
	public Iterator<ElokuvaRooli> iterator() {
		return elokuvaRooliTaulukko.iterator();	
	}


	/**
	 * Etsitään kaikki henkilöt jotka ovat elokuvassa i
	 * @param nro elokuvan id
	 * @param id näyttelijä vai ohjaaja
	 * @return kaikki henkilöt
	 * @example
	 * <pre name="test">
	 * #THROWS TilaException 
	 * ElokuvaRoolit elokuvaRoolit = new ElokuvaRoolit(); 
	 * Elokuvat leffat = new Elokuvat();
	 *	Henkilo henk1= new Henkilo("Mauku"); henk1.numeroi();
	 *	Elokuva leffa1= new Elokuva(); leffa1.numeroi(); leffa1.nimea("LEFFA1");
	 *  Rooli nayttelija= new Rooli(); nayttelija.numeroi();
	 *	Rooli ohjaaja= new Rooli(); ohjaaja.numeroi();
	 *	leffat.lisaa(leffa1);
	 *	
	 *  Henkilo henk2= new Henkilo("Miuku"); henk2.numeroi();
	 *	Elokuva leffa2= new Elokuva(); leffa2.numeroi(); leffa2.nimea("LEFFA2");
	 *	
	 *	ElokuvaRooli b = new ElokuvaRooli(leffa2.getElokuvanNro(),henk2.getHenkilonId(), nayttelija.getRooliId());
	 *  ElokuvaRooli a = new ElokuvaRooli(leffa1.getElokuvanNro(),henk1.getHenkilonId(), ohjaaja.getRooliId());
	 *  ElokuvaRooli c = new ElokuvaRooli(leffa1.getElokuvanNro(),henk2.getHenkilonId(), nayttelija.getRooliId());
	 *  ElokuvaRooli d = new ElokuvaRooli(leffa1.getElokuvanNro(),henk1.getHenkilonId(), nayttelija.getRooliId());
	 *	elokuvaRoolit.lisaa(a);
	 *	elokuvaRoolit.lisaa(b);
	 *  elokuvaRoolit.lisaa(c);
	 *  elokuvaRoolit.lisaa(d);
	 *
	 * List<Integer> loytyneet; 
	 * 
	 * loytyneet = (List<Integer>)elokuvaRoolit.etsiNayttelijat(leffa1.getElokuvanNro(), 2);
	 * loytyneet.size() === 1; 
	 * loytyneet.get(0) == henk1.getHenkilonId() === true; 
	 * 
	 * loytyneet = (List<Integer>)elokuvaRoolit.etsiNayttelijat(leffa1.getElokuvanNro(), 1);
	 * loytyneet.size() === 2; 
	 * loytyneet.get(0) == henk1.getHenkilonId() === true; 
	 * loytyneet.get(1) == henk2.getHenkilonId() === true; 
	 * 
	 * loytyneet = (List<Integer>)elokuvaRoolit.etsiNayttelijat(leffa2.getElokuvanNro(), 1);
	 * loytyneet.size() === 1; 
	 * loytyneet.get(0) == henk2.getHenkilonId() === true; 
	 * </pre>
	 */
	public Collection<Integer> etsiNayttelijat(int nro, int id) {
	    List<Integer> loytyneet = new ArrayList<Integer>(); 
	    for (ElokuvaRooli elokuvarooli : this) { 
	    	
	    	StringBuilder a=new StringBuilder(elokuvarooli.toString());
	    	int elokuvaID= 		Integer.valueOf(Mjonot.erota(a, '|'));
			int henkiloID =     Integer.valueOf(Mjonot.erota(a, '|'));
			int rooliID=		Integer.valueOf(Mjonot.erota(a, '|'));
			
	        if (elokuvaID == nro  && rooliID==id)
	    	   loytyneet.add(henkiloID);  
	    } 
	   
	    if (!loytyneet.contains(null))
	    	Collections.sort(loytyneet);  
	    
	    return loytyneet; 
	}
	

	/**
	 * @param elokuvanNro elokuvanNro
	 * @param henkilonId henkilonId
	 * @param i i
	 */
	public void lisaa(int elokuvanNro, int henkilonId, int i) {
		ElokuvaRooli rooli = new ElokuvaRooli(elokuvanNro, henkilonId,i );
		this.lisaa(rooli);
	}


	/**
	 * poistetaan roolit leffasta i 
	 * @param id leffan id jonka roolit poistetaan
	 * @return poistettiinko
	 */
	public boolean poista(int id) {
		Collection<Integer> loytyneet = etsiNayttelijat(id, 1);
		loytyneet.addAll(etsiNayttelijat(id, 2));
		int koko =elokuvaRooliTaulukko.size();
		
		for (int i : loytyneet){
			ElokuvaRooli rooli1 = new ElokuvaRooli(id,i, 1);
			ElokuvaRooli rooli2 =new ElokuvaRooli(id, i, 2);
			elokuvaRooliTaulukko.remove(rooli1); 
			elokuvaRooliTaulukko.remove(rooli2); 
			tallennetaanko = true;
		}
		return (elokuvaRooliTaulukko.size()==koko);
	}
	
	
	/**
	 * Tallentaa roolit elokuvassa tiedostoon
	 * @throws TilaException Jos virheitä
	 */
	public void talleta() throws TilaException{
		if(tallennetaanko==false) return;
		
		String nimi= "elokuvaroolit.dat";
		
		try(PrintWriter writer = new PrintWriter(new FileWriter(nimi) )){
			for (ElokuvaRooli elokuvaRooli : elokuvaRooliTaulukko) {
				writer.println(elokuvaRooli.toString());
			} 
			writer.close();
		} catch (FileNotFoundException e) {
			throw new TilaException("Tiedosto ei aukea! " + e.getMessage()); 
		} catch  (IOException ex){
		throw new TilaException("Tiedosto ei aukea! " + ex.getMessage()); 
		}
		tallennetaanko = false;
	}
	
	
	/**
	 * Lukee roolit elokuvsssa tiedostosta ja lisää ne listaan
	 * @throws TilaException Jos tulee virheitä
	 * @example
     * <pre name="test">
     * #THROWS TilaException 
     * #import java.io.File;
     * #import java.util.Iterator;
     * File ftied = new File("elokuvaroolit.dat");
     * ftied.delete();
     * 
     * ElokuvaRoolit elokuvaRoolit = new ElokuvaRoolit();
     * ElokuvaRooli leffa1 = new ElokuvaRooli(1,2,2);
	 * ElokuvaRooli filmi1 = new ElokuvaRooli(1,1,1);
	 * ElokuvaRooli elokuva1 = new ElokuvaRooli(2,2,1);
	 * 
	 * elokuvaRoolit.lueTiedostosta(); #THROWS TilaException
     * 
     *  elokuvaRoolit.lisaa(leffa1);
     *  elokuvaRoolit.lisaa(filmi1);
     *  elokuvaRoolit.lisaa(elokuva1);
     *  elokuvaRoolit.talleta();
     *  elokuvaRoolit = new ElokuvaRoolit();
     *  elokuvaRoolit.lueTiedostosta();
     *  Iterator<ElokuvaRooli> i = elokuvaRoolit.iterator();
     *  i.next() === leffa1;
     *  i.next() === filmi1;
     *  i.next() === elokuva1;
     *  i.hasNext() === false;
     *  elokuvaRoolit.lisaa(leffa1);
     *  elokuvaRoolit.talleta();
     * </pre>
	 */
	public void lueTiedostosta() throws TilaException{
		String tied= "elokuvaroolit.dat";
		
		try (Scanner fi = new Scanner(new FileInputStream(new File(tied)));) {
			while (fi.hasNext()) {
				ElokuvaRooli elokuvaRooli = new ElokuvaRooli();
				String s = fi.nextLine();
				elokuvaRooli.parse(s);
				lisaa(elokuvaRooli);
			}
			fi.close();
		} catch (FileNotFoundException e) {
			throw new TilaException("Tiedosto ei aukea! " + e.getMessage());
		} catch (RuntimeException ex) {
			System.err.println("Vikaa tiedostoa luettaessa: " + ex.getMessage());
		}
	}
	
	
	/**
	 * @param args ei oo
	 * @throws TilaException  jos virheitä
	 */
	public static void main(String[] args) throws TilaException {
		ElokuvaRoolit elokuvaRoolit = new ElokuvaRoolit(); 
		Elokuvat leffat = new Elokuvat(); 
		Henkilo henk1= new Henkilo();
		Elokuva leffa1= new Elokuva();
		
		Rooli ohjaaja= new Rooli();
		henk1.numeroi();
		leffa1.numeroi();
		leffa1.nimea("henk2");
		ohjaaja.roolita();
		
		ElokuvaRooli XXX = new ElokuvaRooli(leffa1.getElokuvanNro(),henk1.getHenkilonId(), ohjaaja.getRooliId());
		
		elokuvaRoolit.lisaa(XXX);
		
		henk1.numeroi();
		leffa1.numeroi();
		leffa1.esimTulosta();
		ohjaaja.numeroi();
		
		ElokuvaRooli XX = new ElokuvaRooli(leffa1.getElokuvanNro(),henk1.getHenkilonId(), ohjaaja.getRooliId());
		
		elokuvaRoolit.lisaa(XX);
		leffat.lisaa(leffa1);
		
		try {
			elokuvaRoolit.talleta();
		} catch (TilaException e) {
			System.out.println(e.getMessage());
		}
		
		for (ElokuvaRooli tama : elokuvaRoolit) 
			tama.tulosta(System.out);
	}
}