# 2^11

Klooni suositusta 2048-pelistä java-työpöytäpelinä. Alkuperäinen Gabriele Cirullin luoma peli löytyy osoitteesta: https://gabrielecirulli.github.io/2048/.

Kyseessä on peli, jonka pelikenttä on 4x4-kokoinen ruudukko, ja pelinappuloina toimivat laatikot, joiden arvot ovat kahden potensseja. Kun pelaaja painaa nuolinäppäintä (ylös, alas, vasemmalle tai oikealle) siirtyy kaikki luvut kyseiseen suuntaan, jos siinä suunnassa on tilaa liikkua. Kahden saman arvoisen luvun kohdatessa, ne yhdistyvät, ja luvuksi saadaan näiden summa, eli seuraava kahden potenssi (2^n + 2^n = 2*2^n = 2^(n+1)). Jokaisen siirron jälkeen ruudukkoon lisätään satunnaiseen tyhjään kohtaan kakkonen tai nelonen. Pelaajan peli loppuu, kun vapaita siirtoja ei ole. Alkuperäisessä pelissä, pelaaja voittaa pelin, kun ruudukolle saadaan luku 2048, mutta peliä voi jatkaa siitä hyvin paljon pidemmällekin. 

Peliä käyttää vain yksi pelaaja kerrallaan. 