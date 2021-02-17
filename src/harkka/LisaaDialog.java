package harkka;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import luokat.Henkilo;
import luokat.TilaException;
import fi.jyu.mit.gui.ComboBoxChooser;
import fi.jyu.mit.gui.EditPanel;
import fi.jyu.mit.gui.ListChooser;

/**
 * Uusi ikkuna, jossa voi lisätä elokuvan
 * @author Hentter Eloranta
 * @version 14.5.2015
 */
public class LisaaDialog extends JDialog {

	/***/
	public final EditPanel editPanelUusiNayttelija = new EditPanel();
	/***/
	public final ListChooser listChooserKaikkiNayttelijat = new ListChooser();
	private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private final JPanel panel_3 = new JPanel();
    private final Component verticalStrut_1 = Box.createVerticalStrut(20);
    private final JPanel panel_4 = new JPanel();
    private final EditPanel editPanelVuosi = new EditPanel();
    private final ComboBoxChooser comboBoxChooserOhjaaja = new ComboBoxChooser();
    private final ListChooser listChooserNayttelijatElokuvassa = new ListChooser();
    private final JButton buttonSiirra = new JButton("Siirr\u00E4");
    private final JTextArea textAreaKuvaus = new JTextArea();
    private final JLabel lblKuvaus = new JLabel("Kuvaus");
    private final JButton btnLisaaElokuva = new JButton("Lis\u00E4\u00E4 elokuva");
    private final JButton btnPeruuta = new JButton("Peruuta");
	private final JButton btnUusiNayttelija = new JButton("Lis\u00E4\u00E4 uusi n\u00E4yttelij\u00E4");

