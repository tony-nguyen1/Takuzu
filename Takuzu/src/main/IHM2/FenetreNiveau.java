package main.IHM2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetreNiveau extends JFrame{

    private Fenetre fen;
    private JPanel pan;
    private JButton niveau1;
    private JButton niveau2;
    private JButton niveau3;
    private int largeurFenetre;
    private int hauteurFenetre;
    private String fontName = "Verdana";


    public FenetreNiveau() {
        this.largeurFenetre = 400;
        this.hauteurFenetre = 520;
        pan = new JPanel();
        niveau1 = new JButton("Facile"); //création d'un bouton fde niveau facile.
        niveau2 = new JButton("Moyen"); //création d'un bouton de niveau moyen.
        niveau3 = new JButton("Difficile"); //création d'un bouton de niveau difficile
    }

    public void creerFenetre(){
            //pan.setLayout(null);
            this.setTitle("Niveaux");
            this.setSize(largeurFenetre,hauteurFenetre);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            pan.setBackground(Color.lightGray);
            niveau1.setBackground(Color.white);
            //niveau1.setBounds(120,hauteurFenetre - 300, 150, 150);
            niveau1.setFont(new Font(fontName, Font.BOLD, 25));
            niveau1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            niveau1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                fen = new Fenetre(8,1000, 800);
                fen.creerFenetre();
            }
        });
            niveau2.setBackground(Color.white);
            //niveau2.setBounds(580,hauteurFenetre - 300, 150, 150);
            niveau2.setFont(new Font(fontName, Font.BOLD, 25));
            niveau2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            niveau3.setBackground(Color.white);
            //niveau3.setBounds(580,hauteurFenetre - 300, 150, 150);
            niveau3.setFont(new Font(fontName, Font.BOLD, 25));
            niveau3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            setContentPane(pan);
            pan.add(niveau1);
            pan.add(niveau2);
            pan.add(niveau3);
            this.setVisible(true);
        }
}
