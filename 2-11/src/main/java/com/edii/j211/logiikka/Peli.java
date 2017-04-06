/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.logiikka;

/**
 *
 * @author edii
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
