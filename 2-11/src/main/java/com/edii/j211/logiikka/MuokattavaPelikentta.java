package com.edii.j211.logiikka;

/**
 * Pelikentta-rajapinnan laajennos, jossa arvoja voi muokata
 * 
 * @see com.edii.j211.logiikka.Pelikentta
 */
public interface MuokattavaPelikentta extends Pelikentta {
    /**
     * Asettaa arvon yhdelle pelikentän ruudulle.
     * 
     * @param x X-koordinaatti (sarake)
     * @param y Y-koordinaatti (rivi)
     * @param arvo Ruudun uusi arvo
     */
    public void asetaArvo(int x, int y, int arvo);
}
