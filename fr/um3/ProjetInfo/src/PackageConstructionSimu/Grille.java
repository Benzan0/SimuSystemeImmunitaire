package fr.um3.ProjetInfo.src.PackageConstructionSimu;

import fr.um3.ProjetInfo.src.PackageCellule.*;
import fr.um3.ProjetInfo.src.PackageMapping.Rectangle;
import fr.um3.ProjetInfo.src.PackageInterfaceGraphique.SimulationFrame;

import java.util.ArrayList;
import java.util.List;

public class Grille {
    private List<Cellule> listCellules;
    private List<Nutriment> listNutriments;
    private List<Toxine> listToxines    ;
    private static ArrayList<Rectangle> vaissauSanguin = SimulationFrame.vaisseauSimu;

    public Grille() {
        listCellules = new ArrayList<>();
        listNutriments = new ArrayList<>();
        listToxines = new ArrayList<>();
    }

    public List<Toxine> getListToxines(){
        return this.listToxines;
    }
    public List<Nutriment> getListNutriments(){
        return this.listNutriments;
    }

    public List<Cellule> getListCellules() {
        return listCellules;
    }
    public void ajouterCellule(Cellule cellule) {
        listCellules.add(cellule);
    }

    private void etatBact(Bacterie b){
        b.setEtat(Etat.RECHERCHE_NOURRITURE);
        for(Nutriment n : getListNutriments()){
            if(estDansRayon(b.getPosition(),n.getPosition(),Generation.distanceDetection)){
                b.setEtat(Etat.GO_TO_NUT);
            }
        }
    }

    private void etatGB(GlobuleBlanc gb){
        gb.setEtat(Etat.PATROUILLE);
        for(Cellule c : getListCellules()){
            if(c instanceof Bacterie b && estDansRayon(gb.getPosition(),b.getPosition(),Generation.distanceDetectionGB)){
                gb.setEtat(Etat.CHASSE);
            }
        }

    }
    // Méthode pour déplacer une cellule spécifique
    public void deplacerCellule(Cellule cellule) {
        if(cellule instanceof CelluleADureeVie cav){
            cav.setDureeVie(cav.getDureeVie()-1);
        }

        if(cellule instanceof Bacterie){
            System.out.println("Est une bactérie");
            Bacterie bact = (Bacterie) cellule;
            etatBact(bact);
            if(bact.getEtat()==Etat.RECHERCHE_NOURRITURE)
                bact.setDeplacement(vaissauSanguin);
            if(bact.getEtat() == Etat.GO_TO_NUT){
                if(nearestObj(bact) != null){
                    Position prochaineEtape = pathfindingVersCible(bact.getPosition(), nearestObj(bact).getPosition());
                    bact.setPosition(prochaineEtape);
                } else
                    bact.setDeplacement(vaissauSanguin);
            }

        }
        if(cellule instanceof GlobuleBlanc){
            GlobuleBlanc gb = (GlobuleBlanc) cellule;
            etatGB(gb);
            if(gb.getEtat() == Etat.PATROUILLE){
                System.out.println("Deplacement Pat ok");
                gb.setDeplacement(vaissauSanguin);
            }
            if(gb.getEtat() == Etat.CHASSE) {
                if (nearestObj(gb) != null) {
                    System.out.println("prochaineEtape");
                    Position prochaineEtape = pathfindingVersCible(gb.getPosition(),nearestObj(gb).getPosition());
                    gb.setPosition(prochaineEtape);
                } else
                    gb.setDeplacement(vaissauSanguin);
            }
        }
        if(cellule instanceof CelluleMutante cm){
            System.out.println("Destination :"+cm.getDestination());
            if(cm.getPosition().equals(cm.getDestination())){
                cm.setDestination(Position.randPos());
                System.out.println("Changement de Destination");
            }
            Position prochaineEtape = pathfindingVersCible(cm.getPosition(), cm.getDestination());
            cm.setPosition(prochaineEtape);

            listToxines.add(new Toxine(cellule.getPosition()));
        }
    }

