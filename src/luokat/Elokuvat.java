package luokat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;

/**
 * Luokka joka osaa lis‰t‰ ja poistaa leffoja
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class Elokuvat implements Iterable<Elokuva> {

	private int MAX_ELOKUVIA   = Integer.MAX_VALUE;
	private int LKM = 0;
	private  Elokuva[] alkiot= new Elokuva[10];	
	private boolean tallennetaanko = false;
   
	
	/**
	 * @param koko max koko
	 */
	public Elokuvat(int koko) {
		MAX_ELOKUVIA = koko;
		alkiot = new Elokuva[koko];
	}
	
	
	/**
	 * Oletusmuodostaja
	 */
	public Elokuvat() {
		// Ei tarvita alustella
	}


	/**
	 * Palauttaa elokuvien lukum‰‰r‰n
	 * @return elokuvien lukum‰‰r‰
	 */
	public int getLkm() {
	    return LKM;
	}
	
	
	 /**
	  * Etsit‰‰n elokuvat jotka vastaavat hakua
	 * @param hakuehto hakuehto 
	 * @return tietorakenteen lˆytyneist‰ 
	 * @example 
     * <pre name="test"> 
     * #THROWS TilaException  
     *   Elokuvat leffat = new Elokuvat(); 
     *   Elokuva leffa1 = new Elokuva(); leffa1.parse("1|Die Hard|1982|5|Mahtava!"); 
     *   Elokuva leffa2 = new Elokuva(); leffa2.parse("2|Deep Throat|1972|5|Wink Wink ;)"); 
     *   Elokuva leffa3 = new Elokuva(); leffa3.parse("3|DieHasr|1911|3|ˆjsjhfgsdf dsf "); 
     *   Elokuva leffa4 = new Elokuva(); leffa4.parse("4|Leffa|2012|2|aikuwn fe addd"); 
     *   Elokuva leffa5 = new Elokuva(); leffa5.parse("5|Waldo|1992|1|arvosteluaniinvitusti"); 
     *   leffat.lisaa(leffa1); leffat.lisaa(leffa2); leffat.lisaa(leffa3); leffat.lisaa(leffa4); leffat.lisaa(leffa5); 
     *   List<Elokuva> loytyneet; 
     *   loytyneet = (List<Elokuva>)leffat.etsi("s"); 
     *   loytyneet.size() === 1; 
     *   loytyneet.get(0) == leffa3 === true; 
     *    
     *   loytyneet = (List<Elokuva>)leffat.etsi("d"); 
     *   loytyneet.size() === 4; 
     *   loytyneet.get(0) == leffa2 === true; 
     *   loytyneet.get(1) == leffa1 === true;
     *   loytyneet.get(2) == leffa3 === true;
     *   loytyneet.get(3) == leffa5 === true;
     *    
     *   loytyneet = (List<Elokuva>)leffat.etsi("w*");  
     *   loytyneet.size() === 1; 
     *   loytyneet.get(0) == leffa5 === true;  
     *   
     *   loytyneet = (List<Elokuva>)leffat.etsi("*w*");  
     *   loytyneet.size() === 1; 
     *   loytyneet.get(0) == leffa5 === true;  
     *   
     *   loytyneet = (List<Elokuva>)leffat.etsi("*w");  
     *   loytyneet.size() === 1; 
     *   loytyneet.get(0) == leffa5 === true;  
     *   
     *   loytyneet = (List<Elokuva>)leffat.etsi("*"); 
     *   loytyneet.size() === 5; 
     * </pre> 
     */ 
	public Collection<Elokuva> etsi(String hakuehto) {
	    String ehto = "";
	    if(hakuehto.length()==0) ehto= "*";
	    if(!ehto.startsWith("*")) ehto= "*" + hakuehto;
	    if(!ehto.endsWith("*"))   ehto =      ehto + "*";
	    
	    List<Elokuva> loytyneet = new ArrayList<Elokuva>(); 
	    for (Elokuva elokuva : this) { 
	        if (WildChars.onkoSamat(elokuva.getNimi(), ehto)) loytyneet.add(elokuva);  
	    } 
	    
	    Comparator<Elokuva> comparator = new Comparator<Elokuva>() {
	    	@Override
			public int compare(Elokuva a, Elokuva b) {
				String s1 = a.getNimi();
				String s2 = b.getNimi();

				return s1.compareTo(s2);
			}
	    };
	    Collections.sort(loytyneet, comparator);  
	    return loytyneet; 
	}
	   
	
	 /**
	 * Luokka elokuvien iteroitiin
	 * @example
     * <pre name="test">
     * #THROWS TilaException 
     * #import java.util.*;
     * 
     * Elokuvat leffis = new Elokuvat(5);
     * Elokuva leffa = new Elokuva();
	 * Elokuva filmi = new Elokuva();
     * leffa.esimTulosta();
     * leffa.numeroi();
     * filmi.numeroi();
     * filmi.esimTulosta();
     *
     * leffis.lisaa(leffa); 
     * leffis.lisaa(filmi); 
     * leffis.lisaa(leffa); 
     * 
     * StringBuffer ids = new StringBuffer();
     * for (Elokuva kuvis:leffis)   // Kokeillaan for-silmukan toimintaa
     *   ids.append(" "+kuvis.getElokuvanNro());           
     * 
     * String tulos = " " + leffa.getElokuvanNro() + " " + filmi.getElokuvanNro() + " " + leffa.getElokuvanNro();
     * 
     * ids.toString() === tulos; 
     * 
     * ids = new StringBuffer(30);
     * for (Iterator<Elokuva>  i=leffis.iterator(); i.hasNext(); ) { // ja iteraattorin toimintaa
     *   Elokuva kuva = i.next();
     *   ids.append(" "+kuva.getElokuvanNro());           
     * }
     * 
     * ids.toString() === tulos;
     * 
     * Iterator<Elokuva>  i=leffis.iterator();
     * i.next() == leffa  === true;
     * i.next() == filmi  === true;
     * i.next() == leffa  === true;
     * i.next();  #THROWS NoSuchElementException 
     * </pre>
     */
	public class ElokuvatIterator implements Iterator<Elokuva> {
		private int kohta=0;
		
		
		@Override
		public boolean hasNext() {
			return kohta < getLkm();
		}

		
		@Override
		public Elokuva next()throws NoSuchElementException {
			if ( !hasNext() ) throw new NoSuchElementException("Ei ole seuraavaa");
			return anna(kohta++);
		}
		
		
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("T‰t‰ ei tueta");
		}
	 }


	/**
	 * @return Palautetaan iteraattorin elokuviin
	 */
	@Override
	public Iterator<Elokuva> iterator() {
		 return new ElokuvatIterator();
	}
	
	
	/**
	 * Palauttaa viitteen haluttuun elokuvaan
	 * @param nimi elokuvan nimi joka halutaan
	 * @return elokuvan viite
	 * @example
	 * <pre name="test">
     * #THROWS TilaException  
     * Elokuvat elokuvat = new Elokuvat(5); 
     * Elokuva leffa1 = new Elokuva(), leffa2 = new Elokuva(), leffa3 = new Elokuva(); 
     * leffa1.numeroi(); leffa2.numeroi(); leffa3.numeroi(); 
     * leffa1.nimea("leffa1"); leffa2.nimea("leffa2"); leffa3.nimea("leffa3");
     * elokuvat.lisaa(leffa1); elokuvat.lisaa(leffa2); elokuvat.lisaa(leffa3); 
     * elokuvat.annaElokuva(leffa1.getNimi() ) == leffa1 === true; 
     * elokuvat.annaElokuva(leffa2.getNimi()) == leffa2 === true; 
     * elokuvat.annaElokuva(leffa3.getNimi()) == leffa3 === true; 
     * </pre> 
	 */
	public Elokuva annaElokuva(String nimi) {
		for (Elokuva elokuva : this) {
			if (nimi == elokuva.getNimi())
				return elokuva;
		}
		return null;
	}
	
	

	/**
	 * Luodaan elokuva annetuilla tiedoilla ja lis‰t‰‰n se listaan
	 * @param elokuvanNimi elokuvanNimi
	 * @param vuosi vuosi
	 * @param  tahdet tahdet
	 * @param juoni juoni
	 */
	public void lisaa(String elokuvanNimi, int vuosi, int tahdet, String juoni) {
		Elokuva tama = new Elokuva(elokuvanNimi,  vuosi,  tahdet,  juoni);
		tama.numeroi();
		try {
			this.lisaa(tama);
		} catch (TilaException e) {
			System.err.println("ei voitu list‰t‰. "+e);
		}
	}	

	
	/**
	 * Palauttaa viitteen i:teen elokuvaan (Iteraattorijuttuja)
	 * @param nro Monesko elokuva halutaan
	 * @return viite n:teen elokuvaan
	 * @throws IndexOutOfBoundsException jos ei ole
	 */
	public Elokuva anna(int nro) throws IndexOutOfBoundsException {
		if (nro < 0 || LKM <= nro)
			throw new IndexOutOfBoundsException("Laiton indeksi: " + nro);
		return alkiot[nro];
	}
	
	
	/**
	 * Poistetaan elokuva tietorakenteesta siirt‰m‰ll‰ alkioita taaksep‰in
	 * @param id elokuvan numero
	 * @return 0 jos ei poistettu, 1 jos poistettiin
	 * <pre name="test">
     * #THROWS TilaException  
     * Elokuvat elokuvaLista = new Elokuvat();
     * Elokuva leffa = new Elokuva();
	 * Elokuva filmi = new Elokuva();
	 * Elokuva elokuva = new Elokuva();
	 * leffa.numeroi();
     * filmi.numeroi();
     * elokuva.numeroi();
     * int id1 = leffa.getElokuvanNro(); 
     * elokuvaLista.lisaa(leffa); elokuvaLista.lisaa(filmi); elokuvaLista.lisaa(elokuva); 
     * elokuvaLista.poista(id1+1) === 1; 
     * elokuvaLista.getLkm() === 2; 
     * elokuvaLista.poista(id1) === 1; elokuvaLista.getLkm() === 1; 
     * elokuvaLista.poista(id1+3) === 0; elokuvaLista.getLkm() === 1; 
     * </pre> 
     */ 
	public int poista(int id) {
		int a= etsiId(id);
		 if (a < 0) return 0; 
		LKM--;
		for (int i = a; i < LKM; i++)
			alkiot[i] = alkiot[i + 1];
		alkiot[LKM] = null;
		tallennetaanko = true;
		return 1;
	}
	
	
	/**
	 * Tarvitaan poistossa. Etsii leffan id:n perusteella indeksin taulukossa
	 * @param id Leffan id
	 * @return palauttaa indeksin tai -1 jos ei lˆydy
	 *  <pre name="test"> 
     * #THROWS TilaException  
     * Elokuvat elokuvat = new Elokuvat(5);
     * Elokuva leffa1 = new Elokuva(), leffa2 = new Elokuva(), leffa3 = new Elokuva(); 
     * leffa1.numeroi(); leffa2.numeroi(); leffa3.numeroi(); 
     * int id1 = leffa1.getElokuvanNro(); 
     * elokuvat.lisaa(leffa1); elokuvat.lisaa(leffa2); elokuvat.lisaa(leffa3); 
     * elokuvat.etsiId(id1+1) === 1; 
     * elokuvat.etsiId(id1+2) === 2; 
     * </pre> 
	 */
	public int etsiId(int id) {
		for (int i = 0; i < LKM; i++) 
			if (id == alkiot[i].getElokuvanNro()) return i; 
		return -1;
	}
	
	
	/**
	 * Tallentaa elokuvat tiedostoon
	 * @throws TilaException Jos virheit‰
	 */
	public void talleta() throws TilaException{
		if(tallennetaanko==false) return;
		
		String nimi= "elokuvat.dat";
		
		try(PrintWriter writer = new PrintWriter(new FileWriter(nimi) )){
			for (int i= 0; i < getLkm(); i++) {
				Elokuva x = alkiot[i];
				writer.println(x.toString());
			} 
			writer.close();
		} catch (FileNotFoundException e) {
			throw new TilaException("Tiedostoa ei ole! " + e.getMessage()); 
		} catch  (IOException ex){
		throw new TilaException("Tiedosto ei aukea! " + ex.getMessage()); 
		} 
		tallennetaanko = false;
	}
	
	
	/**
	 * Lukee elokuvat tiedostosta ja lis‰‰ ne listaan
	 * @throws TilaException Jos tulee virheit‰
	 * @example
     * <pre name="test">
     * #THROWS TilaException 
     * #import java.io.File;
     * File ftied = new File("elokuvat.dat");
     * ftied.delete();
     * 
     * Elokuvat elokuvaLista = new Elokuvat();
     * Elokuva leffa1 = new Elokuva();
	 * Elokuva filmi1 = new Elokuva();
	 * Elokuva elokuva1 = new Elokuva();
	 * 
	 * elokuvaLista.lueTiedostosta(); #THROWS TilaException
	 * 
	 * leffa1.esimTulosta();
     * filmi1.esimTulosta();
     * elokuva1.esimTulosta();
     * 
     *  elokuvaLista.lisaa(leffa1);
     *  elokuvaLista.lisaa(filmi1);
     *  elokuvaLista.lisaa(elokuva1);
     *  elokuvaLista.talleta();
     *  elokuvaLista = new Elokuvat();
     *  elokuvaLista.lueTiedostosta();
     *  Iterator<Elokuva> i = elokuvaLista.iterator();
     *  i.next() === leffa1;
     *  i.next() === filmi1;
     *  i.next() === elokuva1;
     *  i.hasNext() === false;
     *  elokuvaLista.lisaa(leffa1);
     *  elokuvaLista.talleta();
     * </pre>
	 */
	public void lueTiedostosta() throws TilaException{
		String tied= "elokuvat.dat";
		try (Scanner fi = new Scanner(new FileInputStream(new File(tied)));) {
			while (fi.hasNext()) {
				Elokuva leffa= new Elokuva();
				String s = fi.nextLine();
				leffa.parse(s);
				lisaa(leffa);
			}
			fi.close();
		} catch (FileNotFoundException e) {
			throw new TilaException("Tiedosto ei aukea! " + e.getMessage());
		} catch (RuntimeException ex) {
			System.err.println("Vikaa tiedostoa luettaessa: " + ex.getMessage());
		}
	}
	
	
	/**
	 * Lis‰t‰‰n uusi elokuvan tietorakenteeseen
	 * @param elokuva uusi elokuva
	 * @throws TilaException jos tietorakenne on jo t‰ynn‰
	 * @example
	 * <pre name="test">
     * #THROWS TilaException 
     * #import java.util.*;
	 * Elokuvat lista = new Elokuvat();
     * Elokuva leffa = new Elokuva();
	 * Elokuva filmi = new Elokuva();
     * lista.getLkm() === 0;
     * lista.lisaa(leffa); lista.getLkm() === 1;
     * lista.lisaa(filmi); lista.getLkm() === 2;
     * lista.lisaa(leffa); lista.getLkm() === 3;
     * Iterator<Elokuva> it = lista.iterator();
     * it.next() === leffa;
     * it.next() === filmi;
     * it.next() === leffa;
     * lista.lisaa(leffa); lista.getLkm() === 4;
     * lista.lisaa(leffa); lista.getLkm() === 5;
     * </pre>
     */
	public void lisaa (Elokuva elokuva) throws TilaException{
		if (LKM >= MAX_ELOKUVIA) throw new TilaException ("Liikaa alkioita");
		if (LKM >= alkiot.length) alkiot = Arrays.copyOf(alkiot, LKM+10);;
		alkiot[LKM] = elokuva;
		LKM++;
		tallennetaanko= true;
	}
	
	
	/**
	 * @param args ei k‰yt
	 */
	public static void main(String[] args) {
		Elokuvat leffoja = new Elokuvat();

		Elokuva leffa = new Elokuva();
		Elokuva filmi = new Elokuva();

		leffa.esimTulosta();
		leffa.numeroi();
		filmi.esimTulosta();
		filmi.numeroi();
		try {
			leffoja.lisaa(leffa);
			leffoja.lisaa(filmi);
		} catch (TilaException e1) {
			System.err.println("Jotain meni pieleen. H‰h‰h‰h‰‰ "+e1);
		}
		
		System.out.println("Elokuvia on " + leffoja.getLkm());

		for (int i = 0; i < leffoja.getLkm(); i++) {
			Elokuva elokuva;
			try {
				elokuva = leffoja.anna(i);
				elokuva.tulosta(System.out);
				System.out.println(leffoja.alkiot[1].getNimi());

			} catch (IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("Elokuva nro: " + i);
			
		}
		try {
			leffoja.talleta();
		} catch (TilaException e) {
			System.out.println(e.getMessage());
		}
	}
}