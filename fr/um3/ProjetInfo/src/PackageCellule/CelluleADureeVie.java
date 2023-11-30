package fr.um3.ProjetInfo.src.PackageCellule;

import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;

public abstract class CelluleADureeVie extends Cellule{
    private int dureeVie;

    CelluleADureeVie(Position position, Etat etat, int dureeVie){
        super(position,etat,40,40);
        setDureeVie(dureeVie);
    }

    CelluleADureeVie(){}

    public int getDureeVie() {
        return dureeVie;
    }
    public void setDureeVie(int dureeVie){
        this.dureeVie = dureeVie;
        System.out.println(this.dureeVie);
    }
}
