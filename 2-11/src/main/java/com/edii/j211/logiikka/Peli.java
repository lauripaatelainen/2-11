package com.edii.j211.logiikka;

/**
 * Rajapinta pelin kokonaistilanteelle.
 * 
 * Peli-rajapinnan toteuttavan luokan olio merkitsee yhtä peliä. 
 */
public interface Peli {
    /**
     * Pelikentän koko
     * 
     * @return Palauttaa pelin leveyden (ja korkeuden) ruutuina.
     */
    public int koko();
    
    /**
     * Pelin pelikenttä
     * 
     * @return Peliin liittyvän pelikentän
     */
    public Pelikentta pelikentta();
    
    /**
     * Siirto ylös
     */
    public void ylos();
    
    /**
     * Siirto alas
     */
    public void alas();
    
    /**
     * Siirto oikealle
     */
    public void oikea();
    
    /**
     * Siirto vasemmalle
     */
    public void vasen();
    
    /**
     * Onko peli ohi?
     * 
     * @return true, jos peli on ohi eikä siirtoja voi enää tehdä
     */
    public boolean peliOhi();
    
    /**
     * Tämän hetkinen pistemäärä
     * 
     * @return pisteet
     */
    public int pisteet();
}
