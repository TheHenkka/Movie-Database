package harkka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 *Näytetään ohjelmat tiedot dialogissa
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 11.2.2015
 */
public class TietojaDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final TietojaPanel tietojaPanel = new TietojaPanel();

	/**
	 * Launch the application.
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		try {
			TietojaDialog dialog = new TietojaDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TietojaDialog() {
	    setTitle("ElokuvaDB - Tietoja"); 
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(tietojaPanel);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton okButton = new JButton("OK"); 
				okButton.addActionListener(e -> dispose());				
				okButton.setActionCommand("OK"); 
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setVisible(true);
	}
}