package com.edii.j211;

import com.edii.j211.textui.Tekstikayttoliittyma;
import java.util.Scanner;

/**
 * Pääluokka pelin käynnistykseen. Käynnistää tekstikäyttöliittymän. 
 * 
 * @see com.edii.j211.gui.GraafinenKayttoliittyma
 */
public class Main {
    /**
     * main-methodi, joka luo uuden Tekstikäyttöliittymä-komennon ja käynnistää sen standardisyötteellä ja -tulosteella.
     * 
     * @param args Komentoriviltä annetut parametrit
     */
    
    public static void main(String[] args) {
        new Tekstikayttoliittyma(new Scanner(System.in), System.out).kaynnista();
    }
}
