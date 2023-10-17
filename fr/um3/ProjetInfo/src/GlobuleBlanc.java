package fr.um3.ProjetInfo.src;

public class GlobuleBlanc {

    private Position positionGlobBlanc = new Position(0,0);
    private Etat etatGlobBlanc = Etat.PATROUILLE; // Etat par défaut = Patrouille
    // private static Random random = new Random();

    public GlobuleBlanc(Position positionGlobBlanc, Etat etatGlobBlanc) {
        setPositionGlobBlanc(positionGlobBlanc);
        setEtatGlobBlanc(etatGlobBlanc);
    }

    public  GlobuleBlanc(GlobuleBlanc globuleBlanc){
        positionGlobBlanc = globuleBlanc.positionGlobBlanc;
        etatGlobBlanc = globuleBlanc.etatGlobBlanc;
    }

    public  GlobuleBlanc(){
    }

    public Position getPositionGlobBlanc() {
        return positionGlobBlanc;
    }

    public void setPositionGlobBlanc(Position positionGlobBlanc) {
        this.positionGlobBlanc = positionGlobBlanc;
    }

    public Etat getEtatGlobBlanc() {
        return etatGlobBlanc;
    }

    public void setEtatGlobBlanc(Etat etatGlobBlanc) {
        if(etatGlobBlanc == Etat.PATROUILLE || etatGlobBlanc == Etat.CHASSE)
            this.etatGlobBlanc = etatGlobBlanc;
        else
            System.err.println("\n\n [Etat Non Disponnible Pour Le Globule Blanc]");
    }



    /*
    public void deplacerAleatoirement() {
        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                this.x += MOVE_DISTANCE;  // déplacer à droite
                break;
            case 1:
                this.x -= MOVE_DISTANCE;  // déplacer à gauche
                break;
            case 2:
                this.y += MOVE_DISTANCE;  // déplacer vers le bas
                break;
            case 3:
                this.y -= MOVE_DISTANCE;  // déplacer vers le haut
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

     */
}
