package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.Pelikentta;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ylläpitää pelin tilannetta. 
 * 
 * @author edii
 */
public class PeliImpl implements Peli {
    private PelikenttaImpl kentta;
    private Random random;
    
    /**
     * Luo annetun kokoisen pelikentän. Luodussa pelikentässä on ensimmäinen
     * luku täytettynä.
     * 
     * @param kentanKoko Luotavan kentän koko (leveys ja korkeus)
     */
    public PeliImpl(int kentanKoko) {
        if (kentanKoko <= 1) {
            throw new IllegalArgumentException("Kentän koon on oltava suurempi tai yhtä suuri kuin kaksi");
        }
        
        this.kentta = new PelikenttaImpl(kentanKoko);
        this.random = new Random();
        
        lisaaLuku();
    }
    
    /**
     * Palauttaa kentän koon.
     * 
     * @return Kentän koko
     */
    @Override
    public int koko() {
        return this.kentta.koko();
    }
    
    /**
     * Palauttaa pelikentän.
     * 
     * @return Pelikenttä
     */
    @Override
    public Pelikentta pelikentta() {
        return this.kentta;
    }
    
    /**
     * Siirto ylöspäin.
     * 
     * Luvut siirretään niin ylös kuin ne mahtuu ja törmäävät
     * samat luvut yhdistetään. 
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
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
        for (int y = 0; y < koko(); y++) {
            for (int x = 0; x < koko(); x++) {
                if (kentta.arvo(x, y) == 0) {
                    tyhjat.add(y * koko() + x);
                }
            }
        }
        
        if (!tyhjat.isEmpty()) {
            int valinta = tyhjat.get(random.nextInt(tyhjat.size()));
            int x = valinta % koko();
            int y = (valinta - x) / koko();

            kentta.asetaArvo(x, y, uusiLuku);
        }
    }
}
