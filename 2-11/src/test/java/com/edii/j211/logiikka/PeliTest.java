/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.logiikka;

import com.edii.j211.logiikka.Peli;
import java.util.Random;
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
public class PeliTest {
    
    public PeliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaLiianPieniKentanKoko() {
        for (int i = -1; i <= 1; i++) {
            try {
                Peli peli = new Peli(i);
                fail("Pelikentän luonti liian pienellä kentän koolla onnistui");
            } catch (IllegalArgumentException e) {
                assertEquals("Kentän koon on oltava suurempi tai yhtä suuri kuin kaksi", e.getMessage());
            }
        }
    }
    
    @Test
    public void testaaKentanKoko() {
        for (int i = 2; i < 10; i++) {
            Peli peli = new Peli(i);
            assertEquals(i, peli.getKentanKoko());
            assertEquals(i, peli.getKentta().getKentanKoko());
        }
    }
    
    @Test
    public void testaaUudenKentanSisalto() {
        int nelostenMaara = 0;
        int kakkostenMaara = 0;
        for (int i = 0; i < 10000; i++) {
            for (int koko = 2; koko <= 10; koko++) {
                Peli peli = new Peli(koko);

                Pelikentta kentta = peli.getKentta();
                int tyhjienMaara = 0;

                for (int y = 0; y < koko; y++) {
                    for (int x = 0; x < koko; x++) {
                        assertTrue("Kentästä löytyi luku joka ei ole 0, 2 tai 4", kentta.ruudunArvo(x, y) == 0 || kentta.ruudunArvo(x, y) == 2 || kentta.ruudunArvo(x, y) == 4);
                        if (kentta.ruudunArvo(x, y) == 0) {
                            tyhjienMaara++;
                        } else if (kentta.ruudunArvo(x, y) == 4) {
                            nelostenMaara++;
                        } else if (kentta.ruudunArvo(x, y) == 2) {
                            kakkostenMaara++;
                        }
                    }
                }

                assertEquals("Tyhjien ruutujen määrä ei täsmää", koko * koko - 1, tyhjienMaara);
            }
        }
        float nelostenOsuus = ((float) nelostenMaara) / ((float) nelostenMaara + kakkostenMaara);
        assertTrue("Uusi luku on 4 liian usein: " + nelostenOsuus, nelostenOsuus <= 0.15f);
        assertTrue("Uusi luku on 4 liian harvoin: " + nelostenOsuus, nelostenOsuus >= 0.05f);
    }
    
    private int laskeTyhjat(Pelikentta kentta) {
        int tyhjienMaara = 0;

        for (int y = 0; y < kentta.getKentanKoko(); y++) {
            for (int x = 0; x < kentta.getKentanKoko(); x++) {
                if (kentta.ruudunArvo(x, y) == 0) {
                    tyhjienMaara++;
                }
            }
        }
        
        return tyhjienMaara;
    }
    
    private void nollaaKentta(Pelikentta kentta) {
        for (int y = 0; y < kentta.getKentanKoko(); y++) {
            for (int x = 0; x < kentta.getKentanKoko(); x++) {
                kentta.asetaRuutu(x, y, 0);
            }
        }
    }
    
    @Test
    public void testaaLuvunLisaysTayttaaKentan() {
        for (int koko = 2; koko <= 10; koko++) {
            Peli peli = new Peli(koko);
            for (int i = 0; i < koko * koko - 1; i++) {
                assertEquals("Tyhjien määrä ei täsmää, kun " + koko + " kokoiseen kenttään lisättiin " + (i + 1) + ". luku", koko * koko - 1 - i, laskeTyhjat(peli.getKentta()));
                peli.lisaaLuku();
            }
        }
    }
    
    @Test
    public void testaaLukujenYhdistysYlos() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(0, 1, 2);
        
        kentta.asetaRuutu(1, 1, 4);
        kentta.asetaRuutu(1, 3, 4);
        
        kentta.asetaRuutu(2, 0, 8);
        kentta.asetaRuutu(2, 2, 8);
        
