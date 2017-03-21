/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.logiikka;

import com.edii.j211.logiikka.impl.PelikenttaImpl;
import com.edii.j211.logiikka.impl.PeliImpl;
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
                PeliImpl peli = new PeliImpl(i);
                fail("Pelikentän luonti liian pienellä kentän koolla onnistui");
            } catch (IllegalArgumentException e) {
                assertEquals("Kentän koon on oltava suurempi tai yhtä suuri kuin kaksi", e.getMessage());
            }
        }
    }
    
    @Test
    public void testaaKentanKoko() {
        for (int i = 2; i < 10; i++) {
            PeliImpl peli = new PeliImpl(i);
            assertEquals(i, peli.koko());
            assertEquals(i, peli.pelikentta().koko());
        }
    }
    
    @Test
    public void testaaUudenKentanSisalto() {
        int nelostenMaara = 0;
        int kakkostenMaara = 0;
        for (int i = 0; i < 10000; i++) {
            for (int koko = 2; koko <= 10; koko++) {
                PeliImpl peli = new PeliImpl(koko);

                PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
                int tyhjienMaara = 0;

                for (int y = 0; y < koko; y++) {
                    for (int x = 0; x < koko; x++) {
                        assertTrue("Kentästä löytyi luku joka ei ole 0, 2 tai 4", kentta.arvo(x, y) == 0 || kentta.arvo(x, y) == 2 || kentta.arvo(x, y) == 4);
                        if (kentta.arvo(x, y) == 0) {
                            tyhjienMaara++;
                        } else if (kentta.arvo(x, y) == 4) {
                            nelostenMaara++;
                        } else if (kentta.arvo(x, y) == 2) {
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
    
    @Test
    public void testaaLuvunLisaysTayttaaKentan() {
        for (int koko = 2; koko <= 10; koko++) {
            PeliImpl peli = new PeliImpl(koko);
            for (int i = 0; i < koko * koko - 1; i++) {
                assertEquals("Tyhjien määrä ei täsmää, kun " + koko + " kokoiseen kenttään lisättiin " + (i + 1) + ". luku", koko * koko - 1 - i, peli.pelikentta().tyhjat().length);
                peli.lisaaLuku();
            }
        }
    }
    
    @Test
    public void testaaLukujenYhdistysYlos() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(1, 3, 4);
        
        kentta.asetaArvo(2, 0, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(3, 2, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(1, 0));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(2, 0));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(3, 0));
    }
    
    @Test
    public void testaaLukujenYhdistysYlos2() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 4);
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(0, 1));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 2));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaYlos() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 2);
        
        peli.ylos();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 1));
        
        peli.ylos();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 0));
    }
    
    
    @Test
    public void testaaLukujenYhdistysAlas() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(1, 3, 4);
        
        kentta.asetaArvo(2, 0, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(3, 2, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.alas();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(1, 3));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(2, 3));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(3, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysAlas2() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 4);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 2);
        
        peli.alas();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(0, 2));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 1));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaAlas() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(0, 2, 2);
        kentta.asetaArvo(0, 3, 2);
        
        peli.alas();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 3));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 2));
        
        peli.alas();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 3));
    }
    
    
    
    @Test
    public void testaaLukujenYhdistysVasemmalle() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(3, 1, 4);
        
        kentta.asetaArvo(0, 2, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(2, 3, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.vasen();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 1));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(0, 2));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(0, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysVasemmalle2() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 4);
        
        peli.vasen();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(1, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(2, 0));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaVasemmalle() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 2);
        
        peli.vasen();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(0, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(1, 0));
        
        peli.vasen();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(0, 0));
    }
    
    
    @Test
    public void testaaLukujenYhdistysOikealle() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        
        kentta.asetaArvo(1, 1, 4);
        kentta.asetaArvo(3, 1, 4);
        
        kentta.asetaArvo(0, 2, 8);
        kentta.asetaArvo(2, 2, 8);
        
        kentta.asetaArvo(2, 3, 512);
        kentta.asetaArvo(3, 3, 512);
        
        peli.oikea();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(3, 1));
        assertEquals("Ruudun arvo ei täsmää", 16, kentta.arvo(3, 2));
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta.arvo(3, 3));
    }
    
    @Test
    public void testaaLukujenYhdistysOikealle2() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 4);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 2);
        
        peli.oikea();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 2, kentta.arvo(2, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(1, 0));
    }
    
    @Test
    public void eiYhdistyKahtaKertaaOikealle() {
        PeliImpl peli = new PeliImpl(4);
        PelikenttaImpl kentta = (PelikenttaImpl) peli.pelikentta();
        kentta.tyhjenna();
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(2, 0, 2);
        kentta.asetaArvo(3, 0, 2);
        
        peli.oikea();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, kentta.tyhjat().length);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(3, 0));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta.arvo(2, 0));
        
        peli.oikea();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta.arvo(3, 0));
    }
}
