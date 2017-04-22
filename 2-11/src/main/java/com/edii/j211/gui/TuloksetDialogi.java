package com.edii.j211.gui;

import com.edii.j211.pisterekisteri.PisteRekisteri;
import com.edii.j211.pisterekisteri.Tulos;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 */
public final class TuloksetDialogi extends JDialog {
    public TuloksetDialogi(Frame owner, PisteRekisteri pisteRekisteri) {
        super(owner);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setLocationByPlatform(true);

        JLabel tulostaulu = new JLabel();
        tulostaulu.setFont(new Font("Arial", Font.PLAIN, 12));
        
        Map<Integer, List<Tulos>> tulokset = pisteRekisteri.pistetaulut();
        List<Integer> keys = new ArrayList<>(tulokset.keySet());
        Collections.sort(keys);
        String teksti = "<html>";
        for (int key : keys) {
            teksti += "<b><nobr>Koko: " + key + "</nobr></b><br>";
            int i = 1;
            for (Tulos tulos : tulokset.get(key)) {
                teksti += "<nobr>&nbsp;&nbsp;&nbsp;&nbsp;" + i + ". " + tulos.getNimi() + ": " + tulos.getPisteet() + " pistettä</nobr><br>";
                i += 1;
            }
        }
        teksti += "</html>";
        tulostaulu.setText(teksti);

        JButton suljeNappi = new JButton("Sulje");

        JPanel nappirivi = new JPanel();
        nappirivi.setLayout(new FlowLayout(FlowLayout.CENTER));
        nappirivi.add(suljeNappi);

        suljeNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });
        this.add(tulostaulu);
        this.add(nappirivi);
        this.pack();
        setTitle("Tulokset");
        setMinimumSize(new Dimension(800, 600));
    }
    
    public void nayta() {
        setVisible(true);
    }
}
