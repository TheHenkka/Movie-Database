package harkka;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.BorderLayout;

/**
 * @author Hentter Eloranta, Nicolas Boucht
 * @version 18.2.2015
 *
 */
public class ApuaPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JLabel lblElokuvadbApua = new JLabel("ElokuvaDb - Apua"); 
    private final JPanel panel = new JPanel();
    private final JTextPane txtpnApufield = new JTextPane();
    private final JPanel panel_1 = new JPanel();

    /**
     * Create the panel.
     */
    public ApuaPanel() {
        setLayout(null);
        panel.setBounds(107, 11, 233, 46);
        
        add(panel);
        panel.add(lblElokuvadbApua);
        lblElokuvadbApua.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        panel_1.setBounds(10, 67, 430, 222);
        
        add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        txtpnApufield.setEditable(false);
        panel_1.add(txtpnApufield);
        txtpnApufield.setText("Paina Tiedosto -> Lopeta, niin ohjelma menee kiinni.\r\nPaina Apua -> Tietoja, "
        		+ "niin saat tietoja ohjelmasta.\r\nPaina Apua -> Apua, niin saat t\u00E4m\u00E4n ikkunan.\r\nMik\u00E4\u00E4n"
        		+ " muu nappi ei toimi.\r\nEi sitten mik\u00E4\u00E4n.");
    }
}