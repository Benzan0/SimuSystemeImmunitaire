package fr.um3.ProjetInfo.src;

import java.util.ArrayList;
import java.util.List;

public class Generation {


    public static final int distanceDetection = 100;
    public static final int distanceDetectionGB = 800;
    public final int deplacement = 10;

    private static java.util.ArrayList<Bacterie> listBact = new java.util.ArrayList<>();
    private static java.util.ArrayList<GlobuleBlanc> listGlobblanc = new java.util.ArrayList<>();
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

    // detecte si fuir
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

    // detecte si prend en chasse
    public boolean isEnChasse(GlobuleBlanc globuleBlanc) {
        for (Bacterie bacterie : getListBact()) {
            double distance = calculateDistance(
                    globuleBlanc.getPositionGlobBlanc().getX(),
                    bacterie.getPositionBact().getX(),
                    globuleBlanc.getPositionGlobBlanc().getY(),
                    bacterie.getPositionBact().getY()
            );

            // Check si la distance est inférieure ou égale à la distance de détection
            // et si la bacterie est dans un état qui peut être chassé
            if (distance <= distanceDetectionGB && bacterie.getEtatBact() != Etat.FUITE) {
                return true;
            }
        }
        return false;
    }

    // trouve la bacterie la plus proche
    private Bacterie findClosestBacterie(GlobuleBlanc globuleBlanc) {
        double minDistance = Double.MAX_VALUE;
        Bacterie closestBacterie = null;

        for (Bacterie bacterie : getListBact()) {
            double distance = calculateDistance(
                    globuleBlanc.getPositionGlobBlanc().getX(), bacterie.getPositionBact().getX(),
                    globuleBlanc.getPositionGlobBlanc().getY(), bacterie.getPositionBact().getY()
            );

            if (distance < minDistance) {
                minDistance = distance;
                closestBacterie = bacterie;
            }
        }
        return closestBacterie;
    }


    // se dirige vers la bactierie la plus proche
    private void moveToBacterie(GlobuleBlanc globuleBlanc, Bacterie targetBacterie) {
        // Calculer les différences de position en X et Y entre le globule blanc et la bactérie cible
        int dx = targetBacterie.getPositionBact().getX() - globuleBlanc.getPositionGlobBlanc().getX();
        int dy = targetBacterie.getPositionBact().getY() - globuleBlanc.getPositionGlobBlanc().getY();

        // Modifie X
        int newX = globuleBlanc.getPositionGlobBlanc().getX();
        if (dx < 0)
            newX -= Position.deplacementGB;
        if (dx > 0)
            newX += Position.deplacementGB;

        // Modifie Y
        int newY = globuleBlanc.getPositionGlobBlanc().getY();
        if (dy < 0)
            newY -= Position.deplacementGB;
        if (dy > 0)
            newY += Position.deplacementGB;

        // Mettre à jour la position du globule blanc
        globuleBlanc.getPositionGlobBlanc().setX(newX);
        globuleBlanc.getPositionGlobBlanc().setY(newY);
    }

    // Fonction déterminant si les coordonnées de 2 individu sont egales
    private boolean isCoordEquals(double xGB, double xBact, double yGB, double yBact){
        return calculateDistance(xGB,xBact,yGB,yBact) == 0;
    }

    public void generation() {

        //position des bacterie
        for (int i = 0; i < getListBact().size(); i++) {


            if (isFuite(getListBact().get(i))) {
                getListBact().get(i).setEtatBact(Etat.FUITE);
            } else
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
                        if (dx > 0)
                            newX += Position.deplacement;
                        if (dx < 0)
                            newX -= Position.deplacement;

                        // Modifie Y
                        int newY = bacterie.getPositionBact().getY();
                        if (dy > 0)
                            newY += Position.deplacement;
                        if (dy < 0)
                            newY -= Position.deplacement;
                        // Mettre à jour la position de la bactérie
                        bacterie.getPositionBact().setX(newX);
                        bacterie.getPositionBact().setY(newY);
                    }
                }
            }


            getListBact().get(i).setPositionBact(newPos);

            System.out.println("************************* " + i + " *************************");
            System.out.println(getListBact().get(i).getPositionBact().getX() + " | " + getListBact().get(i).getPositionBact().getY());
            System.out.println(getListBact().get(i).getEtatBact().toString());
        }

    // -------------------------------------------GB-------------------------------------------------
      for(int i =0 ; i<getListGlobblanc().size() ; i++) {
          for (GlobuleBlanc globuleBlanc : getListGlobblanc()) {
              Position newPos = globuleBlanc.getPositionGlobBlanc();

              if (isEnChasse(globuleBlanc)) {
                  globuleBlanc.setEtatGlobBlanc(Etat.CHASSE);
              }
              // Cette partie semble incorrecte, car elle modifie l'état des bactéries en se basant sur l'index du globule blanc. Vérifiez ceci.
              else {
                  getListGlobblanc().get(getListGlobblanc().indexOf(globuleBlanc)).setEtatGlobBlanc(Etat.PATROUILLE);
              }

              if (globuleBlanc.getEtatGlobBlanc() == Etat.PATROUILLE) {
                  newPos.deplacerAleatoirement();
              } else if (globuleBlanc.getEtatGlobBlanc() == Etat.CHASSE) {
                  Bacterie closestBacterie = findClosestBacterie(globuleBlanc);
                  if (closestBacterie != null) {
                      moveToBacterie(globuleBlanc, closestBacterie);
                  }
              }

              ArrayList<Bacterie> newBact = new ArrayList<>(getListBact());
              ArrayList<GlobuleBlanc> newGB = new ArrayList<>(getListGlobblanc());
              for (int i2 = 0; i2 < getListBact().size() ; i2++) {
                  System.err.println("OK");
                  if(isCoordEquals(
                          newGB.get(i).getPositionGlobBlanc().getX(),
                          newBact.get(i2).getPositionBact().getX(),
                          newGB.get(i).getPositionGlobBlanc().getY(),
                          newBact.get(i2).getPositionBact().getY())){

                      // Supprime la bactérie
                      getListBact().remove(i2);

                      // Crée un nouveau globule blanc avec un décalage X de +20
                      GlobuleBlanc originalGb = getListGlobblanc().get(i);
                      GlobuleBlanc newGbunit = new GlobuleBlanc(); // Adaptez cette partie selon votre implémentation.
                      newGbunit.setPositionGlobBlanc(new Position(originalGb.getPositionGlobBlanc().getX(), originalGb.getPositionGlobBlanc().getY()));
                      newGB.add(newGbunit);
                  }

                  setListGlobblanc(newGB);

              }


          }
          getListGlobblanc().get(i).setEtatGlobBlanc(Etat.PATROUILLE);

          System.out.println("size : "+getListGlobblanc().size());
          System.out.println("GB : "+ getListGlobblanc().get(i).getPositionGlobBlanc().getX() + " | " +
                  getListGlobblanc().get(i).getPositionGlobBlanc().getY());
          System.out.println("GB : "+ getListGlobblanc().get(i).getEtatGlobBlanc());

      }


    }
}

