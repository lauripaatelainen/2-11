/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.textui;

import com.edii.j211.textui.Tekstikayttoliittyma;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author edii
 */
public class TekstikayttoliittymaTest {
    
    public TekstikayttoliittymaTest() {
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
    public void testaaLueKokonaisluku() {
        Random random = new Random();
        /* 
         Testaa 1000 satunnaisella luvulla, että lueKokonaisluku() palauttaa
         oikean luvun kun syötteenä annetaan pelkkä luku */
        for (int i = 0; i < 1000; i++) {
            int luku = random.nextInt();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Tekstikayttoliittyma ui = new Tekstikayttoliittyma(new Scanner(Integer.toString(luku)), new PrintStream(outStream));
            int luettuLuku = ui.lueKokonaisluku("kehote " + i);
            Assert.assertEquals("lueKokonaisluku() palautti eri luvun kuin odotettiin", luettuLuku, luku);
            Assert.assertEquals("lueKokonaisluku() antoi eri kehotteen kuin odotettiin", outStream.toString(), "kehote " + i + ": ");
        }
        
        /* 
         Testaa 1000 satunnaisella luvulla, että lueKokonaisluku() palauttaa
         oikean luvun kun syötteenä annetaan ensin rivi, joka ei sisällä
         oikein muotoiltua lukua */
        for (int i = 0; i < 1000; i++) {
            int luku = random.nextInt();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            Tekstikayttoliittyma ui = new Tekstikayttoliittyma(new Scanner("a" + luku + "\n" + (luku + 1) + "c\n" + (luku + 2)), new PrintStream(outStream));
            int luettuLuku = ui.lueKokonaisluku("kehote " + i);
            Assert.assertEquals("lueKokonaisluku() palautti eri luvun kuin odotettiin", luku + 2, luettuLuku);
            Assert.assertEquals("lueKokonaisluku() antoi eri kehotteen kuin odotettiin", outStream.toString(), "kehote " + i + ": kehote " + i + ": kehote " + i + ": ");
        }
    }
}
