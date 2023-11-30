package fr.um3.ProjetInfo.src.PackageCellule;

import fr.um3.ProjetInfo.src.PackageConstructionSimu.Position;
import fr.um3.ProjetInfo.src.PackageMapping.Rectangle;
import fr.um3.ProjetInfo.src.PackageInterfaceGraphique.SimulationFrame;

import java.util.ArrayList;
import java.util.Random;

import static fr.um3.ProjetInfo.src.PackageConstructionSimu.Position.deplacement;

public class Cellule  {
    Position position;
    Etat etat;
    int cellWidth;
    int cellHeight;
    private int id = 0;
    private static int num = 1;

    private double gCost, hCost; // Coûts pour l'algorithme A*
    private Cellule previous;

    private static ArrayList<Rectangle> vaissauSanguin = SimulationFrame.vaisseauSimu;

    public Cellule(Position position, Etat etat, int cellWidth, int cellHeight) {
        this.position = position;
        this.etat = etat;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.id = num++;
    }

    public Cellule(Position position) {
        this.position = position;
    }

    public Cellule() {
        
    }

    public Cellule(Position position, int cellWidth, int cellHeight) {
        this.position = position;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        setGCost(gCost);
        setHCost(hCost);
        setPrevious(previous);
    }

    public int getId(){
        return this.id;
    }
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public int getWidth() {
        return cellWidth;
    }


    public int getHeight() {
        return cellHeight;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public double getHCost() {
        return hCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    public double getFCost() {
        return gCost + hCost;
    }

    public Cellule getPrevious() {
        return previous;
    }

    public void setPrevious(Cellule previous) {
        this.previous = previous;
    }

    private Position calculerNouvellePosition(Position position, int direction, int deplacement) {
        switch (direction) {
            case 0: return new Position(position.getX() + deplacement, position.getY()); // x+1
            case 1: return new Position(position.getX() - deplacement, position.getY()); // x-1
            case 2: return new Position(position.getX(), position.getY() + deplacement); // y+1
            case 3: return new Position(position.getX(), position.getY() - deplacement); // y-1
            default: throw new IllegalArgumentException("Direction non valide: " + direction);
        }
    }


    public void setDeplacement(ArrayList<Rectangle> vaisseauxSanguin) {
        Random rand = new Random();
        Position positionActuelle = this.getPosition();
        int[] directions = new int[]{0, 1, 2, 3}; // Représente les 4 directions possibles

        boolean deplacementFait = false;
        for (int i = 0; i < directions.length; i++) {
            int randomIndex = rand.nextInt(directions.length);
            // Échangez l'élément actuel avec un élément aléatoire
            int temp = directions[i];
            directions[i] = directions[randomIndex];
            directions[randomIndex] = temp;

            Position nouvellePosition = calculerNouvellePosition(positionActuelle, directions[i], deplacement);
            Cellule cellTest = new Cellule(nouvellePosition, this.getWidth(), this.getHeight());

            // Cherchez un vaisseau sanguin qui ne chevauche pas la position testée
            if (vaisseauxSanguin.stream().noneMatch(vs -> vs.intersects(cellTest))) {
                this.setPosition(nouvellePosition);
                deplacementFait = true;
                break;
            }
        }

        // Sortie de débogage

        if (!deplacementFait) {
            System.out.println("Aucun déplacement possible n'a été trouvé.");
        } else {
            //System.out.println("Position après déplacement: " + this.getPosition());
        }

    }


    public boolean equals(Object o){ // 2 cellules sont eguales quand elles ont le mm id
        if(o == null){
            return false;
        }
        if(o instanceof Cellule c){
            return c.getId() == this.getId();
        }
        else {
            return false;
        }
    }


}


