package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;

/**
 * Luokka, joka vastaa pelikentän lukujen siirto- ja yhdistysoperaatioista.
 * 
 * Tarjoaa jokaiseen suuntaan metodi siirroille ja vierekkäisten samojen lukujen
 * yhdistämiselle. 
 */
public class Siirtaja {
    private MuokattavaPelikentta kentta;
    private MuokattavaPelikentta kierto1;
    private MuokattavaPelikentta kierto2;
    private MuokattavaPelikentta kierto3;
    
    public Siirtaja(MuokattavaPelikentta kentta) {
        this.kentta = kentta;
        this.kierto1 = new KierrettyPelikentta(kentta);
        this.kierto2 = new KierrettyPelikentta(kierto1);
        this.kierto3 = new KierrettyPelikentta(kierto2);
    }
    
    /**
     * Siirtäjä luokan sisäinen apumetodi.
     * 
     * Varsinainen yhdistyslogiikka on tässä metodissa. Muut yhdistysmetodit
     * kutsuvat tätä metodia oikealla kentällä (alkuperäinen tai kierretty).
     * 
     * @param kentta Pelikenttä jonka lukuja yhdistetään
     * @return Yhdistämisestä ansaittu pistemäärä
     */
    private static int yhdista(MuokattavaPelikentta kentta) {
        int pisteet = 0;
        for (int y = 0; y < kentta.koko() - 1; y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) != 0) {
                    if (kentta.arvo(x, y + 1) == kentta.arvo(x, y)) {
                        kentta.asetaArvo(x, y, kentta.arvo(x, y) * 2);
                        kentta.asetaArvo(x, y + 1, 0);
                        pisteet += kentta.arvo(x, y);
                    }
                }
            }
        }
        return pisteet;
    }
    
    /**
     * Siirtäjä luokan sisäinen apumetodi.
     * 
     * Varsinainen siirtologiikka on tässä metodissa. Muut siirtometodit
     * kutsuvat tätä metodia oikealla kentällä (alkuperäinen tai kierretty).
     * 
     * @param kentta Pelikenttä jonka lukuja siirretään
     * @return Siirretiinkö yhtään lukua
     */
    private static boolean siirra(MuokattavaPelikentta kentta) {
        boolean out = false;
        for (int y = 0; y < kentta.koko(); y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) != 0) {
                    int y2 = y;
                    while (y2 > 0) {
                        if (kentta.arvo(x, y2 - 1) == 0) {
                            kentta.asetaArvo(x, y2 - 1, kentta.arvo(x, y2));
                            kentta.asetaArvo(x, y2, 0);
                            y2--;
                            out = true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return out;
    }
    
    /**
     * Yhdistää pelikentällä allekkain olevat samat luvut ylöspäin.
     * 
     * Jos päällekkäin on kolme kakkosta, ylimmäksi luvuksi tulee nelonen
     * ja alimmaksi jää kakkonen.
     * 
     * @return Ansaittu pistemäärä
     */
    public int yhdistaYlos() {
        return yhdista(kentta);
    }
    
    /**
     * Yhdistää pelikentällä allekkain olevat samat luvut alaspäin.
     * 
     * Jos päällekkäin on kolme kakkosta, alimmaksi luvuksi tulee nelonen
     * ja ylimmäksi jää kakkonen.
     * 
     * @return Ansaittu pistemäärä
     */
    public int yhdistaAlas() {
        return yhdista(kierto2);
    }
    
    /**
     * Yhdistää pelikentällä vierekkäin olevat samat luvut vasemmalle.
     * 
     * Jos vierekkäin on kolme kakkosta, vasemmanpuoleiseksi tulee nelonen
     * ja nelosen oikealle puolelle kakkonen.
     * 
     * @return Ansaittu pistemäärä
     */
    public int yhdistaVasemmalle() {
        return yhdista(kierto1);
    }
    
    /**
     * Yhdistää pelikentällä vierekkäin olevat samat luvut oikealle.
     * 
     * Jos vierekkäin on kolme kakkosta, oikeanpuoleiseksi tulee nelonen
     * ja nelosen vasemmalle puolelle kakkonen. 
     * 
     * @return Ansaittu pistemäärä
     */
    public int yhdistaOikealle() {
        return yhdista(kierto3);
    }
    
    /**
     * Siirtää lukuja ylöspäin niin paljon kuin pystyy yhdistämättä lukuja.
     * 
     * @return Siirrettiinkö yhtään lukua
     */
    public boolean siirraYlospain() {
        return siirra(kentta);
    }
    
    /** 
     * Siirtää lukuja alaspäin niin paljon kuin pystyy yhdistämättä lukuja.
     * 
     * @return Siirrettiinkö yhtään lukua
     */
    public boolean siirraAlaspain() {
        return siirra(kierto2);
    }
    
    /**
     * Siirtää lukuja vasemmalle niin paljon kuin pystyy yhdistämättä lukuja.
     * 
     * @return Siirrettiinkö yhtään lukua
     */
    public boolean siirraVasemmalle() {
        return siirra(kierto1);
    }
    
    /**
     * Siirtää lukuja oikealle niin paljon kuin pystyy yhdistämättä lukuja.
     * 
     * @return Siirrettiinkö yhtään lukua
     */
    public boolean siirraOikealle() {
        return siirra(kierto3);
    }
}
