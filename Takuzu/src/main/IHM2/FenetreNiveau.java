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
    private JButton niveau3;
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Verdana";
    private GenerateurTakuzuFacile genfacile;
    private GenerateurTakuzuDifficile gendifficile;


    public FenetreNiveau() {
        this.largeurFenetre = 400;
        this.hauteurFenetre = 520;
        pan = new JPanel();
        niveau1 = new JButton("Facile"); //création d'un bouton fde niveau facile.
        niveau2 = new JButton("Moyen"); //création d'un bouton de niveau moyen.
        niveau3 = new JButton("Difficile"); //création d'un bouton de niveau difficile
    }

    public void creerFenetre(){
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
            niveau1.setBounds(100, 0, 200, 100);
            niveau1.setFocusPainted(false);
            niveau1.setBorderPainted(false);
            niveau1.setContentAreaFilled(false);
            niveau1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // faire en sorte que ça génère une grille facile
                dispose();
                Takuzu t = new Takuzu(8);
                t.preRemplissage();
                Fenetre f = new Fenetre(t);
                f.creerFenetre();

                /*
                dispose();

                Generateur g = new GenerateurTakuzuFacile(20, Takuzu.getPreRemplissageAnswer2());
                Takuzu t = g.generer();

                t.preRemplissage();
                Fenetre f = new Fenetre(t);
                f.creerFenetre();

                 */
            }
        });
            niveau2.setBackground(Color.white);
            //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
            niveau2.setFont(new Font(fontName, Font.BOLD, 40));
            niveau2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            niveau2.setBounds(100,150, 200, 100);
            niveau2.setFocusPainted(false);
            niveau2.setBorderPainted(false);
            niveau2.setContentAreaFilled(false);
            niveau2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // faire en sorte que ça génère une grille moyenne
                dispose();
                Takuzu t = new Takuzu(8);
                t.preRemplissage();
                Fenetre f = new Fenetre(t);
                f.creerFenetre();
            }
        });
            niveau3.setBackground(Color.white);
            //niveau3.setBounds(580,hauteurFenetre - 300, 150, 150);
            niveau3.setFont(new Font(fontName, Font.BOLD, 40));
            niveau3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            niveau3.setBounds(100, 300, 200, 100);
            niveau3.setFocusPainted(false);
            niveau3.setBorderPainted(false);
            niveau3.setContentAreaFilled(false);
            niveau3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // faire en sorte que ça génère une grille difficile
                /*
                dispose();

                GenerateurTakuzuDifficile g = new GenerateurTakuzuDifficile(14, 0);
                Takuzu t = g.generer();

                t.preRemplissage();
                Fenetre f = new Fenetre(t);
                f.creerFenetre();

                 */
                dispose();
                Takuzu t = new Takuzu(8);
                t.preRemplissage();
                Fenetre f = new Fenetre(t);
                f.creerFenetre();
            }


        });
            setContentPane(pan);
            pan.add(niveau1);
            pan.add(niveau2);
            pan.add(niveau3);
            this.setVisible(true);
        }
}
