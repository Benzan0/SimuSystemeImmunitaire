package fr.um3.ProjetInfo.src.PackageCellule;

import fr.um3.ProjetInfo.src.PackageException.ChanceException;
import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;

public class Nutriment {
    private Position position;
    public static double chanceSpawn = 0.1;
    public int dureeVie = 500;


    public Nutriment(){
        setPosition();
        setDureeVie(dureeVie);
    }

    public static void setChanceSpawn(double chanceSpawn) throws ChanceException{
        if(chanceSpawn < 0 || chanceSpawn > 1){
            throw new ChanceException("Erreur : La chance d'apparition du nutriment doit-être comprise entre 0 et 1");
        }
        Nutriment.chanceSpawn = chanceSpawn;
    }

    public int getDureeVie(){
        return dureeVie;
    }
    public void setDureeVie(int dureeVie){
        this.dureeVie = dureeVie-1;
    }
    public void setPosition(){
        this.position = Position.randPos();
        System.out.println("POSITION NUTRI : "+this.position);

    }

    public Position getPosition(){
        return this.position;
    }
}
