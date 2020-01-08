package main.IHM2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.Generateur.Facile.*;
import main.Generateur.*;
import main.Generateur.Difficile.*;
import main.Takuzu;

public class FenetreNiveau extends JFrame{

    private Fenetre fen;
    private JPanel pan;
    private JButton niveau1;
    private JButton niveau2;
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Verdana";
    private GenerateurTakuzuFacile genfacile;
    private GenerateurTakuzuDifficile gendifficile;
    private int taille;

    private String path = System.getProperty("user.dir");

    public FenetreNiveau(int t) {
        this.largeurFenetre = 300;
        this.hauteurFenetre = 400;
        pan = new JPanel();
        niveau1 = new JButton("Facile"); //création d'un bouton fde niveau facile.
        niveau2 = new JButton("Difficile"); //création d'un bouton de niveau difficile
        taille = t;
    }

    public void creerFenetre(){

            Takuzu tak = Takuzu.load(path + "/Takuzu/Ressources/takuzu_taille_" + taille + ".txt");

            pan.setLayout(null);
            this.setTitle("Niveaux");
            this.setSize(largeurFenetre,hauteurFenetre);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            pan.setBackground(Color.lightGray);
            niveau1.setBackground(Color.white);
            //niveau1.setBounds(120,hauteurFenetre - 300, 150, 150);
            niveau1.setFont(new Font(fontName, Font.BOLD, 40));
            niveau1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            niveau1.setBounds(50, 50, 200, 100);
            niveau1.setFocusPainted(false);
            niveau1.setBorderPainted(false);
            niveau1.setContentAreaFilled(false);
            niveau1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();

                Generateur g = new GenerateurTakuzuFacile(tak);
                Takuzu t = g.generer();
                Fenetre f = new Fenetre(t);
                f.creerFenetre();
            }
        });
            niveau2.setBackground(Color.white);
            //niveau3.setBounds(580,hauteurFenetre - 300, 150, 150);
            niveau2.setFont(new Font(fontName, Font.BOLD, 40));
            niveau2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            niveau2.setBounds(50, 200, 200, 100);
            niveau2.setFocusPainted(false);
            niveau2.setBorderPainted(false);
            niveau2.setContentAreaFilled(false);
            niveau2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();

                Generateur g = new GenerateurTakuzuDifficile(tak);
                Takuzu t = g.generer();
                Fenetre f = new Fenetre(t);
                f.creerFenetre();

            }


        });
            setContentPane(pan);
            pan.add(niveau1);
            pan.add(niveau2);
            this.setVisible(true);
        }
}
