package luokat;

/**
 * Virheilmoitus luokka
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 4.3.2015
 */
public class TilaException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Poikkeuksen muodostaja joka saa viestin
	 * @param viesti kertoo mikä meni pieleen
	 */
	public TilaException(String viesti) {
		super(viesti);
	}
}