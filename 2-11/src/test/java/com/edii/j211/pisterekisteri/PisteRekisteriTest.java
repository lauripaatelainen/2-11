/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.pisterekisteri;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edii
 */
public class PisteRekisteriTest {
    private PisteRekisteri rekisteri;
    
    public PisteRekisteriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        rekisteri = new PisteRekisteri();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tuloksenLisays() {
        rekisteri.lisaaTulos(new Tulos("Pekka", 10, 4));
        assertEquals("Pisterekisterin koko ei täsmää", 1, rekisteri.pistetaulut().size());
        assertEquals("Tulostaulun koko ei täsmää", 1, rekisteri.pistetaulu(4).size());
    }
    
    @Test
    public void tyhjaTulostauluOnNull() {
        assertEquals("Pisterekisteri ei palauttanut null tyhjälle tulostaululle", null, rekisteri.pistetaulu(4));
    }
    
    @Test
    public void tulostenJarjestysOnOikea() {
        rekisteri.lisaaTulos(new Tulos("Pekka", 10, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 12, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 8, 4));
        
        assertEquals("Ensimmäinen pistemäärä ei täsmää", 12, rekisteri.pistetaulu(4).get(0).getPisteet());
        assertEquals("Toinen pistemäärä ei täsmää", 10, rekisteri.pistetaulu(4).get(1).getPisteet());
        assertEquals("Kolmas pistemäärä ei täsmää", 8, rekisteri.pistetaulu(4).get(2).getPisteet());
    }
    
    @Test
    public void vainKymmenenParastaSailyy() {
        rekisteri.lisaaTulos(new Tulos("Pekka", 10, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 12, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 8, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 100, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 90, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 6, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 14, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 18, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 20, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 22, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 24, 4));
        
        assertEquals("Tulostaulun koko ei täsmää", 10, rekisteri.pistetaulu(4).size());
        assertEquals("Tulostaulun huonoin tulos ei täsmää", 8, rekisteri.pistetaulu(4).get(9).getPisteet());
    }
    
    @Test
    public void tuloksenGetterit() {
        Tulos tulos = new Tulos("Testinimi", 999, 50);
        assertEquals("Tuloksen nimi ei täsmää", "Testinimi", tulos.getNimi());
        assertEquals("Tuloksen pistemäärä ei täsmää", 999, tulos.getPisteet());
        assertEquals("Tuloksen koko ei täsmää", 50, tulos.getKoko());
    }
    
    @Test
    public void tuloksenEquals() {
        Tulos tulos = new Tulos("Testinimi", 999, 50);
        Tulos tulos1 = new Tulos("Testinimi", 999, 50);
        Tulos tulos2 = new Tulos("Testinimi1", 999, 50);
        Tulos tulos3 = new Tulos("Testinimi", 998, 50);
        Tulos tulos4 = new Tulos("Testinimi", 999, 51);
        
        assertNotEquals(tulos, null);
        assertNotEquals(tulos, rekisteri);
        assertEquals(tulos, tulos1);
        assertNotEquals(tulos, tulos2);
        assertNotEquals(tulos, tulos3);
        assertNotEquals(tulos, tulos4);
    }
}
