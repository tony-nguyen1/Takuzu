package main;

public class Case {

    private int ligne;
    private int colonne;

    public  Case(int l, int c){
        ligne = l;
        colonne = c;
    }

    @Override
    public String toString() {
        return  "[ " +  ligne + " , " + colonne + " ]";
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public Case clone(){
        Case caseAReturn = new Case(-1, -1);
        caseAReturn.setLigne(this.getLigne());
        caseAReturn.setColonne(this.getColonne());
        return caseAReturn;
    }
}
