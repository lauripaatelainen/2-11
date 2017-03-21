/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;

/**
 *
 * @author edii
 */
public class Siirtaja {
    private MuokattavaPelikentta kentta;
    
    public Siirtaja(MuokattavaPelikentta kentta) {
        this.kentta = kentta;
    }
    
    /**
     * Yhdistää pelikentällä allekkain olevat samat luvut ylöspäin.
     * 
     * Jos päällekkäin on kolme kakkosta, ylimmäksi luvuksi tulee nelonen
     * ja alimmaksi jää kakkonen.
     */
    public void yhdistaYlos() {
        for (int y = 0; y < kentta.koko() - 1; y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) != 0) {
                    if (kentta.arvo(x, y + 1) == kentta.arvo(x, y)) {
                        kentta.asetaArvo(x, y, kentta.arvo(x, y) * 2);
                        kentta.asetaArvo(x, y + 1, 0);
                    }
                }
            }
        }
    }
    
    /**
     * Yhdistää pelikentällä allekkain olevat samat luvut alaspäin.
     * 
     * Jos päällekkäin on kolme kakkosta, alimmaksi luvuksi tulee nelonen
     * ja ylimmäksi jää kakkonen.
     */
    public void yhdistaAlas() {
        /* yhdistetään allekkain olevat samat luvut */
        for (int y = kentta.koko() - 1; y > 0; y--) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) != 0) {
                    if (kentta.arvo(x, y - 1) == kentta.arvo(x, y)) {
                        kentta.asetaArvo(x, y, kentta.arvo(x, y) * 2);
                        kentta.asetaArvo(x, y - 1, 0);
                    }
                }
            }
        }
    }
    
    /**
     * Yhdistää pelikentällä vierekkäin olevat samat luvut vasemmalle.
     * 
     * Jos vierekkäin on kolme kakkosta, vasemmanpuoleiseksi tulee nelonen
     * ja nelosen oikealle puolelle kakkonen.
     */
    public void yhdistaVasemmalle() {
        /* yhdistetään vierekkäin olevat samat luvut */
        for (int x = 0; x < kentta.koko() - 1; x++) {
            for (int y = 0; y < kentta.koko(); y++) {
                if (kentta.arvo(x, y) != 0) {
                    if (kentta.arvo(x + 1, y) == kentta.arvo(x, y)) {
                        kentta.asetaArvo(x, y, kentta.arvo(x, y) * 2);
                        kentta.asetaArvo(x + 1, y, 0);
                    }
                }
            }
        }
    }
    
    /**
     * Yhdistää pelikentällä vierekkäin olevat samat luvut oikealle.
     * 
     * Jos vierekkäin on kolme kakkosta, oikeanpuoleiseksi tulee nelonen
     * ja nelosen vasemmalle puolelle kakkonen. 
     */
    public void yhdistaOikealle() {
        for (int x = kentta.koko() - 1; x > 0; x--) {
            for (int y = 0; y < kentta.koko(); y++) {
                if (kentta.arvo(x, y) != 0) {
                    if (kentta.arvo(x - 1, y) == kentta.arvo(x, y)) {
                        kentta.asetaArvo(x, y, kentta.arvo(x, y) * 2);
                        kentta.asetaArvo(x - 1, y, 0);
                    }
                }
            }
        }
    }
    
    /**
     * Siirtää lukuja ylöspäin niin paljon kuin pystyy yhdistämättä lukuja.
     */
    public void siirraYlospain() {
        for (int y = 0; y < kentta.koko(); y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) != 0) {
                    int y2 = y;
                    while (y2 > 0) {
                        if (kentta.arvo(x, y2 - 1) == 0) {
                            kentta.asetaArvo(x, y2 - 1, kentta.arvo(x, y2));
                            kentta.asetaArvo(x, y2, 0);
                            y2--;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /** 
     * Siirtää lukuja alaspäin niin paljon kuin pystyy yhdistämättä lukuja.
     */
    public void siirraAlaspain() {
        for (int y = kentta.koko() - 1; y >= 0; y--) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) != 0) {
                    int y2 = y;
                    while (y2 < kentta.koko() - 1) {
                        if (kentta.arvo(x, y2 + 1) == 0) {
                            kentta.asetaArvo(x, y2 + 1, kentta.arvo(x, y2));
                            kentta.asetaArvo(x, y2, 0);
                            y2++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Siirtää lukuja vasemmalle niin paljon kuin pystyy yhdistämättä lukuja.
     */
    public void siirraVasemmalle() {
        for (int x = 0; x < kentta.koko(); x++) {
            for (int y = 0; y < kentta.koko(); y++) {
                if (kentta.arvo(x, y) != 0) {
                    int x2 = x;
                    while (x2 > 0) {
                        if (kentta.arvo(x2 - 1, y) == 0) {
                            kentta.asetaArvo(x2 - 1, y, kentta.arvo(x2, y));
                            kentta.asetaArvo(x2, y, 0);
                            x2--;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Siirtää lukuja oikealle niin paljon kuin pystyy yhdistämättä lukuja.
     */
    public void siirraOikealle() {
        for (int x = kentta.koko() - 1; x >= 0; x--) {
            for (int y = 0; y < kentta.koko(); y++) {
                if (kentta.arvo(x, y) != 0) {
                    int x2 = x;
                    while (x2 < kentta.koko() - 1) {
                        if (kentta.arvo(x2 + 1, y) == 0) {
                            kentta.asetaArvo(x2 + 1, y, kentta.arvo(x2, y));
                            kentta.asetaArvo(x2, y, 0);
                            x2++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
}
