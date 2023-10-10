package fr.um3.ProjetInfo.src;

import java.util.ArrayList;

public class Generation {


    public static final int distanceDetection = 100;
    public final int deplacement = 10;

    static java.util.ArrayList<Bacterie> listBact = new java.util.ArrayList<>();
    static java.util.ArrayList<GlobuleBlanc> listGlobblanc = new java.util.ArrayList<>();
    java.util.ArrayList<CelluleMutante> listCelMut = new java.util.ArrayList<>();

    public void listCellules(java.util.ArrayList<Bacterie> listBact, java.util.ArrayList<GlobuleBlanc> listGlobblanc, java.util.ArrayList<CelluleMutante> listCelMut) {
        setListBact(listBact);
        setListGlobblanc(listGlobblanc);
        setListCelMut(listCelMut);
    }

    public java.util.ArrayList<Bacterie> getListBact() {
        return listBact;
    }

    public void setListBact(ArrayList<Bacterie> listBact) {
        Generation.listBact = listBact;
    }

    public java.util.ArrayList<GlobuleBlanc> getListGlobblanc() {
        return listGlobblanc;
    }

    public void setListGlobblanc(java.util.ArrayList<GlobuleBlanc> listGlobblanc) {
        Generation.listGlobblanc = listGlobblanc;
    }

    public java.util.ArrayList<CelluleMutante> getListCelMut() {
        return listCelMut;
    }

    public void setListCelMut(ArrayList<CelluleMutante> listCelMut) {
        this.listCelMut = listCelMut;
    }

    public static double calculateDistance(double xGB, double xBact, double yGB, double yBact) {
        double xDiff = xGB - xBact;
        double yDiff = yGB - yBact;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public boolean isFuite(Bacterie bacterie) {
        for (GlobuleBlanc globuleBlanc : getListGlobblanc()) {
            double distance = calculateDistance(
                    globuleBlanc.getPositionGlobBlanc().getX(),
                    bacterie.getPositionBact().getX(),
                    globuleBlanc.getPositionGlobBlanc().getY(),
                    bacterie.getPositionBact().getY()
            );

            if (distance <= distanceDetection) {
                return true;
            }
        }
        return false;
    }


    public void generation() {

        //position des bacterie
        for (int i = 0; i < getListBact().size(); i++) {


                if(isFuite(getListBact().get(i))){
                    getListBact().get(i).setEtatBact(Etat.FUITE);
                }
                else
                    getListBact().get(i).setEtatBact(Etat.RECHERCHE_NOURRITURE);

            System.out.println(isFuite(getListBact().get(i)));


            Position newPos = getListBact().get(i).getPositionBact();
            if (getListBact().get(i).getEtatBact() == Etat.RECHERCHE_NOURRITURE) {
                newPos.deplacerAleatoirement();
            } else {
                // on regarde quel est le globule blanc le plus proche
                double minDist = Double.MAX_VALUE; // Initialise la distance minimale avec la valeur la plus élevée possible
                int cellulePlusProche = 0; // Initialise l'index de la cellule la plus proche

                for (int i2 = 0; i2 < getListGlobblanc().size(); i2++) {
                    int xGB = getListGlobblanc().get(i2).getPositionGlobBlanc().getX();
                    int yGB = getListGlobblanc().get(i2).getPositionGlobBlanc().getY();

                    int xBact = getListBact().get(i).getPositionBact().getX();
                    int yBact = getListBact().get(i).getPositionBact().getY();
                    double tempDistCell = calculateDistance(xGB, xBact, yGB, yBact);

                    if (tempDistCell < minDist) {
                        minDist = tempDistCell;
                        cellulePlusProche = i2;
                    }
                }

                for (int i2 = 0; i < getListBact().size()-1; i++) {
                    Bacterie bacterie = getListBact().get(i);

                    // Si l'état de la bactérie est Fuite, s'éloigner du globule blanc le plus proche
                    if (bacterie.getEtatBact() == Etat.FUITE) {
                        // Trouver le globule blanc le plus proche
                        double minDistance = Double.MAX_VALUE;
                        GlobuleBlanc closestGlobuleBlanc = null;

                        for (GlobuleBlanc globuleBlanc : getListGlobblanc()) {
                            double distance = calculateDistance(
                                    globuleBlanc.getPositionGlobBlanc().getX(), bacterie.getPositionBact().getX(),
                                    globuleBlanc.getPositionGlobBlanc().getY(), bacterie.getPositionBact().getY()
                            );

                            if (distance < minDistance) {
                                minDistance = distance;
                                closestGlobuleBlanc = globuleBlanc;
                            }
                        }

                        // S'éloigner du globule blanc le plus proche
                        if (closestGlobuleBlanc != null) {
                            int dx = bacterie.getPositionBact().getX() - closestGlobuleBlanc.getPositionGlobBlanc().getX();
                            int dy = bacterie.getPositionBact().getY() - closestGlobuleBlanc.getPositionGlobBlanc().getY();
                            double magnitude = Math.sqrt(dx * dx + dy * dy);

                            // Modifie X
                            int newX = bacterie.getPositionBact().getX();
                            if(dx > 0)
                                newX += Position.deplacement;
                            if (dx < 0)
                                newX -= Position.deplacement;

                            // Modifie Y
                            int newY = bacterie.getPositionBact().getY();
                            if(dy > 0)
                                newY += Position.deplacement;
                            if (dy < 0)
                                newY -= Position.deplacement;

                            if(dy==0){
                                if(dx==0){
                                    Position positionInitial = new Position(bacterie.getPositionBact().getX(),
                                            bacterie.getPositionBact().getY());
                                    GlobuleBlanc newGlobuleBlanc = new GlobuleBlanc(positionInitial, Etat.PATROUILLE);

                                    getListBact().remove(i);


                                }
                            }
                            // Mettre à jour la position de la bactérie
                            bacterie.getPositionBact().setX(newX);
                            bacterie.getPositionBact().setY(newY);
                        }
                    }
                }
            }

            getListBact().get(i).setPositionBact(newPos);

            System.out.println("************************* "+i+" *************************");
            System.out.println(getListBact().get(i).getPositionBact().getX()+" | "+getListBact().get(0).getPositionBact().getY());
            System.out.println(getListBact().get(i).getEtatBact().toString());
        }
        for(int i = 0; i<getListGlobblanc().size(); i++){
            Position newPos = getListGlobblanc().get(i).getPositionGlobBlanc();

            if (getListGlobblanc().get(i).getEtatGlobBlanc() == Etat.PATROUILLE) {
                newPos.deplacerAleatoirement();
            }
        }
    }

}

