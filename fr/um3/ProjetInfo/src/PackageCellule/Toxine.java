package fr.um3.ProjetInfo.src.PackageCellule;

import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;

public class Toxine {
    private int dureeVie = 5;
    private Position position;

    public Toxine(Position position) {
        setDureeVie(dureeVie);
        setPosition(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getDureeVie() {
        return dureeVie;
    }

    public void setDureeVie(int dureeVie) {
        this.dureeVie = dureeVie;
    }
}
