package harkka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Paneeli jossa on ohjelman tiedot
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 12.2.2015
 */
public class TietojaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblNewLabel = new JLabel("ElokuvaDb"); 
	private final JPanel panel_2 = new JPanel();
	private final JLabel lblNewLabel_1 = new JLabel("Hentter Eloranta, Nicolas Boucht"); 
	private final JLabel lblNewLabel_2 = new JLabel("Versio 0.001"); 

	/**
	 * Create the panel.
	 */
	public TietojaPanel() {
		setLayout(new BorderLayout(0, 0));
		
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(panel_1, BorderLayout.NORTH);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30)); 
		
		panel_1.add(lblNewLabel);
		
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_2.add(lblNewLabel_1);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_2.add(lblNewLabel_2);
	}
}