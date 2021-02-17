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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;

/**
 * Luokka joka osaa lisätä ja poistaa henkilöitä
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public  class Henkilot implements Iterable<Henkilo>  {
	
	private final Collection<Henkilo> henkiloTaulukko= new ArrayList<>();
	private boolean tallennetaanko = false;
	
	
	/**
	 * Tallentaa henkilöt tiedostoon
	 * @throws TilaException Jos virheitä
	 */
	public void talleta() throws TilaException{
		if(tallennetaanko==false) return;
		
		String tied= "henkilot.dat";
		
		try(PrintWriter writer = new PrintWriter(new FileWriter(tied) )){
			for (Henkilo henkilo : this) {
				writer.println(henkilo.toString());
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
	* Etsitään henkilöt jotka vastaavat hakua
	* @param hakuehto hakuehto 
	* @return tietorakenteen löytyneistä 
	* @example 
    * <pre name="test"> 
    * #THROWS TilaException  
    *   Henkilot henkilot = new Henkilot(); 
    *   Henkilo henk1 = new Henkilo(); henk1.parse("1|I.C Wiener"); 
    *   Henkilo henk2 = new Henkilo(); henk2.parse("2|Sara Saaranen"); 
    *   Henkilo henk3 = new Henkilo(); henk3.parse("3|Anu Saukko"); 
    *   Henkilo henk4 = new Henkilo(); henk4.parse("4|Kissa Kissanen"); 
    *   Henkilo henk5 = new Henkilo(); henk5.parse("5|Waldo"); 
    *   henkilot.lisaa(henk1); henkilot.lisaa(henk2); henkilot.lisaa(henk3); henkilot.lisaa(henk4); henkilot.lisaa(henk5); 
    *   List<Henkilo> loytyneet; 
    *   loytyneet = (List<Henkilo>)henkilot.etsi("s"); 
    *   loytyneet.size() === 3; 
    *   loytyneet.get(0) == henk3 === true; 
    *   loytyneet.get(1) == henk4 === true; 
    *   loytyneet.get(2) == henk2 === true; 
    *    
    *   loytyneet = (List<Henkilo>)henkilot.etsi("*d*"); 
    *   loytyneet.size() === 1; 
    *   loytyneet.get(0) == henk5 === true; 
    *   
    *   loytyneet = (List<Henkilo>)henkilot.etsi("d*"); 
    *   loytyneet.size() === 1; 
    *   loytyneet.get(0) == henk5 === true; 
    *   
    *   loytyneet = (List<Henkilo>)henkilot.etsi("*d"); 
    *   loytyneet.size() === 1; 
    *   loytyneet.get(0) == henk5 === true; 
    * </pre> 
    */ 
	public Collection<Henkilo> etsi(String hakuehto) {
		 String ehto = "";
		 if(hakuehto.length()==0) ehto= "*";
		 if(!ehto.startsWith("*")) ehto= "*" + hakuehto;
		 if(!ehto.endsWith("*"))   ehto =      ehto + "*";
		    
	    List<Henkilo> loytyneet = new ArrayList<Henkilo>(); 
	    for (Henkilo henkilo : this) { 
	        if (WildChars.onkoSamat(henkilo.getHenkilonNimi(), ehto)) loytyneet.add(henkilo);  
	    } 
	    
	    Comparator<Henkilo> comparator = new Comparator<Henkilo>() {
	    	@Override
			public int compare(Henkilo a, Henkilo b) {
				String s1 = a.getHenkilonNimi();
				String s2 = b.getHenkilonNimi();

				return s1.compareTo(s2);
			}
	    };
	    Collections.sort(loytyneet, comparator);  
	    return loytyneet; 
	}
	
	
	/**
	 * Lukee henkilöt tiedostosta ja lisää ne listaan
	 * @throws TilaException Jos tulee virheitä
	 * @example
     * <pre name="test">
     * #THROWS TilaException 
     * #import java.io.File;
     * #import java.util.Iterator;
     * File ftied = new File("henkilot.dat");
     * ftied.delete(); 
     * 
     * Henkilot hnklot = new Henkilot();
     * Henkilo hnk1 = new Henkilo("Kissa");
	 * Henkilo hnk2 = new Henkilo("Koira");
	 * Henkilo hnk3 = new Henkilo("Kana");
	 * 
	 * hnklot.lueTiedostosta(); #THROWS TilaException
     * 
     *  hnklot.lisaa(hnk1);
     *  hnklot.lisaa(hnk2);
     *  hnklot.lisaa(hnk3);
     *  hnklot.talleta();
     *  hnklot = new Henkilot();
     *  hnklot.lueTiedostosta();
     *  Iterator<Henkilo> i = hnklot.iterator();
     *  i.next() === hnk1;
     *  i.next() === hnk2;
     *  i.next() === hnk3;
     *  i.hasNext() === false;
     * </pre>
	 */
	public void lueTiedostosta() throws TilaException{
		String tied= "henkilot.dat";
		
		try (Scanner fi = new Scanner(new FileInputStream(new File(tied)));) {
			while (fi.hasNext()) {
			    Henkilo henkilo = new Henkilo();
				String s = fi.nextLine();
				henkilo.parse(s);
				lisaa(henkilo);
			}
			fi.close();
		} catch (FileNotFoundException e) {
			throw new TilaException("Tiedosto ei aukea! " + e.getMessage());
		} catch (RuntimeException ex) {
			System.err.println("Vikaa tiedostoa luettaessa: " + ex.getMessage());
		}
	}

	
	/**
	 * Iteraattori henkilöistä
	* @example
    * <pre name="test">
    * #import java.util.*;
    * Henkilot hnklot = new Henkilot();
    * Henkilo hnk1 = new Henkilo("Kissa");
	* Henkilo hnk2 = new Henkilo("Koira");
	* Henkilo hnk3 = new Henkilo("Kana");
	* Henkilo hnk4 = new Henkilo();
    * hnk1.numeroi();
    * hnk2.numeroi();
    * hnk3.numeroi();
	* 
	*  hnklot.lisaa(hnk1);
    *  hnklot.lisaa(hnk2);
    *  hnklot.lisaa(hnk3);
    * 
    *  Iterator<Henkilo> i2=hnklot.iterator();
    *  i2.next() === hnk1;
    *  i2.next() === hnk2;
    *  i2.next() === hnk3;
    *  i2.next() === hnk4;  #THROWS NoSuchElementException  
    * </pre>
    */
	@Override
	public Iterator<Henkilo> iterator() {
		return henkiloTaulukko.iterator();	
	}

	
	/**
	 * Elokuvaroolien etsimistä varten. Etsitään henkilö id:n perusteella ja palautetaan nimi
	 * @param i Henkilön id jota etsitään
	 * @return  henkilön nimi tai "EI LÖYTYNYT!" jos ei löydy
	 * @example
	 * <pre name="test">
	 * Henkilot hnklot = new Henkilot();
     * Henkilo hnk1 = new Henkilo("Kissa");
	 * Henkilo hnk2 = new Henkilo("Koira");
	 * Henkilo hnk3 = new Henkilo("Kana");
	 * Henkilo hnk4 = new Henkilo("Kana");
     * hnk1.numeroi();
     * hnk2.numeroi();
     * hnk3.numeroi();
     * hnk4.numeroi();
	 * 
	 *  hnklot.lisaa(hnk1);
     *  hnklot.lisaa(hnk2);
     *  hnklot.lisaa(hnk3);
     *  
     *  hnklot.etsiHenkilo(hnk1.getHenkilonId())===hnk1.getHenkilonNimi();
     *  hnklot.etsiHenkilo(hnk3.getHenkilonId())==hnk3.getHenkilonNimi()===true;
     *  hnklot.etsiHenkilo(hnk2.getHenkilonId())==hnk2.getHenkilonNimi()===true;
     *  hnklot.etsiHenkilo(hnk4.getHenkilonId())=== "EI LÖYTYNYT!";
	  * </pre>
	 */
	public String etsiHenkilo(int i) {
		for (Henkilo henk:this)
			if (henk.getHenkilonId() == i) return henk.getHenkilonNimi();
		return "EI LÖYTYNYT!";
	}

	
	/**
	* Palauttaa henkilöiden lukumäärän
	* @return henkilöiden lukumäärä
	*/
	public int getLkm() {
		return henkiloTaulukko.size();
	}

	
	/**
	 * Lisätään henkilö taulukkoon
	 * @param kenet kenet lisätään
	 */
	public void lisaa(Henkilo kenet) {
		henkiloTaulukko.add(kenet);		
		 tallennetaanko = true;
	}
	
	
	/**
	 * Lisätään tietyn niminen henkilö taulukkoon
	 * @param nimi Henkilön nimi
	 */
	public void lisaa(String nimi) {
		Henkilo tama = new Henkilo(nimi);
		tama.numeroi();
		this.lisaa(tama);
	}
	
	
	/**
	 * Poistetaan henkilö tietorakenteesta
	 * @param henkilo henkilo joka poistetaan
	 * @return Poistettiinko
	 * @example 
	 * <pre name="test">
     * #THROWS TilaException  
     * Henkilot ihmiset = new Henkilot();
     * Henkilo jonne= new Henkilo();
	 * Henkilo janne= new Henkilo();
	 * Henkilo onni= new Henkilo();
	 * Henkilo jone= new Henkilo();
	 * 	jonne.numeroi();
	 *  janne.numeroi();
	 *  onni.numeroi();
	 *  jone.numeroi();	
	 * ihmiset.lisaa(jonne);
	 * ihmiset.lisaa(onni);
	 * ihmiset.lisaa(jone);
	 * 
     * ihmiset.poista(jonne) === true; 		 ihmiset.getLkm() === 2; 
     * ihmiset.poista(janne) === false;		 ihmiset.getLkm() === 2; 
     * ihmiset.poista(onni) === true;		 ihmiset.getLkm() === 1; 
     * </pre> 
	 */
	public boolean poista(Henkilo henkilo) {
		boolean onko = henkiloTaulukko.remove(henkilo); 
		 tallennetaanko = true;
		return onko; 
	}
	
	
	/**
	 * Kai tässä nyt jotain yritetään testata
	 * @param args ei käyt
	 */
	public static void main(String[] args)  {
		Henkilot ihmiset = new Henkilot();
		Henkilo jonne= new Henkilo("jonne");
		Henkilo janne= new Henkilo("janne");
		Henkilo onni= new Henkilo("onni");
		Henkilo jone= new Henkilo("jone");
		
		jonne.numeroi();
		janne.numeroi();
		onni.numeroi();
		jone.numeroi();
		
				
		ihmiset.lisaa(jonne);
		ihmiset.lisaa(janne);
		ihmiset.lisaa(onni);
		ihmiset.lisaa(jone);
		
		for (Henkilo henkilo : ihmiset) 
			henkilo.tulosta(System.out);
		
		System.out.println("Henkilöitä on: "+ihmiset.getLkm());;
		
		try {
			ihmiset.talleta();
		} catch (TilaException e) {
			System.out.println(e.getMessage());
		}
	}
}