        kentta.asetaRuutu(3, 2, 512);
        kentta.asetaRuutu(3, 3, 512);
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(1, 0));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.ruudunArvo(2, 0));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.ruudunArvo(3, 0));
    }
    
    @Test
    public void testaaLukujenYhdistysYlos2() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(0, 1, 2);
        kentta.asetaRuutu(0, 2, 2);
        kentta.asetaRuutu(0, 3, 4);
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.ruudunArvo(0, 1));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 2));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaYlos() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(0, 1, 2);
        kentta.asetaRuutu(0, 2, 2);
        kentta.asetaRuutu(0, 3, 2);
        
        peli.ylos();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 1));
        
        peli.ylos();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(0, 0));
    }
    
    
    @Test
    public void testaaLukujenYhdistysAlas() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(0, 1, 2);
        
        kentta.asetaRuutu(1, 1, 4);
        kentta.asetaRuutu(1, 3, 4);
        
        kentta.asetaRuutu(2, 0, 8);
        kentta.asetaRuutu(2, 2, 8);
        
        kentta.asetaRuutu(3, 2, 512);
        kentta.asetaRuutu(3, 3, 512);
        
        peli.alas();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(1, 3));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.ruudunArvo(2, 3));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.ruudunArvo(3, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysAlas2() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 4);
        kentta.asetaRuutu(0, 1, 2);
        kentta.asetaRuutu(0, 2, 2);
        kentta.asetaRuutu(0, 3, 2);
        
        peli.alas();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.ruudunArvo(0, 2));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 1));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaAlas() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(0, 1, 2);
        kentta.asetaRuutu(0, 2, 2);
        kentta.asetaRuutu(0, 3, 2);
        
        peli.alas();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 2));
        
        peli.alas();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(0, 3));
    }
    
    
    
    @Test
    public void testaaLukujenYhdistysVasemmalle() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(1, 0, 2);
        
        kentta.asetaRuutu(1, 1, 4);
        kentta.asetaRuutu(3, 1, 4);
        
        kentta.asetaRuutu(0, 2, 8);
        kentta.asetaRuutu(2, 2, 8);
        
        kentta.asetaRuutu(2, 3, 512);
        kentta.asetaRuutu(3, 3, 512);
        
        peli.vasen();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(0, 1));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.ruudunArvo(0, 2));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.ruudunArvo(0, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysVasemmalle2() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(1, 0, 2);
        kentta.asetaRuutu(2, 0, 2);
        kentta.asetaRuutu(3, 0, 4);
        
        peli.vasen();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.ruudunArvo(1, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(2, 0));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaVasemmalle() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(1, 0, 2);
        kentta.asetaRuutu(2, 0, 2);
        kentta.asetaRuutu(3, 0, 2);
        
        peli.vasen();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(1, 0));
        
        peli.vasen();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(0, 0));
    }
    
    
    @Test
    public void testaaLukujenYhdistysOikealle() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(1, 0, 2);
        
        kentta.asetaRuutu(1, 1, 4);
        kentta.asetaRuutu(3, 1, 4);
        
        kentta.asetaRuutu(0, 2, 8);
        kentta.asetaRuutu(2, 2, 8);
        
        kentta.asetaRuutu(2, 3, 512);
        kentta.asetaRuutu(3, 3, 512);
        
        peli.oikea();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(3, 1));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.ruudunArvo(3, 2));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.ruudunArvo(3, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysOikealle2() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 4);
        kentta.asetaRuutu(1, 0, 2);
        kentta.asetaRuutu(2, 0, 2);
        kentta.asetaRuutu(3, 0, 2);
        
        peli.oikea();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.ruudunArvo(2, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(1, 0));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaOikealle() {
        Peli peli = new Peli(4);
        Pelikentta kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta.asetaRuutu(0, 0, 2);
        kentta.asetaRuutu(1, 0, 2);
        kentta.asetaRuutu(2, 0, 2);
        kentta.asetaRuutu(3, 0, 2);
        
        peli.oikea();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.ruudunArvo(2, 0));
        
        peli.oikea();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.ruudunArvo(3, 0));
    }
}