	private Harkka harkka;
	private final JComboBox<String> comboBoxTahdet = new JComboBox<String>();
	private final JLabel lblThdet = new JLabel("T\u00E4hdet");
	private final EditPanel editPanelElokuvanNimi = new EditPanel();

    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        try {
            MuokkaaDialog dialog = new MuokkaaDialog();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
        	System.err.println("Jotain meni pieleen. "+e);
        }
    }

	/**
	 * Create the dialog.
	 * @param harkka harkka
	 */
	public LisaaDialog(Harkka harkka) {
		this.harkka= harkka;
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setTitle("ElokuvaDB - Lisää");

		getContentPane().setLayout(new BorderLayout(0, 0));

		getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		verticalStrut_1.setPreferredSize(new Dimension(0, 45));

		panel_3.add(verticalStrut_1);

		panel_3.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 0, 281, 101, 227, 0, 0, 0, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 168, 41, 0, 200, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);
		
		GridBagConstraints gbc_editPanelElokuvanNimi = new GridBagConstraints();
		gbc_editPanelElokuvanNimi.insets = new Insets(0, 0, 5, 5);
		gbc_editPanelElokuvanNimi.fill = GridBagConstraints.BOTH;
		gbc_editPanelElokuvanNimi.gridx = 1;
		gbc_editPanelElokuvanNimi.gridy = 1;
		editPanelElokuvanNimi.getLabel().setText("Elokuvan nimi");
		panel_4.add(editPanelElokuvanNimi, gbc_editPanelElokuvanNimi);

		GridBagConstraints gbc_comboBoxChooserOhjaaja = new GridBagConstraints();
		gbc_comboBoxChooserOhjaaja.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxChooserOhjaaja.fill = GridBagConstraints.BOTH;
		gbc_comboBoxChooserOhjaaja.gridx = 2;
		gbc_comboBoxChooserOhjaaja.gridy = 1;
		comboBoxChooserOhjaaja.getCaptionLabel().setText("Ohjaaja");
		panel_4.add(comboBoxChooserOhjaaja, gbc_comboBoxChooserOhjaaja);

		GridBagConstraints gbc_editPanelVuosi = new GridBagConstraints();
		gbc_editPanelVuosi.insets = new Insets(0, 0, 5, 5);
		gbc_editPanelVuosi.fill = GridBagConstraints.BOTH;
		gbc_editPanelVuosi.gridx = 3;
		gbc_editPanelVuosi.gridy = 1;
		editPanelVuosi.getLabel().setText("Vuosi");
		panel_4.add(editPanelVuosi, gbc_editPanelVuosi);
		
		GridBagConstraints gbc_lblThdet = new GridBagConstraints();
		gbc_lblThdet.insets = new Insets(0, 0, 5, 5);
		gbc_lblThdet.anchor = GridBagConstraints.EAST;
		gbc_lblThdet.gridx = 5;
		gbc_lblThdet.gridy = 1;
		panel_4.add(lblThdet, gbc_lblThdet);
		
		GridBagConstraints gbc_comboBoxTahdet = new GridBagConstraints();
		gbc_comboBoxTahdet.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxTahdet.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTahdet.gridx = 6;
		gbc_comboBoxTahdet.gridy = 1;
		comboBoxTahdet.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
		panel_4.add(comboBoxTahdet, gbc_comboBoxTahdet);

		GridBagConstraints gbc_listChooserKaikkiNayttelijat = new GridBagConstraints();
		gbc_listChooserKaikkiNayttelijat.insets = new Insets(0, 0, 5, 5);
		gbc_listChooserKaikkiNayttelijat.fill = GridBagConstraints.BOTH;
		gbc_listChooserKaikkiNayttelijat.gridx = 1;
		gbc_listChooserKaikkiNayttelijat.gridy = 2;
		listChooserKaikkiNayttelijat.getCaptionLabel().setText(
				"Kaikki n\u00E4yttelij\u00E4t");
		panel_4.add(listChooserKaikkiNayttelijat,
				gbc_listChooserKaikkiNayttelijat);

		GridBagConstraints gbc_buttonSiirra = new GridBagConstraints();
		gbc_buttonSiirra.insets = new Insets(0, 0, 5, 5);
		gbc_buttonSiirra.gridx = 2;
		gbc_buttonSiirra.gridy = 2;
		buttonSiirra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SiirraNayttelija(listChooserKaikkiNayttelijat.getSelectedText());
			}
		});
		panel_4.add(buttonSiirra, gbc_buttonSiirra);

		GridBagConstraints gbc_listChooserNayttelijatElokuvassa = new GridBagConstraints();
		gbc_listChooserNayttelijatElokuvassa.insets = new Insets(0, 0, 5, 5);
		gbc_listChooserNayttelijatElokuvassa.fill = GridBagConstraints.BOTH;
		gbc_listChooserNayttelijatElokuvassa.gridx = 3;
		gbc_listChooserNayttelijatElokuvassa.gridy = 2;
		listChooserNayttelijatElokuvassa.getCaptionLabel().setText(
				"N\u00E4yttelij\u00E4t elokuvassa");
		panel_4.add(listChooserNayttelijatElokuvassa,
				gbc_listChooserNayttelijatElokuvassa);
		
		GridBagConstraints gbc_editPanelUusiNayttelija = new GridBagConstraints();
		gbc_editPanelUusiNayttelija.insets = new Insets(0, 0, 5, 5);
		gbc_editPanelUusiNayttelija.fill = GridBagConstraints.BOTH;
		gbc_editPanelUusiNayttelija.gridx = 3;
		gbc_editPanelUusiNayttelija.gridy = 3;
		editPanelUusiNayttelija.getLabel().setText("N\u00E4yttelij\u00E4n nimi");
		panel_4.add(editPanelUusiNayttelija, gbc_editPanelUusiNayttelija);
		
		GridBagConstraints gbc_btnUusiNayttelija = new GridBagConstraints();
		gbc_btnUusiNayttelija.insets = new Insets(0, 0, 5, 5);
		gbc_btnUusiNayttelija.gridx = 4;
		gbc_btnUusiNayttelija.gridy = 3;
		btnUusiNayttelija.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lisaaHenkilo(editPanelUusiNayttelija.getText());
			}
		});
		panel_4.add(btnUusiNayttelija, gbc_btnUusiNayttelija);

		GridBagConstraints gbc_lblKuvaus = new GridBagConstraints();
		gbc_lblKuvaus.insets = new Insets(0, 0, 5, 5);
		gbc_lblKuvaus.gridx = 1;
		gbc_lblKuvaus.gridy = 4;
		panel_4.add(lblKuvaus, gbc_lblKuvaus);

		GridBagConstraints gbc_textAreaKuvaus = new GridBagConstraints();
		gbc_textAreaKuvaus.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaKuvaus.fill = GridBagConstraints.BOTH;
		gbc_textAreaKuvaus.gridx = 1;
		gbc_textAreaKuvaus.gridy = 5;
		panel_4.add(textAreaKuvaus, gbc_textAreaKuvaus);

		GridBagConstraints gbc_btnLisaaElokuva = new GridBagConstraints();
		gbc_btnLisaaElokuva.insets = new Insets(0, 0, 5, 5);
		gbc_btnLisaaElokuva.gridx = 3;
		gbc_btnLisaaElokuva.gridy = 5;
		btnLisaaElokuva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lisaaElokuva();
			}
		});
		panel_4.add(btnLisaaElokuva, gbc_btnLisaaElokuva);

		GridBagConstraints gbc_btnPeruuta = new GridBagConstraints();
		gbc_btnPeruuta.insets = new Insets(0, 0, 5, 0);
		gbc_btnPeruuta.gridx = 6;
		gbc_btnPeruuta.gridy = 5;
		panel_4.add(btnPeruuta, gbc_btnPeruuta);
	
		setVisible(true);
		
		alusta();
	}

	
	// ===============================================================================
	// Omat jutut


	/**
	 * Oletusmuodostaja. Tarvitaan, jos halutaan muokata ikkunaa
	 */
	public LisaaDialog(/*Harkka harkka*/){
		//
	}
	
	
	/**
	 * Lisätään henkilö näyttelijät elokuvassa listaan
	 * @param nimi Henkilön nimi
	 */
	protected void SiirraNayttelija(String nimi) {
		if (nimi.isEmpty()) return;
		listChooserNayttelijatElokuvassa.add(nimi);
	}
	
	
	/**
	 * Roolitetaan elokuva
	 * @param elokuvanNimi elokuvan nimi
     */
	protected void lisaaHenkiloElokuvaan(String elokuvanNimi) {
		String[] loytyneet=listChooserNayttelijatElokuvassa.getKohteet();
		 List<Henkilo> henkilot= new ArrayList<Henkilo>();
		 
		 //Etsitään henkilöt nimen perusteella
		 for (String nimi: loytyneet)
			  henkilot.addAll(harkka.elokuvaDb.etsiHenkiloita(nimi));
		 
		 //Lisätään roolit
		 for (Henkilo henkilo: henkilot)
		 harkka.elokuvaDb.lisaa(harkka.elokuvaDb.annaElokuva(elokuvanNimi).getElokuvanNro(),  henkilo.getHenkilonId(),  1 );
	}
	
	
	/**
	 * Lisätään ohjaaja elokuvaan
	 * @param elokuvanNimi Elokuvan nimi
	 */
	protected void lisaaOhjaajaElokuvaan(String elokuvanNimi) {
		if (tarkista()== false) return; 

		List<Henkilo> henkilot= new ArrayList<Henkilo>();
		
		//Etsitään henkilö nimen perusteella ja lisätään ohjaaja rooleihin
		henkilot.addAll(harkka.elokuvaDb.etsiHenkiloita(comboBoxChooserOhjaaja.getSelectedText()));
		for (Henkilo henkilo: henkilot)
			harkka.elokuvaDb.lisaa(harkka.elokuvaDb.annaElokuva(elokuvanNimi).getElokuvanNro(),  henkilo.getHenkilonId(),  2 );
	}

	
	/**
	 * tarkistaa onko kaikki kohdat täytetty
	 * @return onko täytetty
	 */
	protected boolean tarkista() {
		if (editPanelElokuvanNimi.getText().isEmpty() || editPanelVuosi.getText().isEmpty() || textAreaKuvaus.getText().isEmpty() || 
				comboBoxChooserOhjaaja.getSelectedText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Täytä kaikki kohdat!");
			return false;
		}
		return true;
	}

	
	/**
     * Lisätään uusi elokuva tietorakenteeseen
     */
	protected void lisaaElokuva() {
		if (tarkista()== false) return;
		String 	elokuvanNimi =  editPanelElokuvanNimi.getText();
		int 	vuosi = 		Integer.parseInt(editPanelVuosi.getText());
		String 	juoni =			textAreaKuvaus.getText();
		int 	tahdet= 		Integer.parseInt(comboBoxTahdet.getSelectedItem().toString());
		
		try {
			harkka.elokuvaDb.lisaa(elokuvanNimi,vuosi,tahdet,juoni);
		} catch (TilaException e) {
			System.err.println("Ei voitu lisätä.  "+e);
		}

		lisaaHenkiloElokuvaan(elokuvanNimi);
		lisaaOhjaajaElokuvaan(elokuvanNimi);
			
		harkka.haeElokuvat();
		JOptionPane.showMessageDialog(null, "Lisätty!");
		dispose();
	}
	
	/**
	 * Haetaan kaikki henkilöt valikoihin
	 */
	protected void alusta() {
		listChooserKaikkiNayttelijat.clear();
		comboBoxChooserOhjaaja.clear();

		Collection<Henkilo> henkilot= harkka.elokuvaDb.etsiHenkiloita("*");
		for (Henkilo henkilo : henkilot) {
			listChooserKaikkiNayttelijat.add(henkilo.getHenkilonNimi());
			comboBoxChooserOhjaaja.add(henkilo.getHenkilonNimi());
		}
	}

	
	/**
	 * Lisätään uusi näyttelijä tietorakenteeseen
	 * @param nimi Nimi
	 */
	protected void lisaaHenkilo(String nimi) {
		if (editPanelUusiNayttelija.getText() == null)
			return;
		harkka.elokuvaDb.lisaaHenkilo(nimi);
		listChooserKaikkiNayttelijat.add(nimi);
		comboBoxChooserOhjaaja.add(nimi);
	}
}