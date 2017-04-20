package com.edii.j211.gui;

import com.edii.j211.logiikka.Peli;
import com.edii.j211.logiikka.Pelikentta;
import com.edii.j211.logiikka.impl.PeliImpl;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.CENTER;

/**
 * Swingillä rakennettu graafinen käyttöliittymä 2^11 pelille. 
 */
public class GraafinenKayttoliittyma extends JFrame {

    private Peli peli;

    private JButton uusiPeliNappi;
    private JLabel infoTeksti;
    private JPanel pelialue;
    private JLabel[][] ruudut;

    public GraafinenKayttoliittyma() {
        luoKayttoliittyma();
        alustaToiminnot();
    }

    private void luoKayttoliittyma() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        uusiPeliNappi = new JButton("Uusi peli");
        uusiPeliNappi.setAlignmentX(CENTER_ALIGNMENT);
        infoTeksti = new JLabel("Aloita peli painamalla nappia");
        infoTeksti.setAlignmentX(CENTER_ALIGNMENT);

        pelialue = new JPanel();

        add(uusiPeliNappi);
        add(infoTeksti);
        setSize(new Dimension(800, 800));
        setTitle("2^11");

        setLocationByPlatform(true);
        
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() != KeyEvent.KEY_PRESSED) {
                    return false;
                }
                
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    peli.ylos();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    peli.alas();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    peli.vasen();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    peli.oikea();
                } else {
                    return false;
                }
                
                paivitaKentta();
                paivitaPisteet();
                return true;
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paivitaKentta();
            }
        });
    }

    private void alustaToiminnot() {
        uusiPeliNappi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogi = new JDialog(GraafinenKayttoliittyma.this);
                dialogi.setLayout(new BoxLayout(dialogi.getContentPane(), BoxLayout.Y_AXIS));
                dialogi.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialogi.setLocationByPlatform(true);

                JPanel rivi = new JPanel();
                rivi.setLayout(new FlowLayout(FlowLayout.CENTER));
                rivi.add(new JLabel("Kentän koko: "));
                JTextField koko = new JTextField();
                koko.setColumns(5);
                koko.setText("4");
                rivi.add(koko);

                JButton okNappi = new JButton("OK");
                JButton peruutaNappi = new JButton("Peruuta");

                JPanel nappirivi = new JPanel();
                nappirivi.setLayout(new FlowLayout(FlowLayout.CENTER));
                nappirivi.add(okNappi);
                nappirivi.add(peruutaNappi);

                okNappi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String kokoStr = koko.getText();
                        try {
                            int koko = Integer.parseInt(kokoStr);
                            if (koko < 2 || koko >= 100) {
                                throw new Exception();
                            } else {
                                uusiPeli(koko);
                                dialogi.dispose();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(dialogi, "Virheellinen koko!", "Virhe", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                peruutaNappi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialogi.dispose();
                    }
                });

                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        dialogi.dispose();
                    }
                });

                dialogi.add(rivi);
                dialogi.add(nappirivi);
                dialogi.pack();
                dialogi.setVisible(true);
            }
        });
    }

    public void uusiPeli(int koko) {
        peli = new PeliImpl(koko);
        luoPelialue();
    }

    private void luoPelialue() {

        this.remove(pelialue);
        pelialue = new JPanel(new GridLayout(peli.koko(), peli.koko()));
        this.add(pelialue);
        ruudut = new JLabel[peli.koko()][peli.koko()];

        for (int y = 0; y < peli.koko(); y++) {
            for (int x = 0; x < peli.koko(); x++) {
                ruudut[y][x] = new JLabel("");
                pelialue.add(ruudut[y][x]);
                ruudut[y][x].setHorizontalAlignment(CENTER);
                ruudut[y][x].setVerticalAlignment(CENTER);
                ruudut[y][x].setBackground(Color.white);
                ruudut[y][x].setOpaque(true);
            }
        }
        
        paivitaKentta();
        invalidate();
        paivitaKentta();
    }
    
    private void paivitaPisteet() {
        infoTeksti.setText("Pisteet: " + peli.pisteet());
    }

    private void paivitaKentta() {
        Pelikentta kentta = peli.pelikentta();

        for (int y = 0; y < peli.koko(); y++) {
            for (int x = 0; x < peli.koko(); x++) {
                int arvo = kentta.arvo(x, y);
                Color fg;
                Color bg;
                
                int height = ruudut[y][x].getHeight();
                System.out.println("" + height);
                ruudut[y][x].setFont(new Font("Arial", Font.BOLD, height / 3));
                
                if (arvo == 0) {
                    ruudut[y][x].setText("");
                    fg = Color.white;
                    bg = new Color(0xcdc1b4);
                } else {
                    ruudut[y][x].setText(Integer.toString(arvo));
                    switch (arvo) {
                        case 2:
                            fg = new Color(0x776e65);
                            bg = new Color(0xeee4da);
                            break;
                        case 4:
                            fg = new Color(0x776e65);
                            bg = new Color(0xede0c8);
                            break;
                        case 8:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xf2b179);
                            break;
                        case 16:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xf59563);
                            break;
                        case 32:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xf67c5f);
                            break;
                        case 64:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xf65e3b);
                            break;
                        case 128:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xedcf72);
                            break;
                        case 256:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xedcc61);
                            break;
                        case 512:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xedc850);
                            break;
                        case 1024:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xedc53f);
                            break;
                        case 2048:
                            fg = new Color(0xf9f6f2);
                            bg = new Color(0xedc22e);
                            break;
                        default:
                            fg = new Color(0xf9f6f2);
                            bg = Color.black;
                    }
                }
                ruudut[y][x].setForeground(fg);
                ruudut[y][x].setBackground(bg);
            }
        }

        this.repaint();
        this.revalidate();
    }

    public static void main(String[] args) {
        new GraafinenKayttoliittyma().setVisible(true);
    }
}
