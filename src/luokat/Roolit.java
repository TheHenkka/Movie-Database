package luokat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Luokka joka huolehtii elokuvien rooleista, tiet‰‰ mit‰ on olemassa ja osaa poistaa niit‰. K‰ytet‰‰n oikeastaan vaan n‰yttelij‰‰ ja ohjaajaa, jotka ovat jo valmiina
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class Roolit implements Iterable<Rooli>  {

	/** Roolitaulukko */
	private final Collection<Rooli> rooliTaulukko= new ArrayList<>();
	private boolean tallennetaanko = false;
	
	
	/**
	 * Lukee henkilˆiden roolit tiedostosta ja lis‰‰ ne listaan
	 * @throws TilaException Jos tulee virhe
	 * @example
     * <pre name="test">
     * #THROWS TilaException 
     * #import java.util.Iterator;
     *  Roolit lista= new Roolit();
	 *	Rooli nayttelija = new Rooli();
	 *	Rooli ohjaaja= new Rooli();
	 *
	 *	nayttelija.roolita();
	 *	ohjaaja.roolita();
	 *	lista.lisaa(nayttelija);
	 *	lista.lisaa(ohjaaja);
     * 
     *  lista.talleta();
     *  lista = new Roolit();
     *  lista.lueTiedostosta();
     *  Iterator<Rooli> i = lista.iterator();
     *  i.next() === nayttelija;
     *  i.next() === ohjaaja;
     *  i.hasNext() === false;
     * </pre>
	 */
	public void lueTiedostosta() throws TilaException{
		
		String tied= "roolivaihtoehdot.dat";

		try (Scanner fi = new Scanner(new FileInputStream(new File(tied)));) {
			while (fi.hasNext()) {
				Rooli rooli= new Rooli();
				String s = fi.nextLine();
				rooli.parse(s);
				lisaa(rooli);
			}
			fi.close();
		} catch (FileNotFoundException e) {
			throw new TilaException("Tiedosto ei aukea! " + e.getMessage());
		} catch (RuntimeException ex) {
			System.err.println("Vikaa tiedostoa luettaessa: " + ex.getMessage());
		}
	}
	
	
	/**
	* @example
    * <pre name="test">
    * #import java.util.*;
    * 
    *  Roolit lista= new Roolit();
	*	Rooli nayttelija = new Rooli();
	*	Rooli ohjaaja= new Rooli();
	*   Rooli kana= new Rooli();
    *
	*	lista.lisaa(nayttelija);
	*	lista.lisaa(ohjaaja);
    * 
    *  Iterator<Rooli> i2=lista.iterator();
    *  i2.next() === nayttelija;
    *  i2.next() === ohjaaja;
    *  i2.next() === kana;  #THROWS NoSuchElementException   
    * </pre>
    */
	@Override
	public Iterator<Rooli> iterator() {
		return rooliTaulukko.iterator();	
	}

	
	/**
	 * @param id Roolivaihtoehdon id 
	 * @return roolivaihtoehto
	 * @throws IndexOutOfBoundsException jos ei lˆydy
	 */
	public String etsiRooli(int id) throws IndexOutOfBoundsException{
		for (Rooli etsittava : rooliTaulukko)
			if (etsittava.getRooliId() == id) return etsittava.getRooliVaihtoehto();
		throw new IndexOutOfBoundsException("Ei ollukkaan");	
	}


	/**
	 * Tallentaa roolitiedot tiedostoon
	 * @throws TilaException Jos virheit‰
	 */
	public void talleta() throws TilaException{
		if(tallennetaanko==false) return;
		
		String nimi= "roolivaihtoehdot.dat";
		
		try(PrintWriter writer = new PrintWriter(new FileWriter(nimi) )){
			for (Rooli rooli : rooliTaulukko) {
				writer.println(rooli.toString());
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
	 * Lis‰t‰‰n rooli taulukkoon
	 * @param tama Roolivaihtoehto joka lis‰t‰‰n
	 */
	public void lisaa(Rooli tama) {
		rooliTaulukko.add(tama);	
	    tallennetaanko = true;
	}
	
	
	/**
	 * @param args nope
	 * @throws TilaException Jos virheit‰
	 */
	public static void main(String[] args) throws TilaException {
		
		Roolit lista= new Roolit();
		Rooli nayttelija = new Rooli();
		Rooli ohjaaja= new Rooli();
		nayttelija.roolita();
		ohjaaja.roolita();
		lista.lisaa(nayttelija);
		lista.lisaa(ohjaaja);
		lista.talleta();
		
		for (Rooli rooli : lista) 
			rooli.tulosta(System.out);
	}
}