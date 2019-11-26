package main.IHM2;

import main.Solveur.BacktrackIntelligent.BacktrackIntelligent;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fenetre extends JFrame{


    private Takuzu takuzu;
    private Takuzu takuzuBackup;
    private GridLayout gridLayout;
    private JPanel pan; //panel pour la grille
    private JPanel panelboutons; //panel pour le niveau
    private JButton bsolution;
    private JComboBox jComboBox;

    private int tailleTakuzu;
    private int largeurFenetre;
    private int hauteurFenetre;
    private int fontSize;

    private String fontName = "Verdana";

    public Fenetre(int tailleTakuzu, int largeurFenetre, int hauteurFenetre) {
        this.tailleTakuzu = tailleTakuzu;
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
        this.fontSize = determineFontSize();

        takuzu = new Takuzu(tailleTakuzu); //création d'un takuzu
        gridLayout = new GridLayout(takuzu.getHeightGrille(), takuzu.getWidthGrille(), 1, 1); //création d'une grille
        pan = new JPanel(gridLayout); //création d'un panel contenant la grille.
        bsolution = new JButton("Solution"); //création d'un bouton solution.
        Object[] elements = new Object[]{"Facile", "Moyen", "Difficile", "Diabolique"};
        jComboBox = new JComboBox(elements);
        panelboutons = new JPanel();
    }

    public void creerFenetre(){

        pan.setPreferredSize(new Dimension(largeurFenetre - 125, hauteurFenetre));//le panel pan ne prend qu'une partie du panel de la fenêtre.
        pan.setBackground(Color.lightGray); // modification de couleur du panel
        takuzu.preRemplissage();
        panelboutons.setBackground(Color.red);
        panelboutons.setLayout(null);
        panelboutons.add(bsolution);
        bsolution.setBounds(0, hauteurFenetre - 200, 125, 100);
        System.out.println(bsolution.getX());
        System.out.println(bsolution.getY());
        bsolution.setAlignmentY(100);
        panelboutons.add(jComboBox);
        jComboBox.setBounds(0, hauteurFenetre - 500, 125, 80);
        this.setTitle("Jeu du Takuzu"); //titre de la fenêtre
        this.setSize(largeurFenetre, hauteurFenetre); // dimension fenêtre
        this.setLocationRelativeTo(null); //la fenêtre s'ouvre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête tout le processus quand on clique sur la croix rouge
        //this.getContentPane().setBackground(Color.cyan);
        //pan.setBorder(blackline);
        this.remplirGrille(takuzu); //utilisation de la méthode remplirgrille()
        takuzuBackup = takuzu.cloneTakuzu();
        jComboBox.setBackground(Color.lightGray);
        bsolution.setBackground(Color.orange); //modification de couleur du bouton "solution"
        bsolution.setFont(new Font(fontName, Font.BOLD, 18)); //modification de la police du bouton "solution"
        bsolution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    //takuzu.affichage();
                    viderGrille();
                    //Si ca crash, test backtrackIntelligent
                    boolean resolution = takuzu.seResoudre(new Hypotheses());
                    if (resolution) { //S'il a réussi à résoudre
                        takuzu.affichage();
                        remplirGrille(takuzu);
                    }
                    else {
                        System.out.println("La résolution à partir de ce qui a été fait est un echec, nous allons donc reprendre le takuzu initial");
                        takuzuBackup.seResoudre(new Hypotheses());
                        takuzuBackup.affichage();
                        remplirGrille(takuzuBackup);
                    }
                }
            }
        });
        //bsolution.addActionListener(this);
        //Border blackline = BorderFactory.createLineBorder(Color.lightGray,1);
        //panbsol.add(bsolution);

       // this.getContentPane().add(panbsol,BorderLayout.EAST);
        this.getContentPane().add(panelboutons);
        this.getContentPane().add(pan, BorderLayout.WEST); //ajout du panel Pane
        //this.getContentPane().add(bsolution); //ajout du bouton bsolution au Pane de la fenêtre
        this.setVisible(true); //ligne permettant de rendre la fenêtre visible
    }

    public void remplirGrille(Takuzu takuzu) {
        System.out.println(takuzu);
        int value;
        for (int x = 0; x < takuzu.getHeightGrille(); x++) { //parcours de la grille
            for (int y = 0; y < takuzu.getWidthGrille(); y++) {
                value = takuzu.getValue(x, y);
                if (value == 0 || value == 1) {
                    JPanel ptest = new JPanel(); //création d'un panel pour chaque case.
                    JLabel label = new JLabel(Integer.toString(value), SwingConstants.CENTER); //création d'un label avec la valeur 1 ou 0.
                    label.setFont(new Font(fontName, Font.BOLD, fontSize)); //modification de la police
                    ptest.add(label); //ajout des labels avec la valeur 1 ou 0 dans les panels ptest
                    pan.add(ptest); // ajout de tous les panels avec un 1 ou 0 dans pan.
                }

                else {
                    JButton bouton = new JButton();//création d'un bouton si pas de valeur
                    pan.add(bouton); //ajout du bouton dans le pan
                    bouton.setContentAreaFilled(false);
                    bouton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JLabel label = new JLabel();
                            bouton.setOpaque(true);

                            //Grace a ces formules magiques on trouve la valeur de X et de Y par rapport au Takuzu, on peut donc faire le lien entre le panel et le takuzu désormais
                            int boutonX = bouton.getX()/(pan.getSize().width/tailleTakuzu);
                            int boutonY =  bouton.getY()/(pan.getSize().height/tailleTakuzu);
                            int i = 0;

                            if (e.getClickCount()%2 == 1) {
                                label.setText("0");
                                label.setFont(new Font(fontName, Font.BOLD, fontSize));
                                takuzu.play0(boutonY, boutonX);

                            }
                            else {
                                label.setText("1");
                                label.setFont(new Font(fontName, Font.BOLD, fontSize));
                                takuzu.play1(boutonY, boutonX);
                            }
                            bouton.setText(label.getText());
                            bouton.setFont(label.getFont());


                            if (!takuzu.estValide()){
                                bouton.setBackground(Color.red);
                            }
                            else {

                                for (int z = 0; z < tailleTakuzu*tailleTakuzu; z++){
                                    pan.getComponent(z).setBackground(Color.white);
                                }
                            }


                            if (takuzu.estGagnant()){
                                for (int z = 0; z < tailleTakuzu*tailleTakuzu; z++){
                                    bouton.setBackground(Color.orange);
                                }
                            }

                        }
                    });

                }

            }
        }
    }

    public void viderGrille() {
        pan.removeAll();
        pan.revalidate();
        pan.repaint();
    }

    public JPanel getPan(){
        return this.pan;
    }

    private int determineFontSize(){
        if (tailleTakuzu == 6)
            return 75;
        else if (tailleTakuzu == 8)
            return 60;
        else if (tailleTakuzu == 10)
            return 45;
        else if (tailleTakuzu == 12)
            return 35;
        else if (tailleTakuzu == 14)
            return 30;
        else if (tailleTakuzu == 16)
            return 25;
        else return 10;
    }

}
