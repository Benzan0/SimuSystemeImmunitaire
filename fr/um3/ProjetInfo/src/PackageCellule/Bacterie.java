package fr.um3.ProjetInfo.src.PackageCellule;

import fr.um3.ProjetInfo.src.PackageConstructionSimu.Generation;
import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;

public class Bacterie extends CelluleADureeVie{
    private static int dureeDeVie = Generation.dureeDeVieBact;

    public Bacterie(Position position, Etat etat) {
        super(position,etat,dureeDeVie);
    }
    public Bacterie() {
    }
}