    private double calculateDistance(Position pos1, Position pos2) {
        double dx = pos1.getX() - pos2.getX();
        double dy = pos1.getY() - pos2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Bacterie nearestObj(GlobuleBlanc gb) {
        ArrayList<Bacterie> listBact = new ArrayList<Bacterie>();
        for(Cellule cell : listCellules) {
            if (cell instanceof Bacterie) {
                Bacterie bact = (Bacterie) cell;
                listBact.add(bact);
            }
        }
        if(!listBact.isEmpty()){
            Bacterie closest = null;
            double minDistance = Double.MAX_VALUE; // Initialiser avec la plus grande valeur possible

            for (Bacterie bacterie : listBact) {
                double distance = calculateDistance(gb.getPosition(), bacterie.getPosition());

                if (distance < minDistance) {
                    minDistance = distance;
                    closest = bacterie;
                }
            }

            return closest;
        }
        else
            return null;
    }

    public Nutriment nearestObj(Bacterie bact) {
        if(!getListNutriments().isEmpty()){
            Nutriment closest = null;
            double minDistance = Double.MAX_VALUE; // Initialiser avec la plus grande valeur possible

            for (Nutriment n : getListNutriments()) {
                double distance = calculateDistance(bact.getPosition(), n.getPosition());
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = n;
                }
            }

            return closest;
        }
        else
            return null;
    }

    public static boolean estDansRayon(Position centre, Position position, int rayon) {
        int dx = centre.getX() - position.getX();
        int dy = centre.getY() - position.getY();
        return dx * dx + dy * dy <= rayon * rayon;
    }

    public Position pathfindingVersCible(Position positionChasseur, Position positionCible) {


        int dx = positionCible.getX() - positionChasseur.getX();
        int dy = positionCible.getY() - positionChasseur.getY();

        // Déterminer la direction pour se rapprocher de la cible
        int moveX = Integer.compare(dx, 0) * 10; // Se déplace de 10 pixels ou reste sur place
        int moveY = Integer.compare(dy, 0) * 10;

        // Vérifier si le mouvement direct est possible (sans obstacles)
        Position nouvellePosition = new Position(positionChasseur.getX() + moveX, positionChasseur.getY() + moveY);
        if (!estBloqueParObstacle(nouvellePosition)) {
            System.out.println(positionChasseur);
            System.out.println("NouvellePos direct");
            System.out.println(nouvellePosition);
            return nouvellePosition;
        }

        // Essayer un mouvement alternatif (horizontal ou vertical uniquement)
        nouvellePosition = new Position(positionChasseur.getX() + moveX, positionChasseur.getY());
        if (!estBloqueParObstacle(nouvellePosition)) {
            System.out.println(positionChasseur +" ///" + nouvellePosition);
            System.out.println("NouvellePos horiz");
            return nouvellePosition;
        }

        nouvellePosition = new Position(positionChasseur.getX(), positionChasseur.getY() + moveY);
        if (!estBloqueParObstacle(nouvellePosition)) {
            System.out.println(positionChasseur +" ///" + nouvellePosition);
            System.out.println("NouvellePos vert");
            System.out.println(nouvellePosition);
            return nouvellePosition;
        }

        // Aucun mouvement possible vers la cible sans rencontrer un obstacle
        System.out.println("pas De nouvellePos");
        System.out.println(nouvellePosition);
        return positionChasseur;
    }

    private boolean estBloqueParObstacle(Position position) {
        int cellWidth = 40;
        int cellHeight = 40;

        Cellule cellule = new Cellule(position, cellWidth, cellHeight);

        for (Rectangle obstacle : vaissauSanguin) {
            if(obstacle.intersects(cellule)) {
                return true;
            }
        }

        return false;
    }
}
