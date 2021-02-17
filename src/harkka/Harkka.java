package harkka;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import luokat.Elokuva;
import luokat.ElokuvaDb;
import fi.jyu.mit.gui.EditPanel;
import fi.jyu.mit.gui.ListChooser;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * 
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 14.5.2015
 */
public class Harkka extends JFrame {

	
	/***/
	public final ListChooser listChooserValitseElokuva = new ListChooser();
	protected final ElokuvaDb elokuvaDb;

	private final JTextField txtHaeElokuva = new JTextField();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panelNappulat = new JPanel();
	private final JPanel panel = new JPanel();
	private final JButton btnNewButtonMuokkaa = new JButton("Muokkaa elokuvaa");
	private final JSplitPane splitPane = new JSplitPane();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnNewMenuTiedosto = new JMenu("Tiedosto");
	private final JMenu mnNewMenuMuokkaa = new JMenu("Muokkaa");
	private final JMenu mnNewMenuApua = new JMenu("Apua");
	private final JMenuItem mntmNewMenuItemTalleta = new JMenuItem("Talleta");
	private final JMenuItem mntmNewMenuItemTulosta = new JMenuItem("Tulosta...");
	private final JMenuItem mntmNewMenuItemLopeta = new JMenuItem("Lopeta");
	private final JMenuItem mntmNewMenuItemLisaaElokuva = new JMenuItem("Lis\u00E4\u00E4 uusi elokuva");
	private final JMenuItem mntmNewMenuItemTietoja = new JMenuItem("Tietoja...");
	private final JMenuItem mntmNewMenuItemApua = new JMenuItem("Apua");
	private final JPanel panelElokuva = new JPanel();
	private final JPanel panelElokuvanTietoja = new JPanel();
	private final JPanel panelElokuvanNimi = new JPanel();
	private final JLabel ELokuvanNimiLabel = new JLabel("\"ElokuvanNimi\"");
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final Component horizontalStrut = Box.createHorizontalStrut(31);
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
	private final JPanel panel_4 = new JPanel();
	private final EditPanel editPanelOhjaaja = new EditPanel();
	private final EditPanel editPanelVuosi = new EditPanel();
	private final EditPanel editPanelTahdet = new EditPanel();
	private final JPanel panelNayttelijat = new JPanel();
	private final Component verticalStrut_2 = Box.createVerticalStrut(30);
	private final ListChooser listChooserNayttelijat = new ListChooser();
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);
	private final JPanel panelKuvaus = new JPanel();
	private final JTextPane textPaneKuvaus = new JTextPane();
	private final Component verticalStrut_3 = Box.createVerticalStrut(20);
	private final Component horizontalStrut_2 = Box.createHorizontalStrut(20);
	private final JLabel lblNewLabel_1 = new JLabel("Kuvaus:");
	private final Component verticalStrut_4 = Box.createVerticalStrut(20);
	private final Component horizontalStrut_3 = Box.createHorizontalStrut(20);
	private final Component verticalStrut_5 = Box.createVerticalStrut(55);
	private final JButton btnNewMovie = new JButton("Lis\u00E4\u00E4 uusi elokuva");
	private final JButton btnPoistaElokuva = new JButton("Poista elokuva");

	/**
	 * Launch the application.
	 * @param args Ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Harkka frame = new Harkka();
					frame.lueTiedosto();
					frame.setVisible(true);
			}catch (Exception e) {
				e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Harkka() {
		elokuvaDb = new ElokuvaDb();
		txtHaeElokuva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				etsiElokuvia();
			}
		});
		
		txtHaeElokuva.setText("Hae elokuvaa...");
		txtHaeElokuva.setColumns(10);
		setMinimumSize(new Dimension(800, 480));
		setTitle("ElokuvaDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 480);

		setJMenuBar(menuBar);

		menuBar.add(mnNewMenuTiedosto);
		mntmNewMenuItemTalleta.addActionListener(e -> tallenna());

		mnNewMenuTiedosto.add(mntmNewMenuItemTalleta);
		mntmNewMenuItemTulosta.addActionListener(e -> eiToimi());

		mnNewMenuTiedosto.add(mntmNewMenuItemTulosta);
		mntmNewMenuItemLopeta.addActionListener(e -> System.exit(0));

		mnNewMenuTiedosto.add(mntmNewMenuItemLopeta);

		menuBar.add(mnNewMenuMuokkaa);
		mntmNewMenuItemLisaaElokuva.addActionListener(e -> lisaa());

		mnNewMenuMuokkaa.add(mntmNewMenuItemLisaaElokuva);

		menuBar.add(mnNewMenuApua);
		mntmNewMenuItemApua.addActionListener(e -> apua());

		mnNewMenuApua.add(mntmNewMenuItemApua);
		mntmNewMenuItemTietoja.addActionListener(e -> tietoja());
		mnNewMenuApua.add(mntmNewMenuItemTietoja);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(panelNappulat, BorderLayout.SOUTH);
		panelNappulat.setLayout(new BorderLayout(0, 0));

		panelNappulat.add(panel, BorderLayout.EAST);
		btnNewButtonMuokkaa.addActionListener(e -> muokkaa(listChooserValitseElokuva.getSelectedText()));

		btnNewMovie.addActionListener(e -> lisaa());
		panel.add(btnNewMovie);

		panel.add(btnNewButtonMuokkaa);
		panelNappulat.add(btnPoistaElokuva, BorderLayout.WEST);
		btnPoistaElokuva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				poistaElokuva(listChooserValitseElokuva.getSelectedText());
			}
		});

		contentPane.add(splitPane, BorderLayout.CENTER);
		listChooserValitseElokuva.getCaptionLabel().setText("Valitse elokuva");
		listChooserValitseElokuva.getList().setFont(new Font("Tahoma", Font.PLAIN, 11));
		listChooserValitseElokuva.getList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				aseta(listChooserValitseElokuva.getSelectedText());
			}
		});
		splitPane.setLeftComponent(listChooserValitseElokuva);
		
		listChooserValitseElokuva.add(txtHaeElokuva, BorderLayout.SOUTH);
		panelElokuva.setVerifyInputWhenFocusTarget(false);
		

		splitPane.setRightComponent(panelElokuva);
		panelElokuva.setLayout(new BorderLayout(0, 0));

		panelElokuva.add(panelElokuvanTietoja, BorderLayout.NORTH);
		panelElokuvanTietoja.setLayout(new BorderLayout(0, 0));

		panelElokuvanTietoja.add(panelElokuvanNimi, BorderLayout.WEST);
		panelElokuvanNimi.setLayout(new BorderLayout(0, 0));
		ELokuvanNimiLabel.setFont(new Font("SWTOR Trajan", Font.BOLD, 24));
		ELokuvanNimiLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ELokuvanNimiLabel.setVerticalAlignment(SwingConstants.BOTTOM);

		panelElokuvanNimi.add(ELokuvanNimiLabel, BorderLayout.EAST);

		panelElokuvanNimi.add(horizontalStrut, BorderLayout.WEST);

		panelElokuvanNimi.add(verticalStrut_1, BorderLayout.SOUTH);

		panelElokuvanTietoja.add(verticalStrut, BorderLayout.NORTH);

		panelElokuvanTietoja.add(panel_4, BorderLayout.SOUTH);
		editPanelOhjaaja.getLabel().setText("Ohjaaja");
		editPanelOhjaaja.getEdit().setEditable(false);
		editPanelOhjaaja.getLabel().setPreferredSize(new Dimension(80, 14));

		panel_4.add(editPanelOhjaaja);
		editPanelVuosi.getLabel().setText("Valmistumisvuosi");
		editPanelVuosi.getEdit().setEditable(false);
		editPanelVuosi.getLabel().setHorizontalAlignment(SwingConstants.LEADING);
		editPanelVuosi.getEdit().setPreferredSize(new Dimension(40, 20));
		editPanelVuosi.getLabel().setPreferredSize(new Dimension(110, 14));
		editPanelVuosi.getEdit().setAlignmentX(Component.LEFT_ALIGNMENT);
		editPanelVuosi.setText("1994");

		panel_4.add(editPanelVuosi);
		editPanelTahdet.getLabel().setText("T\u00E4hdet");
		editPanelTahdet.getEdit().setEditable(false);
		editPanelTahdet.getLabel().setAlignmentX(Component.LEFT_ALIGNMENT);
		editPanelTahdet.getLabel().setPreferredSize(new Dimension(60, 14));
		editPanelTahdet.setText("* * * *");

		panel_4.add(editPanelTahdet);

		panelElokuva.add(panelNayttelijat, BorderLayout.WEST);
		panelNayttelijat.setLayout(new BorderLayout(0, 0));

		panelNayttelijat.add(verticalStrut_2, BorderLayout.NORTH);
		listChooserNayttelijat.getCaptionLabel().setHorizontalAlignment(SwingConstants.CENTER);
		listChooserNayttelijat.getCaptionLabel().setText("N\u00E4yttelij\u00E4t");
		listChooserNayttelijat.getCaptionLabel().setVerticalAlignment(SwingConstants.TOP);

		panelNayttelijat.add(listChooserNayttelijat, BorderLayout.WEST);

		listChooserNayttelijat.add(horizontalStrut_1, BorderLayout.WEST);

		panelNayttelijat.add(verticalStrut_5, BorderLayout.SOUTH);

		panelElokuva.add(panelKuvaus, BorderLayout.CENTER);
		GridBagLayout gbl_panelKuvaus = new GridBagLayout();
		gbl_panelKuvaus.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelKuvaus.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelKuvaus.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelKuvaus.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		panelKuvaus.setLayout(gbl_panelKuvaus);

		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 1;
		gbc_verticalStrut_3.gridy = 0;
		panelKuvaus.add(verticalStrut_3, gbc_verticalStrut_3);

		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panelKuvaus.add(lblNewLabel_1, gbc_lblNewLabel_1);

		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_2.gridx = 0;
		gbc_horizontalStrut_2.gridy = 2;
		panelKuvaus.add(horizontalStrut_2, gbc_horizontalStrut_2);

		GridBagConstraints gbc_textPaneKuvaus = new GridBagConstraints();
		gbc_textPaneKuvaus.gridheight = 2;
		gbc_textPaneKuvaus.gridwidth = 2;
		gbc_textPaneKuvaus.insets = new Insets(0, 0, 5, 5);
		gbc_textPaneKuvaus.fill = GridBagConstraints.BOTH;
		gbc_textPaneKuvaus.gridx = 1;
		gbc_textPaneKuvaus.gridy = 2;
		textPaneKuvaus.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.LIGHT_GRAY));
		panelKuvaus.add(textPaneKuvaus, gbc_textPaneKuvaus);

		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_3.gridx = 3;
		gbc_horizontalStrut_3.gridy = 4;
		panelKuvaus.add(horizontalStrut_3, gbc_horizontalStrut_3);
		
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut_4.gridx = 2;
		gbc_verticalStrut_4.gridy = 5;
		panelKuvaus.add(verticalStrut_4, gbc_verticalStrut_4);
	}

	// ===============================================================================
	// Omat jutut


	/**
	 * Poistetaaan leffa ja sen roolit
	 * @param nimi leffan nimi joka poistetaan
	 */
	protected void poistaElokuva(String nimi) {
		if (nimi.isEmpty()) return;
		String tiedot = elokuvaDb.annaElokuva(nimi).toString();
		StringBuilder a = new StringBuilder(tiedot);
		int nro =Integer.valueOf(Mjonot.erota(a, '|'));
		elokuvaDb.poistaElokuva(nro);
		elokuvaDb.poistaNayttelijaElokuvasta(nro);
		haeElokuvat();
	}


	/**
	 * Laitetaan elokuvan tiedot paikoilleen kun sit‰ klikataan
	 * @param nimi Elokuvan nimi
	 */
	protected void aseta(String nimi){
		if (nimi.isEmpty()) return;
	
		String tiedot = elokuvaDb.annaElokuva(nimi).toString();
		
		StringBuilder a = new StringBuilder(tiedot);
		int nro =Integer.valueOf(Mjonot.erota(a, '|'));
		ELokuvanNimiLabel.setText(Mjonot.erota(a, '|'));
		editPanelVuosi.setText("" +Integer.valueOf(Mjonot.erota(a, '|')));
		editPanelTahdet.setText(""+ Integer.valueOf(Mjonot.erota(a, '|')));	
		textPaneKuvaus.setText(Mjonot.erota(a, '|'));
		
		listChooserNayttelijat.clear(); 
		 
		//Etsit‰‰n elokuvan n‰yttelij‰t
		Collection<Integer> henkiloId = elokuvaDb.etsiNayttelijat(nro,1);
		for (int id : henkiloId)
			listChooserNayttelijat.add(elokuvaDb.etsiHenkilo(id));
		
		//Etsit‰‰n elokuvan ohjaaja
		Collection<Integer> ohjaajaId = elokuvaDb.etsiNayttelijat(nro,2);
		for (int id : ohjaajaId)
			editPanelOhjaaja.setText(elokuvaDb.etsiHenkilo(id));
	}
	
	
	/**
	 * Hakupalkin haku
	 */
	protected void etsiElokuvia() {
		listChooserValitseElokuva.clear();
		String ehto = txtHaeElokuva.getText();
		Collection<Elokuva> elokuvat = elokuvaDb.etsiElokuvia(ehto);
		for (Elokuva elokuva : elokuvat) 
			listChooserValitseElokuva.add(elokuva.getNimi());
	}
	
	/**
	 * T‰m‰ hakee kaikki elokuvat. Aina kun jotain muuttuu
	 */
	protected void haeElokuvat() {
		listChooserValitseElokuva.clear();

		Collection<Elokuva> elokuvat;
		elokuvat = elokuvaDb.etsiElokuvia("*");
		for (Elokuva elokuva : elokuvat) 
			listChooserValitseElokuva.add(elokuva.getNimi());
	}
	
	
	/**
	 * Luetaan kaikki tiedostot
	 */
	protected void lueTiedosto() {
         elokuvaDb.lueTiedostot();
         haeElokuvat();
     }
	

	/**
	 * Tallentaa kaikki muutokset
	 */
	protected void tallenna() {
		elokuvaDb.talleta();
		JOptionPane.showMessageDialog(null, "Tallennettu!");
	}

	
	/**
	 * N‰ytt‰‰ TietojaDialogin
	 */
	@SuppressWarnings("unused")
	protected void tietoja() {
		new TietojaDialog();
	}
	

	/**
	 * N‰ytt‰‰ ApuaDialogin
	 */
	@SuppressWarnings("unused")
	protected void apua() {
		new ApuaDialog();
	}

	
	/**
	 * N‰ytt‰‰ MuokkaaDialogin
	 * @param nimi Elokuvan nimi, jota halutaan muokata
	 */
	@SuppressWarnings("unused")
	protected void muokkaa(String nimi) {
		new MuokkaaDialog(this, nimi);
	}
	
	/**
	 * N‰ytt‰‰ LisaaDialogin
	 */
	@SuppressWarnings("unused")
	protected void lisaa() {
		new LisaaDialog(this);
	}
	
	/**
	 * N‰ytt‰‰ eitoimi popupin
	 */
	private void eiToimi() {
		JOptionPane.showMessageDialog(null, "Ei toimi!");
	}
}