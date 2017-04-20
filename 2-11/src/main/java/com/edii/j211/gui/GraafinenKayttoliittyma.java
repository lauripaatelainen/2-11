package com.edii.j211.gui;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.impl.PeliImpl;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Swingillä rakennettu graafinen käyttöliittymä 2^11 pelille.
 */
public class GraafinenKayttoliittyma extends JFrame {

    private Peli peli;
    private boolean peliOhi;

    private JButton uusiPeliNappi;
    private JLabel infoTeksti;
    private Pelialue pelialue;

    public GraafinenKayttoliittyma() {
        luoKayttoliittyma();
        alustaToiminnot();
    }

    private void luoKayttoliittyma() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        uusiPeliNappi = new JButton("Uusi peli");
        uusiPeliNappi.setAlignmentX(CENTER_ALIGNMENT);
        infoTeksti = new JLabel("Aloita peli painamalla nappia");
        infoTeksti.setAlignmentX(CENTER_ALIGNMENT);

        pelialue = null;

        add(uusiPeliNappi);
        add(infoTeksti);
        setSize(new Dimension(800, 800));
        setTitle("2^11");

        setLocationByPlatform(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() != KeyEvent.KEY_PRESSED) {
                    return false;
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    peli.ylos();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    peli.alas();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    peli.vasen();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    peli.oikea();
                } else {
                    return false;
                }
                
                paivita();
                return true;
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paivita();
            }
        });
    }

    private void alustaToiminnot() {
        uusiPeliNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UusiPeliDialogi dialogi = new UusiPeliDialogi(GraafinenKayttoliittyma.this);
                int tulos = dialogi.nayta();
                if (tulos >= 2) {
                    uusiPeli(tulos);
                }
            }
        });
    }

    public void uusiPeli(int koko) {
        peli = new PeliImpl(koko);
        peliOhi = false;
        luoPelialue();
    }

    private void luoPelialue() {
        if (pelialue != null) {
            this.remove(pelialue);
        }
        
        pelialue = new Pelialue(peli);
        this.add(pelialue);

        revalidate();
        paivita();
    }
    
    private void paivita() {
        if (!peliOhi && peli.peliOhi()) {
            peliOhi = true;
            JOptionPane.showMessageDialog(GraafinenKayttoliittyma.this, "Peli ohi! Sait " + peli.pisteet() + " pistettä.");
        }
        
        if (pelialue != null) {
            pelialue.paivita();
        }
        
        if (peliOhi) {
            infoTeksti.setText("Peli ohi! Pisteet: " + peli.pisteet());
        } else {
            infoTeksti.setText("Pisteet: " + peli.pisteet());
        }
    }

    public static void main(String[] args) {
        new GraafinenKayttoliittyma().setVisible(true);
    }
}
