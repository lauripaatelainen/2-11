package com.edii.j211.gui;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.Pelikentta;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;

/**
 * Pelialue, jolla graafinen käyttöliittymä renderöi pelin tilanteen.
 *
 * @see GraafinenKayttoliittyma
 */
public class Pelialue extends JPanel {

    /**
     * Peli, jota tämä Pelialue kuvastaa.
     */
    private Peli peli;

    /**
     * Pelin jokainen ruutu näytetään omana JLabelina, tämä taulukko säilyttää
     * jokaista ruutua edustavan JLabelin.
     */
    private JLabel[][] ruudut;

    /**
     * Ainoa konstruktori pelialueen luomiseen. Saa parametrinaan pelin, jonka
     * avulla määräytyy minkä kokoinen pelialue tulee näyttää ja mikä pelin
     * tilanne on.
     *
     * @param peli Peli, joka esitetään tällä pelialueella
     * @see com.edii.j211.logiikka.Peli
     */
    public Pelialue(Peli peli) {
        super(new GridLayout(peli.koko(), peli.koko()));
        this.peli = peli;

        ruudut = new JLabel[peli.koko()][peli.koko()];

        for (int y = 0; y < peli.koko(); y++) {
            for (int x = 0; x < peli.koko(); x++) {
                ruudut[y][x] = new JLabel("");
                this.add(ruudut[y][x]);
                ruudut[y][x].setHorizontalAlignment(CENTER);
                ruudut[y][x].setVerticalAlignment(CENTER);
                ruudut[y][x].setBackground(Color.white);
                ruudut[y][x].setOpaque(true);
            }
        }
    }

    /**
     * Pitää sisällään eri pistearvojen näyttöön tarkoitetut edusvärit (numeron
     * väri).
     */
    private static final Map<Integer, Integer> EDUSVARIT = new HashMap<>();

    /**
     * Pitää sisällään eri pistearvojen näyttöön tarkoitetut taustavärit.
     */
    private static final Map<Integer, Integer> TAUSTAVARIT = new HashMap<>();

    static {
        EDUSVARIT.put(2, 0x776e65);
        EDUSVARIT.put(4, 0x776e65);
        TAUSTAVARIT.put(0, 0xcdc1b4);
        TAUSTAVARIT.put(2, 0xeee4da);
        TAUSTAVARIT.put(4, 0xede0c8);
        TAUSTAVARIT.put(8, 0xf2b179);
        TAUSTAVARIT.put(16, 0xf59563);
        TAUSTAVARIT.put(32, 0xf67c5f);
        TAUSTAVARIT.put(64, 0xf65e3b);
        TAUSTAVARIT.put(128, 0xedcf72);
        TAUSTAVARIT.put(256, 0xedcc61);
        TAUSTAVARIT.put(512, 0xedc850);
        TAUSTAVARIT.put(1024, 0xedc53f);
        TAUSTAVARIT.put(2048, 0xedc22e);
    }

    /**
     * Päivitä pelialue vastaamaan nykytilannetta.
     */
    public void paivita() {
        Pelikentta kentta = peli.pelikentta();

        for (int y = 0; y < peli.koko(); y++) {
            for (int x = 0; x < peli.koko(); x++) {
                int arvo = kentta.arvo(x, y);
                Color fg = new Color(EDUSVARIT.getOrDefault(arvo, 0xf9f6f2));
                Color bg = new Color(TAUSTAVARIT.getOrDefault(arvo, 0x000000));

                int height = ruudut[y][x].getHeight();
                ruudut[y][x].setFont(new Font("Arial", Font.BOLD, height / 3));

                if (arvo == 0) {
                    ruudut[y][x].setText("");
                } else {
                    ruudut[y][x].setText(Integer.toString(arvo));
                }

                ruudut[y][x].setForeground(fg);
                ruudut[y][x].setBackground(bg);
            }
        }

        this.repaint();
        this.revalidate();
    }
}
