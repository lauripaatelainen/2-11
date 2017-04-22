/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.pisterekisteri;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
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
public final class PisteRekisteriIOTest {
    private String tempFilename;
    
    public PisteRekisteriIOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            File tempfile = File.createTempFile("2-11-temp", ".tmp");
            tempFilename = tempfile.getAbsolutePath();
            tempfile.delete();
        } catch (IOException e) {
            throw new AssertionError("Väliaikaisen tiedoston luonti epäonnistui");
        }
    }
    
    @After
    public void tearDown() {
        new File(tempFilename).delete();
    }
    
    @Test
    public void dummyTest() {
        new PisteRekisteriIO(); /* ilman tätä testiä Util-luokan ensimmäistä riviä ei lasketa pit:n line coverageen */
    }

    @Test
    public void tallennusJaLukuOnnistuu() {
        PisteRekisteri rekisteri = new PisteRekisteri();
        rekisteri.lisaaTulos(new Tulos("Pekka", 10, 4));
        rekisteri.lisaaTulos(new Tulos("Kalle", 12, 4));
        rekisteri.lisaaTulos(new Tulos("Seppo", 8, 4));
        rekisteri.lisaaTulos(new Tulos("Pekka", 100, 5));
        rekisteri.lisaaTulos(new Tulos("Kalle", 102, 5));
        rekisteri.lisaaTulos(new Tulos("Seppo", 108, 5));
        
        try {
            PisteRekisteriIO.tallenna(rekisteri, tempFilename);
        } catch (IOException e) {
            throw new AssertionError("Tallennus epäonnistui", e);
        }
        
        PisteRekisteri luettu;
        try {
            luettu = PisteRekisteriIO.avaa(tempFilename);
        } catch (IOException e) {
            throw new AssertionError("Luku epäonnistui", e);
        }
        
        assertNotNull(luettu);
        
        assertEquals("Luetun pisterekisterin koko ei täsmää tallennettuun", rekisteri.pistetaulut().size(), luettu.pistetaulut().size());
        assertEquals("Luettu tulostaulu ei täsmää tallennettuun", rekisteri.pistetaulu(4), luettu.pistetaulu(4));
        assertEquals("Luettu tulostaulu ei täsmää tallennettuun", rekisteri.pistetaulu(5), luettu.pistetaulu(5));
    }
    
    @Test
    public void tyhjanAvaus() {
        PisteRekisteri rekisteri;
        try {
            rekisteri = PisteRekisteriIO.avaa(tempFilename);
        } catch (IOException e) {
            throw new AssertionError("Avaus epäonnistuI");
        }
        
        assertNotNull(rekisteri);
        assertEquals(0, rekisteri.pistetaulut().size());
        assertTrue(new File(tempFilename).exists());
    }
    
    @Test
    public void viallisenTiedostonLuku() {
        try {
            PrintStream ps = new PrintStream(tempFilename);
            ps.println("1#5");
            ps.println("matti\t5");
            ps.println("seppo\t6");
            ps.close();
        
        } catch (IOException e) {
            throw new AssertionError("Viallisen testitiedoston luonti epäonnistui");
        }
        
        try {
            PisteRekisteri rekisteri = PisteRekisteriIO.avaa(tempFilename);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void viallisenTiedostonLuku2() {
        try {
            PrintStream ps = new PrintStream(tempFilename);
            ps.println("#5");
            ps.println("matti5");
            ps.println("seppo\t6");
            ps.close();
        
        } catch (IOException e) {
            throw new AssertionError("Viallisen testitiedoston luonti epäonnistui");
        }
        
        try {
            PisteRekisteri rekisteri = PisteRekisteriIO.avaa(tempFilename);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void viallisenTiedostonLuku3() {
        try {
            PrintStream ps = new PrintStream(tempFilename);
            ps.println("#5");
            ps.println("matti\t5a");
            ps.println("seppo\t6");
        
        } catch (IOException e) {
            throw new AssertionError("Viallisen testitiedoston luonti epäonnistui");
        }
        
        try {
            PisteRekisteri rekisteri = PisteRekisteriIO.avaa(tempFilename);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void tyhjanTiedostonLuku() {
        try {
            new File(tempFilename).delete();
            PisteRekisteriIO.avaa(tempFilename);
            assertEquals(0, PisteRekisteriIO.avaa(tempFilename).pistetaulut().size());
        } catch (IOException e) {
            throw new AssertionError("Tyhjän tiedoston luku ei onnistunut");
        }
    }
}
