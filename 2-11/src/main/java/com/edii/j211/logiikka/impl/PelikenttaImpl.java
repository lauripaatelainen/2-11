package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;
import com.edii.j211.logiikka.Pelikentta;
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
}
