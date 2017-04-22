package com.edii.j211.pisterekisteri;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Luokka joka vastaa pisterekisterin lukemisesta ja tallentamisesta tiedostoon.
 * 
 * @see #avaa(java.lang.String) 
 * @see #tallenna(com.edii.j211.pisterekisteri.PisteRekisteri, java.lang.String) 
 */
public class PisteRekisteriIO {
    /**
     * Avaa parametrina annetusta polusta löytyvän pisterekisterin ja palauttaa
     * luetun PisteRekisteri-olion. Jos annettua polkua ei löydy, yrittää metodi
     * luoda uuden tyhjän pisterekisterin. Jos tiedostonkäsittely epäonnistuu,
     * heittää metodi IOExceptionin.
     * 
     * @param tiedostonimi Polku tiedostoon josta pelirekisteri luetaan
     * @return Palauttaa luetun pisterekisterin tai tyhjän rekisterin jos tiedostoa ei ollut olemassa
     * @throws IOException jos tiedoston lukeminen tai uuden tiedoston luominen ei onnistu
     */
    public static PisteRekisteri avaa(String tiedostonimi) throws IOException {
        File tiedosto = new File(tiedostonimi);
        if (!tiedosto.exists()) {
            PisteRekisteri rekisteri = new PisteRekisteri();
            tallenna(rekisteri, tiedostonimi);
            return rekisteri;
        } else {
            try {
                Scanner lukija = new Scanner(tiedosto);
                PisteRekisteri rekisteri = new PisteRekisteri();
                if (!lukija.hasNextLine()) {
                    return rekisteri;
                }

                String rivi = lukija.nextLine().trim();
                if (rivi.charAt(0) != '#') {
                    throw new IOException("Tiedosto ei sisällä kelvollista pisterekisteriä");
                }
                int koko = Integer.parseInt(rivi.substring(1));

                while (lukija.hasNextLine()) {
                    rivi = lukija.nextLine().trim();

                    if (rivi.startsWith("#")) {
                        koko = Integer.parseInt(rivi.substring(1));
                    } else {
                        String[] osat = rivi.split("\t", 2);
                        rekisteri.lisaaTulos(new Tulos(osat[0], Integer.parseInt(osat[1]), koko));
                    }
                }
                
                return rekisteri;
            } catch (IOException e) {
                throw new IOException("Virhe pisterekisterin lukemisessa: " + e.getMessage());
            } catch (Exception e) {
                throw new IOException("Tuntematon virhe pisterekisterin lukemisessa");
            }
            
        }
    }
    
    /**
     * Tallentaa pisterekisterin annettuun polkuun. Onnistuessa metodi ei
     * palauta mitään, jos tiedostonkäsittely epäonnistuu,
     * heittää metodi IOExceptionin.
     * 
     * @param rekisteri Tallennettava rekisteri
     * @param tiedostonimi Polku johon rekisteri tallennetaan
     * @throws IOException jos rekisterin tallennus epäonnistuu
     */
    public static void tallenna(PisteRekisteri rekisteri, String tiedostonimi) throws IOException {
        File tiedosto = new File(tiedostonimi);
        PrintStream ps = new PrintStream(tiedosto);
        Map<Integer, List<Tulos>> pistetaulut = rekisteri.pistetaulut();
        for (int koko : pistetaulut.keySet()) {
            ps.println("#" + koko);
            for (Tulos tulos : pistetaulut.get(koko)) {
                ps.println(tulos.getNimi() + "\t" + tulos.getPisteet());
            }
        }
    }
}
