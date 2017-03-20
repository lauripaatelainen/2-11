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
    private Pelikentta kentta;
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
        
        this.kentta = new Pelikentta(kentanKoko);
        this.random = new Random();
        
        lisaaLuku();
    }
    
    /**
     * Palauttaa kentän koon.
     * 
     * @return Kentän koko
     */
    public int getKentanKoko() {
        return this.kentta.getKentanKoko();
    }
    
    /**
     * Palauttaa pelikentän.
     * 
     * @return Pelikenttä
     */
    public Pelikentta getKentta() {
        return this.kentta;
    }
    
    /**
     * Siirto ylöspäin.
     * 
     * Luvut siirretään niin ylös kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään. 
     */
    public void ylos() {
        this.kentta.siirraYlospain();
        this.kentta.yhdistaYlos();
        this.kentta.siirraYlospain();
        
        lisaaLuku();
    }
    
    /**
     * Siirto alaspäin.
     * 
     * Luvut siirretään niin alas kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään.
     */
    public void alas() {
        this.kentta.siirraAlaspain();
        this.kentta.yhdistaAlas();
        this.kentta.siirraAlaspain();
        
        lisaaLuku();
    }
    
    /**
     * Siirto oikealle.
     * 
     * Luvut siirretään niin paljon oikealle kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään.
     */
    public void oikea() {
        this.kentta.siirraOikealle();
        this.kentta.yhdistaOikealle();
        this.kentta.siirraOikealle();
        
        lisaaLuku();
    }
    
    /**
     * Siirto vasemmalle.
     * 
     * Luvut siirretään niin paljon vasemmalle kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään.
     */
    public void vasen() {
        this.kentta.siirraVasemmalle();
        this.kentta.yhdistaVasemmalle();
        this.kentta.siirraVasemmalle();
        
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
        for (int y = 0; y < getKentanKoko(); y++) {
            for (int x = 0; x < getKentanKoko(); x++) {
                if (kentta.ruudunArvo(x, y) == 0) {
                    tyhjat.add(y * getKentanKoko() + x);
                }
            }
        }
        
        if (!tyhjat.isEmpty()) {
            int valinta = tyhjat.get(random.nextInt(tyhjat.size()));
            int x = valinta % getKentanKoko();
            int y = (valinta - x) / getKentanKoko();

            kentta.asetaRuutu(x, y, uusiLuku);
        }
    }
}
