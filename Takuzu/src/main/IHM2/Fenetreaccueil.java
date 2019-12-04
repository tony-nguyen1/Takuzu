package main.IHM2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Fenetreaccueil extends JFrame{
    private Fenetre fen;
    private JButton boutonjouer;
    private JButton boutonquitter;
    private JPanel pan;

    private JLabel titrejeu;
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Verdana";

    public Fenetreaccueil(int largeurFenetre, int hauteurFenetre){
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
        pan = new JPanel(); //création d'un panel.
        titrejeu = new JLabel("TAKUZU");
        boutonjouer = new JButton("JOUER"); //création d'un bouton jouer.
        boutonquitter = new JButton("QUITTER"); //création d'un bouton quitter.

    }

    public void creerFenetre(){
        pan.setLayout(null);
        this.setTitle("Bienvenue sur le jeu du Takuzu");
        this.setSize(largeurFenetre,hauteurFenetre);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        pan.setBackground(Color.lightGray);
        titrejeu.setFont(new Font(fontName, Font.BOLD, 23));
        boutonjouer.setBackground(Color.white);
        boutonjouer.setBounds(120,hauteurFenetre - 300, 300, 200);
        boutonjouer.setFont(new Font(fontName, Font.BOLD, 25));
        boutonjouer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                fen = new Fenetre(8,largeurFenetre,hauteurFenetre);
                fen.creerFenetre();
            }
        });
        boutonquitter.setBackground(Color.white);
        boutonquitter.setBounds(580,hauteurFenetre - 300, 300, 200);
        boutonquitter.setFont(new Font(fontName, Font.BOLD, 25));
        boutonquitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        setContentPane(pan);

        pan.add(boutonjouer);
        pan.add(boutonquitter);
        pan.add(titrejeu);
        this.setVisible(true);
    }
}
