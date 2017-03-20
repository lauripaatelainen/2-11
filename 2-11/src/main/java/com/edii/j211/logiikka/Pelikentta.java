package com.edii.j211.logiikka;

/**
 * Ylläpitää pelikentän tilanteen ja vastaa kentän siirroista. 
 * 
 * @author edii
 */
public class Pelikentta {
    private int kentanKoko;
    private int[][] kentta;
    
    public Pelikentta(int kentanKoko) {
        this.kentanKoko = kentanKoko;
        this.kentta = new int[kentanKoko][kentanKoko];
    }
    
    public int getKentanKoko() {
        return kentanKoko;
    }
    
    public void asetaRuutu(int x, int y, int arvo) {
        this.kentta[y][x] = arvo;
    }
    
    public int ruudunArvo(int x, int y) {
        return this.kentta[y][x];
    }
    
    /**
     * Yhdistää pelikentällä allekkain olevat samat luvut ylöspäin.
     * 
     * Jos päällekkäin on kolme kakkosta, ylimmäksi luvuksi tulee nelonen
     * ja alimmaksi jää kakkonen.
     */
    public void yhdistaYlos() {
        for (int y = 0; y < kentanKoko - 1; y++) {
            for (int x = 0; x < kentanKoko; x++) {
                if (kentta[y][x] != 0) {
                    if (kentta[y + 1][x] == kentta[y][x]) {
                        kentta[y][x] *= 2;
                        kentta[y + 1][x] = 0;
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
        for (int y = kentanKoko - 1; y > 0; y--) {
            for (int x = 0; x < kentanKoko; x++) {
                if (kentta[y][x] != 0) {
                    if (kentta[y - 1][x] == kentta[y][x]) {
                        kentta[y][x] *= 2;
                        kentta[y - 1][x] = 0;
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
        for (int x = 0; x < kentanKoko - 1; x++) {
            for (int y = 0; y < kentanKoko; y++) {
                if (kentta[y][x] != 0) {
                    if (kentta[y][x + 1] == kentta[y][x]) {
                        kentta[y][x] *= 2;
                        kentta[y][x + 1] = 0;
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
        for (int x = kentanKoko - 1; x > 0; x--) {
            for (int y = 0; y < kentanKoko; y++) {
                if (kentta[y][x] != 0) {
                    if (kentta[y][x - 1] == kentta[y][x]) {
                        kentta[y][x] *= 2;
                        kentta[y][x - 1] = 0;
                    }
                }
            }
        }
    }
    
    /**
     * Siirtää lukuja ylöspäin niin paljon kuin pystyy yhdistämättä lukuja.
     */
    public void siirraYlospain() {
        for (int y = 0; y < kentanKoko; y++) {
            for (int x = 0; x < kentanKoko; x++) {
                if (kentta[y][x] != 0) {
                    int y2 = y;
                    while (y2 > 0) {
                        if (kentta[y2 - 1][x] == 0) {
                            kentta[y2 - 1][x] = kentta[y2][x];
                            kentta[y2][x] = 0;
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
        for (int y = kentanKoko - 1; y >= 0; y--) {
            for (int x = 0; x < kentanKoko; x++) {
                if (kentta[y][x] != 0) {
                    int y2 = y;
                    while (y2 < kentanKoko - 1) {
                        if (kentta[y2 + 1][x] == 0) {
                            kentta[y2 + 1][x] = kentta[y2][x];
                            kentta[y2][x] = 0;
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
        for (int x = 0; x < kentanKoko; x++) {
            for (int y = 0; y < kentanKoko; y++) {
                if (kentta[y][x] != 0) {
                    int x2 = x;
                    while (x2 > 0) {
                        if (kentta[y][x2 - 1] == 0) {
                            kentta[y][x2 - 1] = kentta[y][x2];
                            kentta[y][x2] = 0;
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
        for (int x = kentanKoko - 1; x >= 0; x--) {
            for (int y = 0; y < kentanKoko; y++) {
                if (kentta[y][x] != 0) {
                    int x2 = x;
                    while (x2 < kentanKoko - 1) {
                        if (kentta[y][x2 + 1] == 0) {
                            kentta[y][x2 + 1] = kentta[y][x2];
                            kentta[y][x2] = 0;
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
