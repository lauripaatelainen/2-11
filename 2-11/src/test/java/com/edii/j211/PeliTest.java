/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211;

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
            assertEquals(i, peli.getKentta().length);
            for (int j = 0; j < i; j++) {
                assertEquals(i, peli.getKentta()[j].length);
            }
        }
    }
    
    @Test
    public void testaaUudenKentanSisalto() {
        int nelostenMaara = 0;
        int kakkostenMaara = 0;
        for (int i = 0; i < 10000; i++) {
            for (int koko = 2; koko <= 10; koko++) {
                Peli peli = new Peli(koko);

                int[][] kentta = peli.getKentta();
                int tyhjienMaara = 0;

                for (int y = 0; y < koko; y++) {
                    for (int x = 0; x < koko; x++) {
                        assertTrue("Kentästä löytyi luku joka ei ole 0, 2 tai 4", kentta[y][x] == 0 || kentta[y][x] == 2 || kentta[y][x] == 4);
                        if (kentta[y][x] == 0) {
                            tyhjienMaara++;
                        } else if (kentta[y][x] == 4) {
                            nelostenMaara++;
                        } else if (kentta[y][x] == 2) {
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
    
    private int laskeTyhjat(int[][] kentta) {
        int tyhjienMaara = 0;

        for (int y = 0; y < kentta.length; y++) {
            for (int x = 0; x < kentta.length; x++) {
                if (kentta[y][x] == 0) {
                    tyhjienMaara++;
                }
            }
        }
        
        return tyhjienMaara;
    }
    
    private void nollaaKentta(int[][] kentta) {
        for (int y = 0; y < kentta.length; y++) {
            for (int x = 0; x < kentta.length; x++) {
                kentta[y][x] = 0;
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
        int[][] kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta[0][0] = 2;
        kentta[1][0] = 2;
        
        kentta[1][1] = 4;
        kentta[3][1] = 4;
        
        kentta[0][2] = 8;
        kentta[2][2] = 8;
        
        kentta[2][3] = 512;
        kentta[3][3] = 512;
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 11, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta[0][0]);
        assertEquals("Ruudun arvo ei täsmää", 8, kentta[0][1]);
        assertEquals("Ruudun arvo ei täsmää", 16, kentta[0][2]);
        assertEquals("Ruudun arvo ei täsmää", 1024, kentta[0][3]);
    }
    
    @Test
    public void testaaLukujenYhdistysYlos2() {
        Peli peli = new Peli(4);
        int[][] kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta[0][0] = 2;
        kentta[1][0] = 2;
        kentta[2][0] = 2;
        kentta[3][0] = 4;
        
        peli.ylos();
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 12, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta[0][0]);
        assertEquals("Ruudun arvo ei täsmää", 2, kentta[1][0]);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta[2][0]);
    }
    
    @Test
    public void eiYhdistyKahtaKertaa() {
        Peli peli = new Peli(4);
        int[][] kentta = peli.getKentta();
        nollaaKentta(kentta);
        
        kentta[0][0] = 2;
        kentta[1][0] = 2;
        kentta[2][0] = 2;
        kentta[3][0] = 2;
        
        peli.ylos();
        
        assertEquals("Tyhjien ruutujen määrä ei täsmää", 13, laskeTyhjat(kentta));
        assertEquals("Ruudun arvo ei täsmää", 4, kentta[0][0]);
        assertEquals("Ruudun arvo ei täsmää", 4, kentta[1][0]);
        
        peli.ylos();
        
        assertEquals("Ruudun arvo ei täsmää", 8, kentta[0][0]);
    }
}
