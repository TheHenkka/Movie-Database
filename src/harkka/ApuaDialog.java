package harkka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

/**
 * Näytetään ohjelmat tiedot dialogissa
 * @author Hentter Eloranta. modified by Nicolas Boucht
 * @version 17.2.2015
 */
public class ApuaDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private final ApuaPanel ApuaPanel = new ApuaPanel();

    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        try {
            ApuaDialog dialog = new ApuaDialog();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ApuaDialog() {
        setTitle("ElokuvaDB - Apua");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
        contentPanel.add(ApuaPanel);
        ApuaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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