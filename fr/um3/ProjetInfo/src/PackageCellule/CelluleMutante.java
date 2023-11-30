package fr.um3.ProjetInfo.src.PackageCellule;

import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;

import java.util.ArrayList;

public class CelluleMutante extends CelluleADureeVie {

    private static final int dureeVie = 10;

    private Position destination = Position.randPos();

    public CelluleMutante(Position position) {
        super(position,Etat.ERRANCE,dureeVie);
        setEtat();
        setDestination(destination);

    }

    public void setEtat(){
        this.etat = Etat.ERRANCE;
    }

    public void setDestination(Position destination){
        this.destination = destination;
    }
    public Position getDestination(){
        return destination;
    }
}
