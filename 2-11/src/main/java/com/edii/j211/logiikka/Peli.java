package com.edii.j211.logiikka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ylläpitää pelin tilannetta. 
 * 
 * @author edii
 */
public class Peli {
    private int kentanKoko;
    private int[][] kentta;
    private Random random;
    
    /**
     * Luo annetun kokoisen pelikentän. Luodussa pelikentässä on ensimmäinen
     * luku täytettynä.
     * 
     * @param kentanKoko Luotavan kentän koko (leveys ja korkeus)
     */
    public Peli(int kentanKoko) {
        if (kentanKoko <= 1) {
            throw new IllegalArgumentException("Kentän koon on oltava suurempi tai yhtä suuri kuin kaksi");
        }
        
        this.kentanKoko = kentanKoko;
        this.kentta = new int[kentanKoko][kentanKoko];
        this.random = new Random();
        
        lisaaLuku();
    }
    
    /**
     * Palauttaa kentän koon.
     * 
     * @return Kentän koko
     */
    public int getKentanKoko() {
        return this.kentanKoko;
    }
    
    /**
     * Palauttaa pelikentän.
     * 
     * Kenttä on taulukko kokonaislukutaulukoita. Ulomman taulukon, samoin kuin
     * sisempien taulukoiden koko, on luku jonka kokoisena kenttä alustettiin.
     * 
     * @return Pelikenttä
     */
    public int[][] getKentta() {
        return this.kentta;
    }
    
    /**
     * Yhdistää pelikentällä allekkain olevat samat luvut ylöspäin.
     * 
     * Jos päällekkäin on kolme kakkosta, ylimmäksi luvuksi tulee nelonen
     * ja alimmaksi jää kakkonen.
     */
    private void yhdistaYlos() {
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
    private void yhdistaAlas() {
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
    private void yhdistaVasemmalle() {
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
    private void yhdistaOikealle() {
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
     * Siirtää lukuja ylöspäin niin paljon kuin mahtuu.
     * 
     * Jokainen ruutu käydään läpi alkaen ylärivistä, jolloin voidaan
     * olla varmoja että pystysuunnassa ei jää tyhjiä aukkoja. Metodi
     * ei yhdistä törmääviä samoja lukuja.
     */
    private void siirraYlospain() {
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
     * Siirtää lukuja alaspäin niin paljon kuin mahtuu.
     * 
     * Jokainen ruutu käydään läpi alkaen alarivistä, jolloin voidaan
     * olla varmoja että pystysuunnassa ei jää tyhjiä aukkoja. Metodi
     * ei yhdistä törmääviä samoja lukuja.
     */
    private void siirraAlaspain() {
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
     * Siirtää lukuja vasemmalle niin paljon kuin mahtuu.
     * 
     * Jokainen ruutu käydään läpi alkaen vasemmanpuoleisesta sarakkeesta,
     * jolloin voidaan olla varmoja että vaakasuunnassa ei jää tyhjiä aukkoja.
     * Metodi ei yhdistä törmääviä samoja lukuja.
     */
    private void siirraVasemmalle() {
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
     * Siirtää lukuja oikealle niin paljon kuin mahtuu.
     * 
     * Jokainen ruutu käydään läpi alkaen oikeanpuoleisesta sarakkeesta,
     * jolloin voidaan olla varmoja että vaakasuunnassa ei jää tyhjiä aukkoja.
     * Metodi ei yhdistä törmääviä samoja lukuja. 
     */
    private void siirraOikealle() {
        /* siirretään lukuja niin paljon kuin mahtuu, ei yhdistetä vielä */
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
    
    /**
     * Siirto ylöspäin.
     * 
     * Luvut siirretään niin ylös kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään. 
     */
    public void ylos() {
        siirraYlospain();
        yhdistaYlos();
        siirraYlospain();
        
        lisaaLuku();
    }
    
    /**
     * Siirto alaspäin.
     * 
     * Luvut siirretään niin alas kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään.
     */
    public void alas() {
        siirraAlaspain();
        yhdistaAlas();
        siirraAlaspain();
        
        lisaaLuku();
    }
    
    /**
     * Siirto oikealle.
     * 
     * Luvut siirretään niin paljon oikealle kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään.
     */
    public void oikea() {
        siirraOikealle();
        yhdistaOikealle();
        siirraOikealle();
        
        lisaaLuku();
    }
    
    /**
     * Siirto vasemmalle.
     * 
     * Luvut siirretään niin paljon vasemmalle kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään.
     */
    public void vasen() {
        siirraVasemmalle();
        yhdistaVasemmalle();
        siirraVasemmalle();
        
        lisaaLuku();
    }
    
    /**
     * Lisää luvun satunnaiseen tyhjään ruutuun.
     * 
     * Uusi luku on 10% todennäköisyydellä 4 ja 90% todennäköisyydellä 2. 
     */
    public void lisaaLuku() {
        int uusiLuku = 2;
        
        /* uusi luku on 10% todennäköisyydellä 4, muuten 2 */
        if (random.nextInt(10) == 0) {
            uusiLuku = 4;
        }
        
        List<Integer> tyhjat = new ArrayList<>();
        for (int y = 0; y < kentanKoko; y++) {
            for (int x = 0; x < kentanKoko; x++) {
                if (kentta[y][x] == 0) {
                    tyhjat.add(y * kentanKoko + x);
                }
            }
        }
        
        if (!tyhjat.isEmpty()) {
            int valinta = tyhjat.get(random.nextInt(tyhjat.size()));
            int x = valinta % kentanKoko;
            int y = (valinta - x) / kentanKoko;

            kentta[y][x] = uusiLuku;
        }
    }
}
