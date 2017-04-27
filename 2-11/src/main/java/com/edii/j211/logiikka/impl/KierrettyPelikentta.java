package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;

/**
 * Käytettään Siirtäjän sisällä yksinkertaistamaan eri siirto-operaatioita.
 * 
 * KierrettyPelikentta on 90 astetta myötäpäivään kierretty versio annetusta
 * pelikentästä. Muutokset kierrettyyn pelikenttään vaikuttavat
 * myös alkuperäiseen pelikenttään. Esim. jos 4 kokoisen kierretyn pelikentän
 * vasemman ylänurkan arvoa muutetaan, muuttaa se alkuperäisessä pelikentässä 
 * oikean ylänurkan arvoa. 
 */
public class KierrettyPelikentta implements MuokattavaPelikentta {
    private MuokattavaPelikentta kentta;
    
    /**
     * Luo uuden kierretyn pelikentän.
     * @param pelikentta Pelikenttä, joka kierretään 90 astetta myötäpäivään.
     */
    public KierrettyPelikentta(MuokattavaPelikentta pelikentta) {
        this.kentta = pelikentta;
    }
    
    @Override
    public void asetaArvo(int x, int y, int arvo) {
        kentta.asetaArvo(y, koko() - 1 - x, arvo);
    }

    @Override
    public int koko() {
        return kentta.koko();
    }

    @Override
    public int arvo(int x, int y) {
        return kentta.arvo(y, koko() - 1 - x);
    } 
}
