/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.logiikka.impl;

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
public class PeliSiirrotTest {
    private static PeliImpl peli;
    private static PelikenttaImpl kentta;
    
    public PeliSiirrotTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        peli = new PeliImpl(4);
        kentta = (PelikenttaImpl) peli.pelikentta();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Util.tyhjenna(kentta);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaLukujenYhdistysYlos() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(1, 3, 4);
        
        kentta.asetaArvo(2, 0, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(3, 2, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(1, 0));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(2, 0));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(3, 0));
    }
    
    @Test
    public void testaaLukujenYhdistysYlos2() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 4);
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(0, 1));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 2));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaYlos() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 2);
        
        peli.ylos();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 1));
        
        peli.ylos();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 0));
    }
    
    
    @Test
    public void testaaLukujenYhdistysAlas() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(1, 3, 4);
        
        kentta.asetaArvo(2, 0, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(3, 2, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.alas();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(1, 3));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(2, 3));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(3, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysAlas2() {
        kentta.asetaArvo(0, 0, 4);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 2);
        
        peli.alas();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(0, 2));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 1));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaAlas() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 2);
        
        peli.alas();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 2));
        
        peli.alas();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 3));
    }
    
    
    
    @Test
    public void testaaLukujenYhdistysVasemmalle() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(3, 1, 4);
        
        kentta.asetaArvo(0, 2, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(2, 3, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.vasen();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 1));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(0, 2));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(0, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysVasemmalle2() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 4);
        
        peli.vasen();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(1, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(2, 0));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaVasemmalle() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 2);
        
        peli.vasen();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(1, 0));
        
        peli.vasen();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 0));
    }
    
    
    @Test
    public void testaaLukujenYhdistysOikealle() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(3, 1, 4);
        
        kentta.asetaArvo(0, 2, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(2, 3, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.oikea();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(3, 1));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(3, 2));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(3, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysOikealle2() {
        kentta.asetaArvo(0, 0, 4);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 2);
        
        peli.oikea();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(2, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(1, 0));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaOikealle() {
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 2);
        
        peli.oikea();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, Util.tyhjat(kentta).length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(2, 0));
        
        peli.oikea();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(3, 0));
    }
    
    @Test
    public void pelikenttaPysyySamanaYlos() {
        int pisteet = peli.pisteet();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 2);
        
        peli.ylos();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Pelin pistemäärä ei täsmää", pisteet, peli.pisteet());
    }
    
    @Test
    public void pelikenttaPysyySamanaAlas() {
        int pisteet = peli.pisteet();
        
        kentta.asetaArvo(0, 3, 2);
        kentta.asetaArvo(1, 3, 2);
        kentta.asetaArvo(2, 3, 2);
        kentta.asetaArvo(3, 3, 2);
        
        peli.alas();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Pelin pistemäärä ei täsmää", pisteet, peli.pisteet());
    }
    
    
    
    @Test
    public void pelikenttaPysyySamanaVasemmalle() {
        int pisteet = peli.pisteet();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 2);
        
        peli.vasen();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Pelin pistemäärä ei täsmää", pisteet, peli.pisteet());
    }
    
    
    @Test
    public void pelikenttaPysyySamanaOikealle() {
        int pisteet = peli.pisteet();
        
        kentta.asetaArvo(3, 0, 2);
        kentta.asetaArvo(3, 1, 2);
        kentta.asetaArvo(3, 2, 2);
        kentta.asetaArvo(3, 3, 2);
        
        peli.oikea();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, Util.tyhjat(kentta).length);
        assertEquals("Pelin pistemäärä ei täsmää", pisteet, peli.pisteet());
    }
    
    @Test
    public void pisteetKasvaaYlos() {
        int pisteet = peli.pisteet();
        
        Util.tyhjenna(kentta);
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 4);
        kentta.asetaArvo(2, 0, 8);
        kentta.asetaArvo(3, 0, 16);
        
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(2, 1, 8);
        kentta.asetaArvo(3, 1, 16);
        
        kentta.asetaArvo(0, 2, 4);
        kentta.asetaArvo(1, 2, 4);
        kentta.asetaArvo(2, 2, 4);
        kentta.asetaArvo(3, 2, 4);
        
        peli.ylos();
        peli.ylos();
        
        assertTrue("Pistemäärä ei täsmää", peli.pisteet() >= pisteet + 68 && peli.pisteet() <= pisteet + 76);
    }
    
    @Test
    public void pisteetKasvaaAlas() {
        int pisteet = peli.pisteet();
        
        Util.tyhjenna(kentta);
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 4);
        kentta.asetaArvo(2, 0, 8);
        kentta.asetaArvo(3, 0, 16);
        
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(2, 1, 8);
        kentta.asetaArvo(3, 1, 16);
        
        kentta.asetaArvo(0, 2, 4);
        kentta.asetaArvo(1, 2, 4);
        kentta.asetaArvo(2, 2, 4);
        kentta.asetaArvo(3, 2, 4);
        
        peli.alas();
        peli.alas();
        
        assertTrue("Pistemäärä ei täsmää", peli.pisteet() >= pisteet + 68 && peli.pisteet() <= pisteet + 76);
    }
    
    @Test
    public void pisteetKasvaaVasemmalle() {
        int pisteet = peli.pisteet();
        
        Util.tyhjenna(kentta);
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 4);
        kentta.asetaArvo(0, 2, 8);
        kentta.asetaArvo(0, 3, 16);
        
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(1, 2, 8);
        kentta.asetaArvo(1, 3, 16);
        
        kentta.asetaArvo(2, 0, 4);
        kentta.asetaArvo(2, 1, 4);
        kentta.asetaArvo(2, 2, 4);
        kentta.asetaArvo(2, 3, 4);
        
        peli.vasen();
        peli.vasen();
        
        assertTrue("Pistemäärä ei täsmää", peli.pisteet() >= pisteet + 68 && peli.pisteet() <= pisteet + 76);
    }
    
    @Test
    public void pisteetKasvaaOikealle() {
        int pisteet = peli.pisteet();
        
        Util.tyhjenna(kentta);
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 4);
        kentta.asetaArvo(0, 2, 8);
        kentta.asetaArvo(0, 3, 16);
        
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(1, 2, 8);
        kentta.asetaArvo(1, 3, 16);
        
        kentta.asetaArvo(2, 0, 4);
        kentta.asetaArvo(2, 1, 4);
        kentta.asetaArvo(2, 2, 4);
        kentta.asetaArvo(2, 3, 4);
        
        peli.oikea();
        peli.oikea();
        
        assertTrue("Pistemäärä ei täsmää", peli.pisteet() >= pisteet + 68 && peli.pisteet() <= pisteet + 76);
    }
}
