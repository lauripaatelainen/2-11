package com.edii.j211;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author edii
 */
public class MainTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outStream));
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testMain() {
        String[] strArr1 = new String[] {};
        Main.main(strArr1);
        assert strArr1.length == 0;
        assert outStream.toString().length() == 0;
        
        outStream.reset();
        String[] strArr2 = new String[] {"asd"};
        Main.main(strArr2);
        assert strArr2[0].equals("Testi");
        assert outStream.toString().trim().equals("Testi");
    }
}
