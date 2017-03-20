package com.edii.j211;

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
     * Käynnistää pelin. Käyttöliittymä pyytää käyttäjältä kentän koon,
     * jonka jälkeen peli aloitetaan. Metodi lopettaa suorituksen vasta
     * kun peli päättyy. 
     */
    public void kaynnista() {
        int kentanKoko = lueKokonaisluku("Anna kentän koko");
        
    }
}
