package com.edii.j211;

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
    
    public int getKentanKoko() {
        return this.kentanKoko;
    }
    
    public int[][] getKentta() {
        return this.kentta;
    }
    
    private void yhdistaYlos() {
        /* yhdistetään allekkain olevat samat luvut */
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
    
    private void yhdistaOikealle() {
        /* yhdistetään vierekkäin olevat samat luvut */
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
    
    private void siirraAlaspain() {
        /* siirretään lukuja niin paljon kuin mahtuu, ei yhdistetä vielä */
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
    
    public void ylos() {
        siirraYlospain();
        yhdistaYlos();
        siirraYlospain();
        
        lisaaLuku();
    }
    
    public void alas() {
        siirraAlaspain();
        yhdistaAlas();
        siirraAlaspain();
        
        lisaaLuku();
    }
    
    public void oikea() {
        siirraOikealle();
        yhdistaOikealle();
        siirraOikealle();
        
        lisaaLuku();
    }
    
    public void vasen() {
        siirraVasemmalle();
        yhdistaVasemmalle();
        siirraVasemmalle();
        
        lisaaLuku();
    }
    
    /**
     * Lisää luvun satunnaiseen tyhjään ruutuun
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
