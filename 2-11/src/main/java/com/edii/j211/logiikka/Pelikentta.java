package com.edii.j211.logiikka;

/**
 * Rajapinta pelikentän tilanteelle.
 *
 * @see com.edii.j211.logiikka.MuokattavaPelikentta
 */
public interface Pelikentta {

    /**
     * Kertoo pelikentän koon.
     *
     * @return Pelikentän koko
     */
    public int koko();

    /**
     * Palauttaa pelikentän yhden ruudun arvon.
     *
     * @param x X-koordinaatti (sarake)
     * @param y Y-koordinaatti (rivi)
     * @return Ruudun arvo
     */
    public int arvo(int x, int y);
}
