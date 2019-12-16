package main.IHM2;


import main.Solveur.BacktrackIntelligent.BacktrackIntelligent;
import main.Solveur.Hypotheses.Hypotheses;
import main.Solveur.MaitreSolveur.MaitreSolveur;
import main.Takuzu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Fenetre extends JFrame{


    private Takuzu takuzu;
    private Takuzu takuzuBackup;
    private GridLayout gridLayout;
    private FenetreRegles fenetreRegles;
    private FenetreNiveau fenetreNiveau;
    private JPanel pan; //panel pour la grille
    private JPanel panelboutons; //panel pour le niveau
    private JButton bsolution, undo, bregles, bniveau, undoJusquaSolution;
    /*private JComboBox jComboBox;
    private Object[] elements = new Object[]{"Facile", "Difficile"};*/
    private int tailleTakuzu;
    private int largeurFenetre;
    private int hauteurFenetre;
    private int fontSize;

    private Deque<Takuzu> DequeTakuzu;

    private String fontName = "Verdana";

    public Fenetre(Takuzu t) {
        DequeTakuzu = new ArrayDeque<>();
        this.largeurFenetre = 800;
        this.hauteurFenetre = 800;

        tailleTakuzu = t.getTailleGrille();
        fontSize = determineFontSize();

        gridLayout = new GridLayout(tailleTakuzu, tailleTakuzu); //création d'une grille
        pan = new JPanel(gridLayout); //création d'un panel contenant la grille.
        bsolution = new JButton("Solution"); //création d'un bouton solution.
        undo = new JButton("Undo");
        bniveau = new JButton("Niveau");
        panelboutons = new JPanel();
        bregles = new JButton("Règles");

        takuzu = t;
        takuzuBackup = t.cloneTakuzu();
    }


    public void creerFenetreParametre(){

        this.setTitle("Jeu du Takuzu"); //titre de la fenêtre
        this.setSize(largeurFenetre, hauteurFenetre); // dimension fenêtre
        this.setLocationRelativeTo(null); //la fenêtre s'ouvre au centre de l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //arrête tout le processus quand on clique sur la croix rouge

        pan.setPreferredSize(new Dimension(largeurFenetre - 125, hauteurFenetre));//le panel pan ne prend qu'une partie du panel de la fenêtre.
        pan.setBackground(Color.lightGray); // modification de couleur du panel

        panelboutons.setBackground(Color.darkGray);
        panelboutons.setLayout(null);
        panelboutons.add(bsolution);
        panelboutons.add(undo);
        panelboutons.add(bregles);
        panelboutons.add(bniveau);

        bsolution.setBounds(0, hauteurFenetre - 200, 125, 100);
        bregles.setBounds(0, hauteurFenetre - 400, 125, 100);
        undo.setBounds(0, hauteurFenetre - 600, 125, 100);
        bniveau.setBounds(0, 0, 125, 100);
    }

    public void creerBouton(){
        //Bouton solution
        bsolution.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        bsolution.setFont(new Font(fontName, Font.BOLD, 18)); //modification de la police du bouton "solution"
        bsolution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {
                    //takuzu.affichage();
                    viderGrille();

                    boolean resolution = takuzu.seResoudre(new Hypotheses());
                    if (resolution) { //S'il a réussi à résoudre
                        takuzu.affichage();
                        //remplirGrille(takuzu);
                    } else {
                        System.out.println("La résolution à partir de ce qui a été fait est un echec, nous allons donc reprendre le takuzu initial");
                        takuzuBackup.seResoudre(new Hypotheses());
                        takuzuBackup.affichage();
                        //remplirGrille(takuzuBackup);
                    }
                }
            }
        });

        //Bouton undo
        undo.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        undo.setFont(new Font(fontName, Font.BOLD, 18));
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 0) {

                    if (!DequeTakuzu.isEmpty()) {
                        takuzu = DequeTakuzu.pollLast();
                        remplirGrilleDifference(takuzu);

                        //Retire le rouge au cas ou le takuzu ne serait plus valide
                        if (takuzu.estValide()){
                            colorieGris();
                        }

                    }

                }
            }
        });

        //Bouton des règles
        bregles.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        bregles.setFont(new Font(fontName, Font.BOLD, 18)); //modification de la police du bouton "solution"
        bregles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fenetreRegles = new FenetreRegles();
                fenetreRegles.creerFenetre();
            }
        });

        //Bouton de la selection de niveau
        bniveau.setBackground(new Color(148, 159, 230)); //modification de couleur du bouton "solution"
        bniveau.setFont(new Font(fontName, Font.BOLD, 18)); //modification de la police du bouton "solution"
        bniveau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                fenetreNiveau = new FenetreNiveau();
                fenetreNiveau.creerFenetre();
            }
        });

        this.getContentPane().add(panelboutons);
        this.getContentPane().add(pan, BorderLayout.WEST); //ajout du panel Pane

    }

    public void creerFenetre() {
        creerFenetreParametre();
        creerBouton();
        this.creerGrille();
        this.setVisible(true); //ligne permettant de rendre la fenêtre visible

    }

    public void creerGrille() {
        int value;
        for (int x = 0; x < tailleTakuzu; x++) { //parcours de la grille
            for (int y = 0; y < tailleTakuzu; y++) {
                value = takuzu.getValue(x, y);
                JButton ptest = new JButton(); //création d'un bouton pour chaque case.


                if (value == 0 || value == 1) {
                    JLabel label = new JLabel(Integer.toString(value), SwingConstants.CENTER); //création d'un label avec la valeur 1 ou 0.
                    label.setFont(new Font(fontName, Font.BOLD, fontSize)); //modification de la police
                    ptest.add(label); //ajout des labels avec la valeur 1 ou 0 dans les panels ptest
                    ptest.setBackground(Color.WHITE);
                }

                else{
                    ptest.setContentAreaFilled(false);
                    ptest.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JLabel label = new JLabel();
                            ptest.setOpaque(true);
                            //On sauvegarde le takuzu afin de l'ajouter dans la pile (pour undo)
                            Takuzu takuzuASauvegarder = takuzu.cloneTakuzu();

                            //Grace a ces formules magiques on trouve la valeur de X et de Y par rapport au Takuzu, on peut donc faire le lien entre le panel et le takuzu désormais
                            int boutonX = ptest.getX() / (pan.getSize().width / tailleTakuzu);
                            int boutonY = ptest.getY() / (pan.getSize().height / tailleTakuzu);

                            //Si on clique une fois on joue un 0, si on reclique on joue un 1
                            if (e.getClickCount() >= 1) {

                                label.setFont(new Font(fontName, Font.BOLD, fontSize));

                                if (ptest.getText().equals("") || ptest.getText().equals("1")) {
                                    label.setText("0");
                                    takuzu.play0(boutonY, boutonX);
                                } else {
                                    label.setText("1");
                                    takuzu.play1(boutonY, boutonX);
                                }

                                ptest.setText(label.getText());
                                ptest.setFont(label.getFont());
                                DequeTakuzu.add(takuzuASauvegarder);
                            }

                            //Colorie en rouge si le Takuzu n'est plus valide
                            if (!takuzu.estValide()){
                                ptest.setBackground(Color.RED);
                            }

                            //Sinon recolorie en gris
                            else {
                                colorieGris();
                            }

                            //Colorie en orange si le takuzu est gagnant
                            if (takuzu.estGagnant()){
                                for (int z = 0; z < tailleTakuzu*tailleTakuzu; z++){
                                    pan.getComponent(z).setBackground(Color.orange);
                                }
                            }

                        }
                    });
                }
                //Ajout du bouton à chaque fin d'itération d'une boucle
                pan.add(ptest);
            }
        }
    }

    /**
     *
     * @param t le takuzu qui va servir d'exemple
     * La fonction va changer l'affichage de la grille de sorte à ce qu'il corresponde à celui du takuzu t
     */
    public void remplirGrilleDifference(Takuzu t) {
        int value;
        int compteurComposant = 0;


        for (int x = 0; x < tailleTakuzu; x++) { //parcours de la grille
            for (int y = 0; y < tailleTakuzu; y++) {
                JLabel label = new JLabel();
                label.setFont(new Font(fontName, Font.BOLD, fontSize));

                value = t.getValue(x, y);

                if ((value == 0 || value == 1) && takuzuBackup.getValue(x, y) == -1) {
                    label.setText(String.valueOf(value));
                }

                if ((value == -1)){
                    label.setText("");
                }

                JButton bouton = (JButton)pan.getComponent(compteurComposant);
                bouton.setText(label.getText());
                bouton.setFont(label.getFont());

                compteurComposant++;
            }
        }

    }

    /**
     * Fonction qui sert à colorier tout en gris dans le cas les cases étaient pas rempli initialement (en gros ca sert à retirer le rouge qui est mis quand la case n'est pas valide)
     */
    private void colorieGris(){
        for (int z = 0; z < tailleTakuzu * tailleTakuzu; z++) {
            int ligne, colonne;
            //C'est pour faire le lien entre la grille et le takuzu afin de colorier UNIQUEMENT les cases dont le joueur a joué dessus
            ligne = z / tailleTakuzu;
            colonne = z % tailleTakuzu;
            if (takuzuBackup.getValue(ligne, colonne) == -1) {
                pan.getComponent(z).setBackground(Color.lightGray);
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

    public int getLargeurFenetre(){
        return largeurFenetre;
    }

    public int getHauteurFenetre(){
        return hauteurFenetre;
    }
    public Takuzu getTakuzu(){
        return takuzu;
    }
}
