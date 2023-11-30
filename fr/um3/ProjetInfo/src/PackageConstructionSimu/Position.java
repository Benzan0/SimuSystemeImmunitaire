package fr.um3.ProjetInfo.src.PackageConstructionSimu;

import fr.um3.ProjetInfo.src.PackageCellule.Bacterie;
import fr.um3.ProjetInfo.src.PackageCellule.GlobuleBlanc;
import fr.um3.ProjetInfo.src.PackageMapping.Rectangle;

import java.util.*;


public class Position implements Comparable<Position> {
    public static final int width = 1000;
    public static final int height = 1000;
    public static final int deplacement = 10;
    private int x = 0;
    private int y = 0;

    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public Position(){}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Position other) {
        int distance = this.x * this.x + this.y * this.y;
        int otherDistance = other.x * other.x + other.y * other.y;

        return Integer.compare(distance, otherDistance);
    }

    public String toString() {
        return ("Pos X : " + this.getX() + "|||| Pos Y : " + this.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof Position) {
            Position p = (Position) obj;
            return this.getX() == p.getX() && this.getY() == p.getY();
        } else
            return false;
    }


    private int distance(Position other) {
        // Distance entre cette position et une autre (peut être simplement la distance de Manhattan)
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }



    public static Position randPos(){
        Random random = new Random();
        int x = 10*random.nextInt(0,100);
        int y = 10*random.nextInt(0,60);
        return (new Position(x, y));

    }

    public static Position randPos(GlobuleBlanc gb){
        Random random = new Random();

        int x = 10*random.nextInt(0,100);
        int y = 10*random.nextInt(74,76);
        return (new Position(x, y));

    }

    public static Position randPos(Bacterie bacterie){
        Random random = new Random();

        int x = 10*random.nextInt(0,100);
        int y = 10*random.nextInt(0,70);
        return (new Position(x, y));

    }
}