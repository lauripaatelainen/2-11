package com.edii.j211.gui;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.impl.PeliImpl;
import com.edii.j211.pisterekisteri.PisteRekisteri;
import com.edii.j211.pisterekisteri.PisteRekisteriIO;
import com.edii.j211.pisterekisteri.Tulos;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Swingillä rakennettu graafinen käyttöliittymä 2^11 pelille.
 */
public class GraafinenKayttoliittyma extends JFrame {
    private static final String TULOSTIEDOSTO = "Tulokset.txt";

    private Peli peli;
    private boolean peliOhi;

    private JButton uusiPeliNappi;
    private JLabel infoTeksti;
    private Pelialue pelialue;
    
    private PisteRekisteri pisteRekisteri;

    public GraafinenKayttoliittyma() {
        luoKayttoliittyma();
        alustaToiminnot();
        try {
            lataaPisterekisteri();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Pisterekisterin lataus epäonnistui: " + e.getMessage(), "Virhe", JOptionPane.ERROR_MESSAGE);
            pisteRekisteri = new PisteRekisteri();
        }
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
        if (!peliOhi && peli != null && peli.peliOhi()) {
            peliOhi = true;
            List<Tulos> tulokset = pisteRekisteri.pistetaulu(peli.koko());
            int sijoitus = -1;
            
            if (tulokset == null || tulokset.isEmpty()) {
                sijoitus = 1;
            } else if (tulokset.size() >= 10 && tulokset.get(tulokset.size() - 1).getPisteet() >= peli.pisteet()) {
                JOptionPane.showMessageDialog(this, "Peli ohi! Sait " + peli.pisteet() + " pistettä. Et sijoittunut top-listalle. ");
            } else {
                sijoitus = tulokset.size();
                for (int i = 0; i < tulokset.size(); i++) {
                    if (tulokset.get(i).getPisteet() < peli.pisteet()) {
                        sijoitus = i + 1;
                        break;
                    }
                }
            }
            
            if (sijoitus != -1) {
                String nimi = JOptionPane.showInputDialog(GraafinenKayttoliittyma.this, "Peli ohi! Sait " + peli.pisteet() + " pistettä ja sijoituit sijalle " + sijoitus + ". Anna nimi top-listaa varten.");
                Tulos tulos = new Tulos(nimi, peli.pisteet(), peli.koko());
                pisteRekisteri.lisaaTulos(tulos);
                try {
                    tallennaPisterekisteri();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Pisterekisterin tallennus epäonnistui: " + e.getMessage(), "Virhe", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        if (pelialue != null) {
            pelialue.paivita();
        }
        
        if (peliOhi) {
            infoTeksti.setText("Peli ohi! Pisteet: " + peli.pisteet());
        } else {
            infoTeksti.setText("Pisteet: " + ((peli != null) ? peli.pisteet() : 0));
        }
    }
    
    private void lataaPisterekisteri() throws IOException {
        pisteRekisteri = PisteRekisteriIO.avaa(TULOSTIEDOSTO);
    }
    
    private void tallennaPisterekisteri() throws IOException {
        PisteRekisteriIO.tallenna(pisteRekisteri, TULOSTIEDOSTO);
    }

    public static void main(String[] args) {
        new GraafinenKayttoliittyma().setVisible(true);
    }
}
