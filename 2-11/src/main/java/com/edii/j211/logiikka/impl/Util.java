package com.edii.j211.logiikka.impl;

import com.edii.j211.logiikka.MuokattavaPelikentta;
import com.edii.j211.logiikka.Pelikentta;
import java.util.ArrayList;
import java.util.List;

/**
 * Staattinen apuluokka pelikenttää koskevia operaatioita varten.
 *
 * Luokan metodit eivät ole riippuvaisi tietyistä toteutuksista, vaan
 * parametrina annetaan rajapintaoliot.
 */
public final class Util {

    /**
     * Tyhjentää annetun pelikentän. ts. asettaa kaikkiin ruutuihin arvoksi
     * nollan.
     *
     * @param kentta Pelikenttä joka tyhjennetään
     */
    public static void tyhjenna(MuokattavaPelikentta kentta) {
        for (int y = 0; y < kentta.koko(); y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                kentta.asetaArvo(x, y, 0);
            }
        }
    }

    /**
     * Etsii pelikentän tyhjät ruudut. Palauttaa taulukon kahden kokoisia
     * taulukoita, jotka sisältävät x- ja y-koordinaatit jokaiseen tyhjään
     * ruutuun. Taulukkoa tulkitaan näin: arr[0][0] = x-koordinaatti, arr[0][1]
     * = y-koordinaatti.
     *
     * @param kentta Pelikenttä, jonka tyhjät ruudut etsitään.
     * @return Tyhjien ruutujen koordinaatit kaksiulotteisena taulukkona.
     */
    public static int[][] tyhjat(Pelikentta kentta) {
        List<int[]> out = new ArrayList<>();

        for (int y = 0; y < kentta.koko(); y++) {
            for (int x = 0; x < kentta.koko(); x++) {
                if (kentta.arvo(x, y) == 0) {
                    out.add(new int[]{x, y});
                }
            }
        }

        int[][] outArr = new int[out.size()][];
        return out.toArray(outArr);
    }
}
