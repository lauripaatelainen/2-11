package com.edii.j211;

import com.edii.j211.textui.Tekstikayttoliittyma;
import java.util.Scanner;

/**
 * Pääluokka pelin käynnistykseen. Kehitysvaiheessa käynnistää
 * testikäyttöön tarkoitetun tekstikäyttöliittymän, myöhemmin
 * graafisen käyttöliittymän. 
 * 
 * @author edii
 */
public class Main {
    public static void main(String[] args) {
        new Tekstikayttoliittyma(new Scanner(System.in), System.out).kaynnista();
    }
}
