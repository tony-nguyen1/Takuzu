package takuzu.IHM2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Fenetreaccueil extends JFrame{
    private FenetreTaille fen;
    private JButton boutonjouer;
    private JButton boutonquitter;
    private JPanel pan;

    private JLabel titrejeu;
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Corbel";

    public Fenetreaccueil(int largeurFenetre, int hauteurFenetre){
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
        pan = new JPanel(); //création d'un panel.
        titrejeu = new JLabel(" Le TaKuZu");
        boutonjouer = new JButton("Jouer"); //création d'un bouton jouer.
        boutonquitter = new JButton("Quitter"); //création d'un bouton quitter.

    }

    public void creerFenetre(){
        pan.setLayout(null);
        this.setTitle("Takuzu");
        this.setSize(largeurFenetre,hauteurFenetre);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //pan.setBackground(Color.lightGray);
        titrejeu.setBounds(320, hauteurFenetre -700, 450, 200);
        titrejeu.setFont(new Font(fontName, Font.BOLD, 70));
        boutonjouer.setBounds(120,hauteurFenetre - 300, 250, 70);
        boutonjouer.setFont(new Font(fontName, Font.PLAIN, 60));
        boutonjouer.setFocusPainted(false);
        boutonjouer.setBorderPainted(false);
        boutonjouer.setContentAreaFilled(false);
        boutonjouer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                fen = new FenetreTaille();
                fen.creerFenetre();
            }
        });
        //boutonquitter.setForeground(new Color(245,230,77));
        boutonquitter.setBounds(580,hauteurFenetre - 300, 250, 70);
        boutonquitter.setFont(new Font(fontName, Font.PLAIN, 60));
        boutonquitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        boutonquitter.setFocusPainted(false);
        boutonquitter.setBorderPainted(false);
        boutonquitter.setContentAreaFilled(false);
        setContentPane(pan);
        pan.add(boutonjouer);
        pan.add(boutonquitter);
        pan.add(titrejeu);
        this.setVisible(true);
    }

    public int getLargeurFenetre(){
        return largeurFenetre;
    }

    public int getHauteurFenetre(){
        return hauteurFenetre;
    }
}