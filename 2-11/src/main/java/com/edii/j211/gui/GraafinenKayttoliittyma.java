package com.edii.j211.gui;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.impl.PeliImpl;
import com.edii.j211.pisterekisteri.PisteRekisteri;
import com.edii.j211.pisterekisteri.PisteRekisteriIO;
import com.edii.j211.pisterekisteri.Tulos;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
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
    private JButton tuloksetNappi;
    private JLabel infoTeksti;
    private Pelialue pelialue;
    private PisteRekisteri pisteRekisteri;

    /**
     * Oletuskonstruktori uuden käyttöliittymän luomiseen.
     */
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

    /**
     * Luo käyttöliittymäkomponentit.
     */
    private void luoKayttoliittyma() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        uusiPeliNappi = new JButton("Uusi peli");
        uusiPeliNappi.setAlignmentX(CENTER_ALIGNMENT);
        tuloksetNappi = new JButton("Tulokset");
        tuloksetNappi.setAlignmentX(CENTER_ALIGNMENT);
        infoTeksti = new JLabel("Aloita peli painamalla nappia");
        infoTeksti.setAlignmentX(CENTER_ALIGNMENT);
        pelialue = null;
        add(uusiPeliNappi);
        add(tuloksetNappi);
        add(infoTeksti);
        setSize(new Dimension(800, 800));
        setTitle("2^11");
        setLocationByPlatform(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(java.awt.event.KeyEvent e) {
                if (e.getID() != java.awt.event.KeyEvent.KEY_PRESSED) {
                    return false;
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
                    peli.ylos();
                } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
                    peli.alas();
                } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
                    peli.vasen();
                } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
                    peli.oikea();
                } else {
                    return false;
                }
                paivita();
                return true;
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                paivita();
            }
        });
    }

    /**
     * Kytkee painikkeisiin toiminnot.
     */
    private void alustaToiminnot() {
        uusiPeliNappi.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                UusiPeliDialogi dialogi = new UusiPeliDialogi(GraafinenKayttoliittyma.this);
                int tulos = dialogi.nayta();
                if (tulos >= 2) {
                    uusiPeli(tulos);
                }
            }
        });
        tuloksetNappi.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                TuloksetDialogi dialogi = new TuloksetDialogi(GraafinenKayttoliittyma.this, pisteRekisteri);
                dialogi.nayta();
            }
        });
    }

    /**
     * Luo uuden pelin ja kytkee sen käyttöliittymään.
     *
     * @param koko Pelikentän koko
     */
    public void uusiPeli(int koko) {
        peli = new PeliImpl(koko);
        peliOhi = false;
        luoPelialue();
    }

    /**
     * Luo pelialueen.
     */
    private void luoPelialue() {
        if (pelialue != null) {
            this.remove(pelialue);
        }
        pelialue = new Pelialue(peli);
        this.add(pelialue);
        revalidate();
        paivita();
    }

    /**
     * Päivittää muuttuvat käyttöliittymän komponentit, kuten pelialueen ja
     * infotekstin.
     */
    private void paivita() {
        if (!peliOhi && peli != null && peli.peliOhi()) {
            infoTeksti.setText("Peli ohi! Pisteet: " + peli.pisteet());
            peliOhi = true;
            List<Tulos> tulokset = pisteRekisteri.pistetaulu(peli.koko());
            int sijoitus = pisteRekisteri.sijoitus(peli.koko(), peli.pisteet());
            if (sijoitus == -1) {
                JOptionPane.showMessageDialog(this, "Peli ohi! Sait " + peli.pisteet() + " pistettä. Et sijoittunut top-listalle. ");
            } else {
                String nimi = JOptionPane.showInputDialog(GraafinenKayttoliittyma.this, "Peli ohi! Sait " + peli.pisteet() + " pistettä ja sijoituit sijalle " + sijoitus + ". Anna nimi top-listaa varten.");
                Tulos tulos = new Tulos(nimi, peli.pisteet(), peli.koko());
                pisteRekisteri.lisaaTulos(tulos);
                try {
                    tallennaPisterekisteri();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Pisterekisterin tallennus epäonnistui: " + e.getMessage(), "Virhe", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            infoTeksti.setText("Pisteet: " + ((peli != null) ? peli.pisteet() : 0));
        }
        if (pelialue != null) {
            pelialue.paivita();
        }
    }

    /**
     * Lataa pisterekisterin ennalta määritellystä tiedostosta.
     *
     * @throws IOException mikäli tulosrekisterin käsittelyssä tapahtuu virhe.
     * @see GraafinenKayttoliittyma#TULOSTIEDOSTO
     */
    private void lataaPisterekisteri() throws IOException {
        pisteRekisteri = PisteRekisteriIO.avaa(TULOSTIEDOSTO);
    }

    /**
     * Tallentaa pisterekisterin ennalta määriteltyyn tiedostoon
     *
     * @throws IOException mikäli tulosrekisterin käsittelyssä tapahtuu virhe.
     * @see GraafinenKayttoliittyma#TULOSTIEDOSTO
     */
    private void tallennaPisterekisteri() throws IOException {
        PisteRekisteriIO.tallenna(pisteRekisteri, TULOSTIEDOSTO);
    }

    /**
     * Pääohjelma graafisen käyttöliittymän käynnistykseen.
     *
     * @param args Komentoriviltä saadut parametrit
     */
    public static void main(String[] args) {
        new GraafinenKayttoliittyma().setVisible(true);
    }
}
