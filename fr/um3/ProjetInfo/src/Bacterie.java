package fr.um3.ProjetInfo.src;

import javax.swing.text.Position;

public class Bacterie {
    private Position positionBact; // position de la bacterie
    private Etat etatBact = Etat.RECHERCHE_NOURRITURE; // "recherche nouriture par defaut"

    public Bacterie(Position positionBact, Etat etatBact) {
        setPositionBact(positionBact);
        setEtatBact(etatBact);
    }

    public Position getPositionBact() {
        return positionBact;
    }

    public void setPositionBact(Position positionBact) {
        this.positionBact = positionBact;
    }

    public Etat getEtatBact() {
        return etatBact;
    }

    public void setEtatBact(Etat etatBact) {
        if(etatBact == Etat.RECHERCHE_NOURRITURE || etatBact == Etat.FUITE)
            this.etatBact = etatBact;
        else
            System.err.println("\n\n [Etat Non Disponnible Pour La Bacterie]");
    }
}