package com.edii.j211;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.edii.j211.Main;
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
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testMain() {
        String[] strArr1 = new String[] {};
        Main.main(strArr1);
        assert strArr1.length == 0;
        
        String[] strArr2 = new String[] {"asd"};
        Main.main(strArr2);
        assert strArr2[0].equals("Testi");
    }
}
