package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;

/**
 * Pelikentta-rajapinnan toteutus.
 *
 * Toteuttaa laajemman MuokattavaPelikenttä rajapinnan, jota {@link PeliImpl}
 * käyttää sisäisesti, mutta palauttaa muille näkyvänä Pelikentta-olion, jota ei
 * voi muokata.
 *
 * @see com.edii.j211.logiikka.MuokattavaPelikentta
 * @see com.edii.j211.logiikka.Pelikentta
 * @see com.edii.j211.logiikka.impl.PeliImpl
 */
public class PelikenttaImpl implements MuokattavaPelikentta {

    private int koko;
    private int[][] kentta;

    /**
     * Konstruktori uuden pelikentän toteutuksen luontiin. Vaatii parametrina
     * vain luotavan pelikentän koon.
     * @param koko Pelikentän koko
     */
    public PelikenttaImpl(int koko) {
        this.koko = koko;
        this.kentta = new int[koko][koko];
    }

    @Override
    public int koko() {
        return koko;
    }

    @Override
    public void asetaArvo(int x, int y, int arvo) {
        this.kentta[y][x] = arvo;
    }

    @Override
    public int arvo(int x, int y) {
        return this.kentta[y][x];
    }
}
