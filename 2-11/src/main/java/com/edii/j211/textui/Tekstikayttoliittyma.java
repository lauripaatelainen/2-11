package com.edii.j211.textui;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.Pelikentta;
import com.edii.j211.logiikka.impl.PeliImpl;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Tekstikäyttöliittymä pelin logiikan testaamista varten. Peliä voi pelata
 * komennoilla alas / ylös / vasemmalle / oikealle, vaikkei se kovin nopeaa ole.
 * 
 * @author edii
 */
public class Tekstikayttoliittyma {
    private Scanner in;
    private PrintStream out;
    
    /**
     * Luo uuden Tekstikäyttöliittymän. 
     * 
     * @param in Scanner-olio käyttäjän syötettä varten (yleensä new Scanner(System.in))
     * @param out PrintStream-olio käyttäjälle näytettävää tekstiä varten (yleensä System.out)
     */
    public Tekstikayttoliittyma(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }
    
    /**
     * Lukee käyttäjältä kokonaisluvun. Lukee lukua niin pitkään, kunnes
     * kelvollinen luku on annettu. Ei heitä virheitä. 
     * 
     * Sisäinen metodi tekstikäyttöliitymän toteutusta varten.
     * 
     * @param kehote Käyttäjälle näytettävä kehote
     * @return Luettu kokonaisluku
     */
    public int lueKokonaisluku(String kehote) {
        while (true) {
            out.print(kehote + ": ");
            String rivi = in.nextLine();
            try {
                return Integer.parseInt(rivi);
            } catch (NumberFormatException e) {
            /* suorita luuppi uudestaan */
            }
        }
    }
    
    /**
     * Lukee käyttäjältä valinnan annetuista vaihtoehdoista. 
     * 
     * Lukee niin pitkään, kunnes kelvollinen luku on annettu. Ei heitä virheitä. 
     * 
     * Sisäinen metodi tekstikäyttöliitymän toteutusta varten.
     * 
     * @param kehote Käyttäjälle näytettävä kehote
     * @return Luettu valinta
     */
    public String lueValinta(String kehote, String[] vaihtoehdot) {
        while (true) {
            out.print(kehote + "(" + vaihtoehdot[0]);
            for (int i = 1; i < vaihtoehdot.length; i++) {
                out.print("/" + vaihtoehdot[i]);
            }
            out.print("): ");
            String rivi = in.nextLine();
            try {
                for (String vaihtoehto : vaihtoehdot) {
                    if (vaihtoehto.equals(rivi)) {
                        return vaihtoehto;
                    }
                }
            } catch (NumberFormatException e) {
            /* suorita luuppi uudestaan */
            }
        }
    }
    
    /**
     * Tulostaa parametrina annetun kentän.
     * 
     * @param kentta Tulostettava kenttä
     */
    public void tulostaKentta(Pelikentta kentta) {
        for (int y = 0; y < kentta.koko(); y++) {
            String rivi = "";
            for (int x = 0; x < kentta.koko(); x++) {
                rivi += kentta.arvo(x, y) + " ";
            }
            out.println(rivi);
        }
    }
    
    /**
     * Käynnistää pelin. Käyttöliittymä pyytää käyttäjältä kentän koon,
     * jonka jälkeen peli aloitetaan. Metodi lopettaa suorituksen vasta
     * kun peli päättyy. 
     */
    public void kaynnista() {
        int kentanKoko = lueKokonaisluku("Anna kentän koko");
        Peli peli = new PeliImpl(kentanKoko);
        Pelikentta kentta = peli.pelikentta();
        
        while (true) {
            tulostaKentta(kentta);
            
            out.println();
            out.println("Pisteet: " + Integer.toString(peli.pisteet()));
            out.println();
            
            String valinta = lueValinta("Valinta", new String[] {"alas", "ylos", "vasen", "oikea", "lopeta"});
            if (valinta.equals("lopeta")) {
                break;
            } else if (valinta.equals("ylos")) {
                peli.ylos();
            } else if (valinta.equals("alas")) {
                peli.alas();
            } else if (valinta.equals("vasen")) {
                peli.vasen();
            } else if (valinta.equals("oikea")) {
                peli.oikea();
            }
        }
    }
}
