package fr.um3.ProjetInfo.src.PackageCellule;

import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;

public class GlobuleBlanc extends Cellule  {
    private Etat etat = Etat.PATROUILLE;
    private static final int dureeVie = 50;

    public GlobuleBlanc(Position position,Etat etat) {
        super(position,etat,40,40);
    }

    public  GlobuleBlanc(){
        super();
    }
}
