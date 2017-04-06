package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;
import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.Pelikentta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
                Peli peli = new PeliImpl(koko);

                Pelikentta kentta = peli.pelikentta();
                int tyhjienMaara = 0;

                for (int y = 0; y < koko; y++) {
                    for (int x = 0; x < koko; x++) {
                        assertTrue("Kentästä löytyi luku joka ei ole 0, 2 tai 4", kentta.arvo(x, y) == 0 || kentta.arvo(x, y) == 2 || kentta.arvo(x, y) == 4);
                        switch (kentta.arvo(x, y)) {
                            case 0:
                                tyhjienMaara++;
                                break;
                            case 4:
                                nelostenMaara++;
                                break;
                            case 2:
                                kakkostenMaara++;
                                break;
                            default:
                                break;
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
                assertEquals("Tyhjien määrä ei täsmää, kun " + koko + " kokoiseen kenttään lisättiin " + (i + 1) + ". luku", koko * koko - 1 - i, Util.tyhjat(peli.pelikentta()).length);
                peli.lisaaLuku();
            }
        }
    }
    
    @Test
    public void luvunLisaysTayteenKenttaanEiTeeMitaan() {
        PeliImpl peli = new PeliImpl(2);
        peli.lisaaLuku();
        peli.lisaaLuku();
        peli.lisaaLuku();
        
        Pelikentta kentta = peli.pelikentta();
        
        int x0y0 = kentta.arvo(0, 0);
        int x1y0 = kentta.arvo(1, 0);
        int x0y1 = kentta.arvo(0, 1);
        int x1y1 = kentta.arvo(1, 1);
        
        peli.lisaaLuku();
        
        assertEquals(x0y0, kentta.arvo(0, 0));
        assertEquals(x1y0, kentta.arvo(1, 0));
        assertEquals(x0y1, kentta.arvo(0, 1));
        assertEquals(x1y1, kentta.arvo(1, 1));
    }
    
    @Test
    public void peliOhi() {
        PeliImpl peli = new PeliImpl(2);
        MuokattavaPelikentta kentta = (MuokattavaPelikentta) peli.pelikentta();
        
        assertTrue("peliOhi() palautti alkutilassa true", peli.peliOhi() == false);
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 4);
        kentta.asetaArvo(1, 0, 4);
        kentta.asetaArvo(1, 1, 2);
        
        assertTrue("peliOhi() palautti false, vaikka siirtoja ei voi tehdä", peli.peliOhi() == true);
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 2);
        kentta.asetaArvo(1, 0, 4);
        kentta.asetaArvo(1, 1, 8);
        
        assertTrue("peliOhi() palautti true täydellä kentällä, vaikka siirron alas tai ylös voi tehdä", peli.peliOhi() == false);
        
        kentta.asetaArvo(0, 0, 2);
        kentta.asetaArvo(0, 1, 4);
        kentta.asetaArvo(1, 0, 2);
        kentta.asetaArvo(1, 1, 8);
        
        assertTrue("peliOhi() palautti true täydellä kentällä, vaikka siirron vasemmalle tai oikealle voi tehdä", peli.peliOhi() == false);
    }
}
