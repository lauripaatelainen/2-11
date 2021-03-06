package com.edii.j211.pisterekisteri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pisterekisterin ylläpidosta vastaava luokka. Tämä luokka tarjoaa
 * käyttöliittymälle yksinkertaisen rajapinnan tuloslistojen hallintaan.
 *
 * Rekisteri säilyttää erillisen top10-listan jokaista kentän kokoa kohden. Kun
 * rekisteriin lisätään uusi pistemäärä joka yltää top10-listalle, sijoitetaan
 * se oikeaan kohtaan listaa ja jos lista oli entuudestaan täynnä, huonoin tulos
 * poistetaan.
 *
 * @see PisteRekisteriIO
 */
public class PisteRekisteri {

    private Map<Integer, List<Tulos>> pistetaulut;

    /**
     * Luo uuden tyhjän pisterekisten.
     */
    public PisteRekisteri() {
        this.pistetaulut = new HashMap<>();
    }

    /**
     * Lisää pelaajan saaman tuloksen rekisteriin.
     *
     * @param tulos Pelaajan tulos
     */
    public void lisaaTulos(Tulos tulos) {
        if (!pistetaulut.containsKey(tulos.getKoko())) {
            pistetaulut.put(tulos.getKoko(), new ArrayList<>());
        }

        List<Tulos> pistetaulu = pistetaulut.get(tulos.getKoko());
        pistetaulu.add(tulos);
        Collections.sort(pistetaulu);
        if (pistetaulu.size() > 10) {
            pistetaulu.remove(10);
        }
    }

    /**
     * Palauttaa annetun kokoisen pelikentän top10-pisteet laskevassa
     * järjestyksessä.
     *
     * @param koko Pelikentän koko
     * @return Lista pelaajien tuloksia laskevassa järjestyksessä. null jos
     * yhtään tulosta ei ole tallennettu
     */
    public List<Tulos> pistetaulu(int koko) {
        return pistetaulut.getOrDefault(koko, null);
    }

    /**
     * Palautta kaikki pistetaulut.
     *
     * @return Map joka sisältää kaikki pistetaulut, avaimena toimii kentän
     * koko.
     */
    public Map<Integer, List<Tulos>> pistetaulut() {
        return pistetaulut;
    }

    /**
     * Laskee pisteillä saadun sijan top10-listalla.
     *
     * @param koko Pelikentän koko jolla pistemäärä saatiin
     * @param pisteet Saadut pisteet
     * @return Sija (1-10), -1 jos pisteet ei riittänyt top10-listalle.
     */
    public int sijoitus(int koko, int pisteet) {
        List<Tulos> tulokset = pistetaulu(koko);
        if (tulokset == null || tulokset.isEmpty()) {
            return 1;
        } else if (tulokset.size() == 10 && tulokset.get(tulokset.size() - 1).getPisteet() >= pisteet) {
            return -1;
        } else {
            for (int i = 0; i < tulokset.size(); i++) {
                if (tulokset.get(i).getPisteet() < pisteet) {
                    return i + 1;
                }
            }
            return tulokset.size() + 1;
        }
    }
}
