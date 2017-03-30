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
    public int koko();
    public Pelikentta pelikentta();
    
    public void ylos();
    public void alas();
    public void oikea();
    public void vasen();
    
    public int pisteet();
}
