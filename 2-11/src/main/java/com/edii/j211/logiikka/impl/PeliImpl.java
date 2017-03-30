package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.Pelikentta;
import java.util.Random;

/**
 * Ylläpitää pelin tilannetta.
 *
 * @author edii
 */
public class PeliImpl implements Peli {

    private PelikenttaImpl kentta;
    private Random random;
    private Siirtaja siirtaja;
    private int pisteet = 0;

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
        this.siirtaja = new Siirtaja(this.kentta);
        this.random = new Random();

        lisaaLuku();
    }
    
    public int getPisteet() {
        return pisteet;
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
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
     * Luvut siirretään niin ylös kuin ne mahtuu ja törmäävät samat luvut
     * yhdistetään.
     */
    @Override
    public void ylos() {
        boolean siirretty = this.siirtaja.siirraYlospain();
        int pisteet = this.siirtaja.yhdistaYlos();
        siirretty |= this.siirtaja.siirraYlospain();

        if (siirretty || pisteet > 0) {
            lisaaLuku();
        }
        
        setPisteet(getPisteet() + pisteet);
    }

    /**
     * Siirto alaspäin.
     *
     * Luvut siirretään niin alas kuin ne mahtuu ja törmäävät samat luvut
     * yhdistetään.
     */
    @Override
    public void alas() {
        this.siirtaja.siirraAlaspain();
        this.siirtaja.yhdistaAlas();
        this.siirtaja.siirraAlaspain();

        lisaaLuku();
    }

    /**
     * Siirto oikealle.
     *
     * Luvut siirretään niin paljon oikealle kuin ne mahtuu ja törmäävät samat
     * luvut yhdistetään.
     */
    @Override
    public void oikea() {
        this.siirtaja.siirraOikealle();
        this.siirtaja.yhdistaOikealle();
        this.siirtaja.siirraOikealle();

        lisaaLuku();
    }

    /**
     * Siirto vasemmalle.
     *
     * Luvut siirretään niin paljon vasemmalle kuin ne mahtuu ja törmäävät samat
     * luvut yhdistetään.
     */
    @Override
    public void vasen() {
        this.siirtaja.siirraVasemmalle();
        this.siirtaja.yhdistaVasemmalle();
        this.siirtaja.siirraVasemmalle();

        lisaaLuku();
    }

    /**
     * Lisää luvun satunnaiseen tyhjään ruutuun.
     *
     * Uusi luku on 10% todennäköisyydellä 4 ja 90% todennäköisyydellä 2.
     */
    public final void lisaaLuku() {
        int[][] tyhjat = kentta.tyhjat();
        if (tyhjat.length > 0) {
            int[] valinta = tyhjat[random.nextInt(tyhjat.length)];
            
            int uusiLuku = 2;

            /* uusi luku on 10% todennäköisyydellä 4, muuten 2 */
            if (random.nextInt(10) == 0) {
                uusiLuku = 4;
            }

            kentta.asetaArvo(valinta[0], valinta[1], uusiLuku);
        }
    }
}
