package com.edii.j211.gui;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 */
public final class UusiPeliDialogi extends JDialog {
    private int tulos = -1;

    public UusiPeliDialogi(Frame owner) {
        super(owner);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setLocationByPlatform(true);

        JPanel rivi = new JPanel();
        rivi.setLayout(new FlowLayout(FlowLayout.CENTER));
        rivi.add(new JLabel("Kentän koko: "));
        JTextField koko = new JTextField();
        koko.setColumns(5);
        koko.setText("4");
        rivi.add(koko);

        JButton okNappi = new JButton("OK");
        JButton peruutaNappi = new JButton("Peruuta");

        JPanel nappirivi = new JPanel();
        nappirivi.setLayout(new FlowLayout(FlowLayout.CENTER));
        nappirivi.add(okNappi);
        nappirivi.add(peruutaNappi);

        okNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kokoStr = koko.getText();
                try {
                    int koko = Integer.parseInt(kokoStr);
                    if (koko < 2 || koko >= 100) {
                        throw new Exception();
                    } else {
                        tulos = koko;
                        dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(UusiPeliDialogi.this, "Virheellinen koko!", "Virhe", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        peruutaNappi.addActionListener(new ActionListener() {
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

        this.add(rivi);
        this.add(nappirivi);
        this.pack();
    }
    
    public int nayta() {
        setVisible(true);
        return tulos;
    }
}
