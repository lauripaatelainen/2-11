/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;
import com.edii.j211.logiikka.Pelikentta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edii
 */
public final class Util {    
    public static void tyhjenna(MuokattavaPelikentta kentta) {
        for (int y = 0; y < kentta.koko(); y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                kentta.asetaArvo(x, y, 0);
            }
        }
    }
    
    public static int[][] tyhjat(Pelikentta kentta) {
        List<int[]> out = new ArrayList<>();
        
        for (int y = 0; y < kentta.koko(); y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) == 0) {
                    out.add(new int[] { x, y });
                }
            }
        }
        
        int[][] outArr = new int[out.size()][];
        return out.toArray(outArr);
    }    
}
