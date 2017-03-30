/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edii.j211.logiikka;

import com.edii.j211.logiikka.Pelikentta;

/**
 *
 * @author edii
 */
public interface MuokattavaPelikentta extends Pelikentta {
    public void asetaArvo(int x, int y, int arvo);
}
