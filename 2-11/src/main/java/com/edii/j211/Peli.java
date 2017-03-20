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
    
    public void ylos() {
        /* siirretään lukuja niin paljon kuin mahtuu, ei yhdistetä vielä */
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
        
        /* siirretään lukuja uudestaan niin paljon kuin mahtuu */
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
        
        lisaaLuku();
    }
    
    public void alas() {
        
    }
    
    public void oikea() {
        
    }
    
    public void vasen() {
        
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
