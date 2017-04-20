package com.edii.j211.pisterekisteri;

import java.io.File;
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
 * se oikeaan kohtaan listaa ja jos lista oli entuudestaan täynnä, huonoin
 * tulos poistetaan.
 * 
 * @see PisteRekisteriIO
 */
public class PisteRekisteri {
    private Map<Integer, List<Tulos>> pistetaulut;
    
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
     * Palauttaa annetun kokoisen pelikentän top10-pisteet laskevassa järjestyksessä.
     * @param koko Pelikentän koko
     * @return Lista pelaajien tuloksia laskevassa järjestyksessä. null jos yhtään tulosta ei ole tallennettu
     */
    public List<Tulos> pistetaulu(int koko) {
        return pistetaulut.getOrDefault(koko, null);
    }
    
    /**
     * Palautta kaikki pistetaulut.
     * @return Map joka sisältää kaikki pistetaulut, avaimena toimii kentän koko.
     */
    public Map<Integer, List<Tulos>> pistetaulut() {
        return pistetaulut;
    }
}
