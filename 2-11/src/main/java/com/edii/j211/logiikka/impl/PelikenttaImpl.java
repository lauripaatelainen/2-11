package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;
import java.util.ArrayList;
import java.util.List;

/**
 * Ylläpitää pelikentän tilanteen. 
 * 
 * @author edii
 */
public class PelikenttaImpl implements MuokattavaPelikentta {
    private int koko;
    private int[][] kentta;
    
    public PelikenttaImpl(int koko) {
        this.koko = koko;
        this.kentta = new int[koko][koko];
    }
    
    @Override
    public int koko() {
        return koko;
    }
    
    @Override
    public void asetaArvo(int x, int y, int arvo) {
        this.kentta[y][x] = arvo;
    }
    
    @Override
    public int arvo(int x, int y) {
        return this.kentta[y][x];
    }
    
    @Override
    public void tyhjenna() {
        for (int y = 0; y < koko; y++) {
            for (int x = 0; x < koko; x++) {
                asetaArvo(x, y, 0);
            }
        }
    }

    @Override
    public int[][] tyhjat() {
        List<int[]> out = new ArrayList<>();
        
        for (int y = 0; y < koko; y++) {
            for (int x = 0; x < koko; x++) {
                if (arvo(x, y) == 0) {
                    out.add(new int[] { x, y });
                }
            }
        }
        
        int[][] outArr = new int[out.size()][];
        return out.toArray(outArr);
    }
}
