package com.edii.j211.pisterekisteri;

/**
 * Pelaajan saama pistemäärä tietyllä kentän koolla.
 */
public class Tulos implements Comparable<Tulos> {

    private final String nimi;
    private final int pisteet;
    private final int koko;

    /**
     * Luo uuden Tulos-olion annetuilla tiedoilla.
     *
     * @param nimi Pelaajan nimi jolle tulos kuuluu
     * @param pisteet Saadut pisteet
     * @param koko Pelikentän koko, jolla pisteet saatiin
     */
    public Tulos(String nimi, int pisteet, int koko) {
        this.nimi = nimi;
        this.pisteet = pisteet;
        this.koko = koko;
    }

    public String getNimi() {
        return nimi;
    }

    public int getPisteet() {
        return pisteet;
    }

    public int getKoko() {
        return koko;
    }

    @Override
    public int compareTo(Tulos o) {
        return Integer.compare(o.pisteet, pisteet);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tulos)) {
            return false;
        }

        Tulos toinen = (Tulos) obj;
        return nimi.equals(toinen.nimi) && pisteet == toinen.pisteet && koko == toinen.koko;
    }
}
